package org.egov.swm.web.controller;

import javax.validation.Valid;

import org.egov.common.contract.request.RequestInfo;
import org.egov.common.contract.response.ResponseInfo;
import org.egov.swm.domain.model.CollectionPoint;
import org.egov.swm.domain.model.CollectionPointSearch;
import org.egov.swm.domain.model.Pagination;
import org.egov.swm.domain.service.CollectionPointService;
import org.egov.swm.web.requests.CollectionPointRequest;
import org.egov.swm.web.requests.CollectionPointResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/collectionpoints")
public class CollectionPointController {

	@Autowired
	private CollectionPointService collectionPointService;

	@PostMapping("/_create")
	@ResponseStatus(HttpStatus.CREATED)
	public CollectionPointResponse create(@RequestBody @Valid CollectionPointRequest collectionPointRequest) {

		CollectionPointResponse collectionPointResponse = new CollectionPointResponse();
		collectionPointResponse.setResponseInfo(getResponseInfo(collectionPointRequest.getRequestInfo()));

		collectionPointRequest = collectionPointService.create(collectionPointRequest);

		collectionPointResponse.setCollectionPoints(collectionPointRequest.getCollectionPoints());

		return collectionPointResponse;
	}

	@PostMapping("/_update")
	@ResponseStatus(HttpStatus.CREATED)
	public CollectionPointResponse update(@RequestBody @Valid CollectionPointRequest collectionPointRequest) {

		CollectionPointResponse collectionPointResponse = new CollectionPointResponse();
		collectionPointResponse.setResponseInfo(getResponseInfo(collectionPointRequest.getRequestInfo()));

		collectionPointRequest = collectionPointService.update(collectionPointRequest);

		collectionPointResponse.setCollectionPoints(collectionPointRequest.getCollectionPoints());

		return collectionPointResponse;
	}

	@PostMapping("/_search")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public CollectionPointResponse search(@ModelAttribute CollectionPointSearch collectionPointSearch,
			@RequestBody RequestInfo requestInfo, @RequestParam String tenantId) {

		Pagination<CollectionPoint> collectionPointList = collectionPointService.search(collectionPointSearch);

		CollectionPointResponse response = new CollectionPointResponse();
		response.setCollectionPoints(collectionPointList.getPagedData());
		response.setResponseInfo(getResponseInfo(requestInfo));

		return response;

	}

	private ResponseInfo getResponseInfo(RequestInfo requestInfo) {
		return ResponseInfo.builder().apiId(requestInfo.getApiId()).ver(requestInfo.getVer())
				.resMsgId(requestInfo.getMsgId()).resMsgId("placeholder").status("placeholder").build();
	}

}