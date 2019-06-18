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

package org.egov.mseva.service;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.egov.common.contract.request.RequestInfo;
import org.egov.mseva.config.PropertiesManager;
import org.egov.mseva.model.AuditDetails;
import org.egov.mseva.model.LastLoginDetails;
import org.egov.mseva.model.RecepientEvent;
import org.egov.mseva.model.lltWrapper;
import org.egov.mseva.producer.MsevaEventsProducer;
import org.egov.mseva.repository.MsevaRepository;
import org.egov.mseva.utils.ResponseInfoFactory;
import org.egov.mseva.web.contract.Event;
import org.egov.mseva.web.contract.EventRequest;
import org.egov.mseva.web.contract.EventResponse;
import org.egov.mseva.web.contract.EventSearchCriteria;
import org.egov.mseva.web.contract.NotificationCountResponse;
import org.egov.mseva.web.validator.MsevaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@Service
public class MsevaService {
	
	@Autowired
	private PropertiesManager properties;
	
	@Autowired
	private MsevaEventsProducer producer;
	
	@Autowired
	private ResponseInfoFactory responseInfo;
	
	@Autowired
	private MsevaValidator validator;
	
	@Autowired
	private MsevaRepository repository;
	
	public EventResponse createEvents(EventRequest request) {
		validator.validateCreateEvent(request);
		log.info("enriching and storing the event......");
		enrichCreateEvent(request);
		producer.push(properties.getSaveEventsTopic(), request);
		
		return EventResponse.builder()
				.responseInfo(responseInfo.createResponseInfoFromRequestInfo(request.getRequestInfo(), true))
				.events(request.getEvents()).build();
	}
	
	public EventResponse updateEvents(EventRequest request) {
		validator.validateUpdateEvent(request);
		log.info("enriching and updating the event......");
		enrichUpdateEvent(request);
		producer.push(properties.getUpdateEventsTopic(), request);
		
		return EventResponse.builder()
				.responseInfo(responseInfo.createResponseInfoFromRequestInfo(request.getRequestInfo(), true))
				.events(request.getEvents()).build();
	}
	
	public EventResponse searchEvents(RequestInfo requestInfo, EventSearchCriteria criteria) {
		validator.validateSearch(requestInfo, criteria);
		log.info("Searching events......");
		enrichSearchCriteria(requestInfo, criteria);
		List<Event> events = repository.fetchEvents(criteria);
		
		LastLoginDetails loginDetails = LastLoginDetails.builder()
				.userId(requestInfo.getUserInfo().getUuid()).lastLoginTime(new Date().getTime()).build();
		lltWrapper wrapper = lltWrapper.builder().lastLoginDetails(loginDetails).build();
		producer.push(properties.getLltDetailsTopic(), wrapper);
		
		
		return EventResponse.builder()
				.responseInfo(responseInfo.createResponseInfoFromRequestInfo(requestInfo, true))
				.events(events).build();
	}
	
	public NotificationCountResponse fetchCount(RequestInfo requestInfo, EventSearchCriteria criteria) {
		validator.validateSearch(requestInfo, criteria);
		enrichSearchCriteria(requestInfo, criteria);
		Long count = repository.fetchCount(criteria);
		
		return NotificationCountResponse.builder()
				.responseInfo(responseInfo.createResponseInfoFromRequestInfo(requestInfo, true))
				.count(count).build();
	}
	
	private void enrichCreateEvent(EventRequest request) {
		request.getEvents().forEach(event -> {
			if(null != event.getActions()) {
				event.setId(UUID.randomUUID().toString());
				event.getActions().setId(UUID.randomUUID().toString());
				event.getActions().setEventId(event.getId());
				event.getActions().setTenantId(event.getTenantId());
			}
			if(null != event.getEventDetails()) {
				event.getEventDetails().setId(UUID.randomUUID().toString());
				event.getEventDetails().setEventId(event.getId());
			}
			List<RecepientEvent> recepientEventList = new ArrayList<>();
			manageRecepients(event, recepientEventList);
			event.setRecepientEventMap(recepientEventList);
			
			AuditDetails auditDetails = AuditDetails.builder().createdBy(request.getRequestInfo().getUserInfo().getUuid())
					.createdTime(new Date().getTime())
					.lastModifiedBy(request.getRequestInfo().getUserInfo().getUuid())
					.lastModifiedTime(new Date().getTime())
					.build();
			
			event.setAuditDetails(auditDetails);

		});
	}
	
	private void enrichUpdateEvent(EventRequest request) {
		request.getEvents().forEach(event -> {	
			if(null != event.getActions()) {
				if(StringUtils.isEmpty(event.getActions().getId())) {
					event.setId(UUID.randomUUID().toString());
					event.getActions().setId(UUID.randomUUID().toString());
					event.getActions().setEventId(event.getId());
					event.getActions().setTenantId(event.getTenantId());
				}
			}
			if(null != event.getEventDetails()) {
				if(StringUtils.isEmpty(event.getEventDetails().getId())) {
					event.getEventDetails().setId(UUID.randomUUID().toString());
					event.getEventDetails().setEventId(event.getId());
				}
			}
			List<RecepientEvent> recepientEventList = new ArrayList<>();
			manageRecepients(event, recepientEventList);
			event.setRecepientEventMap(recepientEventList);
			
			AuditDetails auditDetails = event.getAuditDetails();
			auditDetails.setLastModifiedBy(request.getRequestInfo().getUserInfo().getUuid());
			auditDetails.setLastModifiedTime(new Date().getTime());
			
			event.setAuditDetails(auditDetails);

		});
	}
	
	
	private void enrichSearchCriteria(RequestInfo requestInfo, EventSearchCriteria criteria ) {
		if(requestInfo.getUserInfo().getType().equals("CITIZEN")) {
			if(!CollectionUtils.isEmpty(criteria.getUserids()))
				criteria.getUserids().clear();
			if(!CollectionUtils.isEmpty(criteria.getRoles()))
				criteria.getRoles().clear();

			List<String> userIds = new ArrayList<>();
			List<String> roles = new ArrayList<>();
			userIds.add(requestInfo.getUserInfo().getUuid());
			roles.add("CITIZEN");
			criteria.setUserids(userIds); criteria.setRoles(roles); 
			criteria.setTenantId(requestInfo.getUserInfo().getTenantId());
		}
		if(!CollectionUtils.isEmpty(criteria.getUserids()) || !CollectionUtils.isEmpty(criteria.getRoles()))
			buildRecepientListForSearch(criteria);
	}
	
	
	private void buildRecepientListForSearch(EventSearchCriteria criteria) {
		List<String> recepients = new ArrayList<>();
		if(!CollectionUtils.isEmpty(criteria.getUserids()))
			criteria.getUserids().forEach( user -> recepients.add(user));
		
		if(!CollectionUtils.isEmpty(criteria.getRoles())) {
			criteria.getRoles().forEach(role -> {
				recepients.add(role + "|*");
				recepients.add(role + "|" + criteria.getTenantId());
			});
			recepients.add("*|"+criteria.getTenantId());
			recepients.add("All");
		}
	}
	
	
	private void manageRecepients(Event event, List<RecepientEvent> recepientEventList) {
		
		if(!CollectionUtils.isEmpty(event.getRecepient().getToUsers())) {
			event.getRecepient().getToRoles().clear();
		} //toUsers will take precedence over toRoles.
		
		if(!CollectionUtils.isEmpty(event.getRecepient().getToUsers())) {
			event.getRecepient().getToUsers().forEach(user -> {
				RecepientEvent rcpntevent = RecepientEvent.builder().recepient(user).eventId(event.getId()).build();
				recepientEventList.add(rcpntevent);
			});
		}
		if(!CollectionUtils.isEmpty(event.getRecepient().getToRoles())) {
			event.getRecepient().getToRoles().forEach(role -> {
				RecepientEvent rcpntevent = RecepientEvent.builder().recepient(role).eventId(event.getId()).build();
				recepientEventList.add(rcpntevent);
			});
		}
	}
	
}