/*
 * eGov suite of products aim to improve the internal efficiency,transparency,
 * accountability and the service delivery of the government  organizations.
 *
 *  Copyright (C) 2016  eGovernments Foundation
 *
 *  The updated version of eGov suite of products as by eGovernments Foundation
 *  is available at http://www.egovernments.org
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program. If not, see http://www.gnu.org/licenses/ or
 *  http://www.gnu.org/licenses/gpl.html .
 *
 *  In addition to the terms of the GPL license to be adhered to in using this
 *  program, the following additional terms are to be complied with:
 *
 *      1) All versions of this program, verbatim or modified must carry this
 *         Legal Notice.
 *
 *      2) Any misrepresentation of the origin of the material is prohibited. It
 *         is required that all modified versions of this material be marked in
 *         reasonable ways as different from the original version.
 *
 *      3) This license does not grant any rights to any user of the program
 *         with regards to rights under trademark law for use of the trade names
 *         or trademarks of eGovernments Foundation.
 *
 *  In case of any queries, you can reach eGovernments Foundation at contact@egovernments.org.
 */

package org.egov.eis.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.egov.eis.broker.EmployeeProducer;
import org.egov.eis.config.PropertiesManager;
import org.egov.eis.model.EmployeeDocument;
import org.egov.eis.model.Nominee;
import org.egov.eis.model.User;
import org.egov.eis.model.enums.EntityType;
import org.egov.eis.repository.NomineeRepository;
import org.egov.eis.service.helper.NomineeNominatingEmployeeMapper;
import org.egov.eis.web.contract.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

//import org.egov.eis.broker.NomineeProducer;
//import org.egov.eis.model.NomineeDocument;
//import org.egov.eis.repository.NomineeJurisdictionRepository;
//import org.egov.eis.repository.NomineeLanguageRepository;
//import org.egov.eis.service.exception.NomineeIdNotFoundException;
//import org.egov.eis.service.helper.NomineeHelper;
//import org.egov.eis.service.helper.NomineeUserMapper;

@Service
public class NomineeService {

    public static final Logger LOGGER = LoggerFactory.getLogger(NomineeService.class);

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private EmployeeProducer employeeProducer;

    @Autowired
    private NomineeRepository nomineeRepository;

    @Autowired
    private EmployeeDocumentsService documentsService;

    @Autowired
    private PropertiesManager propertiesManager;

    @Autowired
    private UserService userService;

    @Autowired
    private NomineeNominatingEmployeeMapper nomineeNominatingEmployeeMapper;

    public List<Nominee> getNominees(NomineeGetRequest nomineeGetRequest, RequestInfo requestInfo) {
        List<Nominee> nominees = nomineeRepository.findForCriteria(nomineeGetRequest);

        if (nominees.isEmpty())
            return nominees;

        List<Long> ids = nominees.stream().map(nominee -> nominee.getEmployee().getId()).collect(Collectors.toList());
        LOGGER.debug("NominatingEmployee ids " + ids);
        EmployeeCriteria employeeCriteria = EmployeeCriteria.builder()
                .id(ids).tenantId(nomineeGetRequest.getTenantId()).build();
        List<User> users = userService.getUsers(employeeCriteria, requestInfo);
        LOGGER.debug("userService: " + users);
        nomineeNominatingEmployeeMapper.mapNominatingEmployeesWithNominees(nominees, users);
        nominees.forEach(nominee -> {
            List<EmployeeDocument> docs = documentsService.getDocumentsForReferenceType(nominee.getEmployee().getId(),
                    nominee.getId(), EntityType.NOMINEE.toString(), nominee.getTenantId());
            List<String> stringDocs = docs.stream().map(doc -> doc.getDocument()).collect(Collectors.toList());
            nominee.setDocuments(stringDocs);
        });

        return nominees;
    }

    public List<Nominee> createAsync(NomineeRequest nomineeRequest) throws JsonProcessingException {
        populateDataForSave(nomineeRequest.getNominees(), nomineeRequest.getRequestInfo());

        String nomineeRequestJson = null;
        nomineeRequestJson = mapper.writeValueAsString(nomineeRequest);
        LOGGER.info("nomineeJson::" + nomineeRequestJson);

        employeeProducer.sendMessage(propertiesManager.getSaveNomineeTopic(), propertiesManager.getNomineeSaveKey(),
                nomineeRequestJson);

        return nomineeRequest.getNominees();
    }

    @Transactional
    public void create(NomineeRequest nomineeRequest) {
        List<Nominee> nominees = nomineeRequest.getNominees();
        nomineeRepository.save(nominees);
    }

    public List<Nominee> updateAsync(NomineeRequest nomineeRequest) throws JsonProcessingException {
        populateDataForUpdate(nomineeRequest.getNominees(), nomineeRequest.getRequestInfo());

        String nomineeUpdateRequestJson = null;
        nomineeUpdateRequestJson = mapper.writeValueAsString(nomineeRequest);
        LOGGER.info("nomineeJson update::" + nomineeUpdateRequestJson);

        employeeProducer.sendMessage(propertiesManager.getUpdateNomineeTopic(), propertiesManager.getNomineeUpdateKey(),
                nomineeUpdateRequestJson);
        return nomineeRequest.getNominees();
    }

    @Transactional
    public void update(NomineeRequest nomineeRequest) {
        nomineeRepository.update(nomineeRequest.getNominees());
        documentsService.updateDocumentsForNominee(nomineeRequest.getNominees());
    }

    private void populateDataForSave(List<Nominee> nominees, RequestInfo requestInfo) {
        UserInfo requester = requestInfo.getUserInfo();
        List<Long> sequences = nomineeRepository.generateSequences(nominees.size());
        for (int i = 0; i < nominees.size(); i++) {
            nominees.get(i).setId(sequences.get(i));
            nominees.get(i).setTenantId(nominees.get(i).getTenantId());
            nominees.get(i).setCreatedBy(requester.getId());
            nominees.get(i).setCreatedDate(new Date().getTime());
            nominees.get(i).setLastModifiedBy(requester.getId());
            nominees.get(i).setLastModifiedDate(new Date().getTime());
        }
    }

    private void populateDataForUpdate(List<Nominee> nominees, RequestInfo requestInfo) {
        nominees.forEach(nominee -> {
            nominee.setLastModifiedBy(requestInfo.getUserInfo().getId());
            nominee.setLastModifiedDate(new Date().getTime());
        });
    }

}