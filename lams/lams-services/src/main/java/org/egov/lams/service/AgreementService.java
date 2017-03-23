package org.egov.lams.service;

import java.util.List;

import org.egov.lams.contract.AgreementRequest;
import org.egov.lams.model.Agreement;
import org.egov.lams.model.AgreementCriteria;
import org.egov.lams.model.Demand;
import org.egov.lams.model.DemandReason;
import org.egov.lams.producers.AgreementProducer;
import org.egov.lams.repository.AgreementRepository;
import org.egov.lams.repository.DemandRepository;
import org.egov.lams.service.AllotteeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AgreementService {
	public static final Logger logger = LoggerFactory.getLogger(AgreementService.class);

	@Autowired
	private AgreementRepository agreementRepository;
	
	@Autowired
	private AgreementProducer agreementProducer;
	
	@Autowired
	private DemandRepository demandRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private AllotteeService allotteeService;

	public List<Agreement> searchAgreement(AgreementCriteria agreementCriteria) {
		/*
		 * three boolean variables isAgreementNull,isAssetNull and
		 * isAllotteeNull declared to indicate whether criteria arguments for
		 * each of the Agreement,Asset and Allottee objects are given or not.
		 */
		boolean isAgreementNull = agreementCriteria.getAgreementId() == null
				&& agreementCriteria.getAgreementNumber() == null && agreementCriteria.getStatus() == null
				&& (agreementCriteria.getFromDate() == null && agreementCriteria.getToDate() == null)
				&& agreementCriteria.getTenderNumber() == null && agreementCriteria.getTinNumber() == null
				&& agreementCriteria.getTradelicenseNumber() == null && agreementCriteria.getAsset() == null
				&& agreementCriteria.getAllottee() == null;

		boolean isAllotteeNull = agreementCriteria.getAllotteeName() == null
				&& agreementCriteria.getMobilenumber() == null;

		boolean isAssetNull = agreementCriteria.getAssetCategory() == null
				&& agreementCriteria.getShoppingComplexNo() == null && agreementCriteria.getAssetCode() == null
				&& agreementCriteria.getLocality() == null && agreementCriteria.getRevenueWard() == null
				&& agreementCriteria.getElectionWard() == null && agreementCriteria.getTenantId() == null
				&& agreementCriteria.getDoorno() == null;

		if (!isAgreementNull && !isAssetNull && !isAllotteeNull) {
			logger.info("agreementRepository.findByAllotee");
			return agreementRepository.findByAllotee(agreementCriteria);
			
		} else if (!isAgreementNull && isAssetNull && !isAllotteeNull) {
			logger.info("agreementRepository.findByAllotee");
			return agreementRepository.findByAgreementAndAllotee(agreementCriteria);

		} else if (!isAgreementNull && !isAssetNull && isAllotteeNull) {
			logger.info("agreementRepository.findByAgreementAndAsset : both agreement and ");
			return agreementRepository.findByAgreementAndAsset(agreementCriteria);

		} else if ((isAgreementNull && isAssetNull && !isAllotteeNull)
				|| (isAgreementNull && !isAssetNull && !isAllotteeNull)) {
			logger.info("agreementRepository.findByAllotee : only allottee || allotte and asset");
			return agreementRepository.findByAllotee(agreementCriteria);

		} else if (isAgreementNull && !isAssetNull && isAllotteeNull) {
			logger.info("agreementRepository.findByAsset : only asset");
			return agreementRepository.findByAsset(agreementCriteria);

		} else if (!isAgreementNull && isAssetNull && isAllotteeNull) {
			logger.info("agreementRepository.findByAgreement : only agreement");
			return agreementRepository.findByAgreement(agreementCriteria);
		} else {
			// if no values are given for all the three criteria objects
			// (isAgreementNull && isAssetNull && isAllotteeNull)
			logger.info("agreementRepository.findByAgreement : all values null");
			return agreementRepository.findByAgreement(agreementCriteria);
		}
	}
	
	/*
	 * This method is used to create new agreement
	 * 
	 * @return Agreement, return the agreement details with current status
	 * 
	 * @param agreement, hold agreement details 
	 * 
	 * */
	
	public Agreement createAgreement(AgreementRequest agreementRequest){
		
		Agreement agreement = agreementRequest.getAgreement();
		ObjectMapper mapper = new ObjectMapper();
		String agreementValue = null;
	    Long agreementNumber = null;
	    
	    List<DemandReason> demandReasons = demandRepository.getDemandReason(agreementRequest);
	    List<Demand> demands= demandRepository.getDemandList(agreementRequest, demandReasons);
	    demandRepository.createDemand(demands, agreementRequest.getRequestInfo());
	    
		
	    try {
			//TODO put ackno gen service here 
	    	 	agreementNumber=(Long) jdbcTemplate.queryForList("SELECT NEXTVAL('seq_lams_agreement')").get(0).get("nextval");
				agreement.setAgreementNumber(agreementNumber.toString());
	    }catch(Exception ex){
	    		ex.printStackTrace();
	    		throw new RuntimeException(ex.getMessage());
	    }
		try {
				logger.info("createAgreement service::"+agreement);
				agreementValue = mapper.writeValueAsString(agreement);
				logger.info("agreementValue::"+agreementValue);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
				throw new RuntimeException(e.getMessage());
		}
		try {
				//agreementProducer.sendMessage("agreement-save-db", "save-agreement", agreementValue);
		}catch(Exception ex){
				ex.printStackTrace();
				throw new RuntimeException(ex.getMessage());
		}
		return agreement;
	}

	/***
	 * method to update agreementNumber using acknowledgeNumber
	 * 
	 * @param agreement
	 * @return
	 */
	public Agreement updateAgreement(Agreement agreement) {
		ObjectMapper mapper = new ObjectMapper();
		String agreementValue = null;
		Long agreementNumber = null;

		try {
			// TODO put agreement number generator here and change
			agreementNumber = (Long) jdbcTemplate.queryForList("SELECT NEXTVAL('seq_lams_agreement')").get(0)
					.get("nextval");
			agreement.setAgreementNumber(agreementNumber.toString());
		} catch (Exception exception) {
			logger.info(exception.getMessage(),exception);
			throw exception;
		}
		try {
			logger.info("createAgreement service::" + agreement);
			agreementValue = mapper.writeValueAsString(agreement);
			logger.info("agreementValue::" + agreementValue);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		try {
			agreementProducer.sendMessage("workflow-start", "save-agreement", agreementValue);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return agreement;
	}

}
