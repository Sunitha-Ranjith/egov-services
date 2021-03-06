/**
 * NOTE: This class is auto generated by the swagger code generator program (2.2.3).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package org.egov.works.workorder.web.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.egov.tracer.model.ErrorRes;
import org.egov.works.workorder.web.contract.NoticeRequest;
import org.egov.works.workorder.web.contract.NoticeResponse;
import org.egov.works.workorder.web.contract.RequestInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-16T05:39:15.252Z")

@Api(value = "notices", description = "the notices API")
public interface NoticesApi {

    @ApiOperation(value = "Create new Notice(s).", notes = "To create new Notice in the system. API supports bulk creation with max limit as defined in the Notice Request. Please note that either whole batch succeeds or fails, there's no partial batch success. To create one Notice, please pass array with one Notice object.  Notice can only created for approved Work Order and after setting offline status to Letter of Acceptance. ", response = NoticeResponse.class, tags = {
            "Notice", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Notice(s) created successfully", response = NoticeResponse.class),
            @ApiResponse(code = 400, message = "Notice(s) creation failed", response = ErrorRes.class) })

    @RequestMapping(value = "/notices/_create", method = RequestMethod.POST)
    ResponseEntity<NoticeResponse> noticesCreatePost(
            @ApiParam(value = "Details of new Notice(s) + RequestInfo meta data.", required = true) @Valid @RequestBody NoticeRequest noticeRequest);

    @ApiOperation(value = "Get the list of Notice(s) defined in the system.", notes = "Search and get Notice(s) based on defined search criteria. Currently search parameters are only allowed as HTTP query params.  In case multiple parameters are passed Notice(s) will be searched as an AND combination of all the parameters.  Maximum result size is restricted based on the maxlength of Notice as defined in NoticeResponse model.  Search results will be sorted by the sortProperty Provided in the parameters ", response = NoticeResponse.class, tags = {
            "Notice", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Notice(s) Retrieved Successfully", response = NoticeResponse.class),
            @ApiResponse(code = 400, message = "Invalid input.", response = ErrorRes.class) })

    @RequestMapping(value = "/notices/_search", method = RequestMethod.POST)
    ResponseEntity<NoticeResponse> noticesSearchPost(
            @NotNull @ApiParam(value = "Unique id for a tenant.", required = true) @RequestParam(value = "tenantId", required = true) String tenantId,
            @ApiParam(value = "Parameter to carry Request metadata in the request body") @Valid @RequestBody RequestInfo requestInfo,
            @Min(0) @Max(100) @ApiParam(value = "Number of records returned.", defaultValue = "20") @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,
            @ApiParam(value = "Page number", defaultValue = "1") @RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber,
            @ApiParam(value = "This takes any field from the Object seperated by comma and asc,desc keywords. example name asc,code desc or name,code or name,code desc", defaultValue = "id") @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
            @Size(max = 50) @ApiParam(value = "Comma separated list of Work Order Numbers") @RequestParam(value = "workOrderNumbers", required = false) List<String> workOrderNumbers,
            @Size(max = 50) @ApiParam(value = "Comma separated list of Ids of Notice(s)") @RequestParam(value = "ids", required = false) List<String> ids,
            @Size(max = 50) @ApiParam(value = "Comma separated list of LOA Numbers") @RequestParam(value = "loaNumbers", required = false) List<String> loaNumbers,
            @Size(max = 50) @ApiParam(value = "Comma separated list of Detailed Estimate Numbers") @RequestParam(value = "detailedEstimateNumbers", required = false) List<String> detailedEstimateNumbers,
            @Size(max = 50) @ApiParam(value = "Comma separated list of Work Identification Numbers") @RequestParam(value = "workIdentificationNumbers", required = false) List<String> workIdentificationNumbers,
            @Size(max = 50) @ApiParam(value = "Comma separated list of Names of the contractor to whom Notice is issued") @RequestParam(value = "contractorNames", required = false) List<String> contractorNames,
            @Size(max = 50) @ApiParam(value = "Comma separated list of codes of the contractor to whom Notice is issued") @RequestParam(value = "contractorCodes", required = false) List<String> contractorCodes,
            @Size(max = 50) @ApiParam(value = "Comma separated list of the Notice Status") @RequestParam(value = "statuses", required = false) List<String> statuses,
            @ApiParam(value = "Matches exact or like string given regardless of case-sensitive.") @RequestParam(value = "workOrderNumberLike", required = false) String workOrderNumberLike,
            @ApiParam(value = "Matches exact or like string given regardless of case-sensitive.") @RequestParam(value = "loaNumberLike", required = false) String loaNumberLike,
            @ApiParam(value = "Matches exact or like string given regardless of case-sensitive.") @RequestParam(value = "detailedEstimateNumberLike", required = false) String detailedEstimateNumberLike,
            @ApiParam(value = "Matches exact or like string given regardless of case-sensitive.") @RequestParam(value = "workIdentificationNumberLike", required = false) String workIdentificationNumberLike,
            @ApiParam(value = "Matches exact or like string given regardless of case-sensitive.") @RequestParam(value = "noticeNumberLike", required = false) String noticeNumberLike,
            @Size(max = 50) @ApiParam(value = "Comma separated list of Detailed Estimate Numbers") @RequestParam(value = "noticeNumbers", required = false) List<String> noticeNumbers,
            @ApiParam(value = "Matches exact or like string given regardless of case-sensitive.") @RequestParam(value = "contractorCodeLike", required = false) String contractorCodeLike,
            @ApiParam(value = "Matches exact or like string given regardless of case-sensitive.") @RequestParam(value = "contractorNameLike", required = false) String contractorNameLike);

    @ApiOperation(value = "Update existing Notice(s).", notes = "To update existing Notice in the system. API supports bulk updation with max limit as defined in the Notice Request. Please note that either whole batch succeeds or fails, there's no partial batch success. To update one Notice, please pass array with one Notice object. ", response = NoticeResponse.class, tags = {
            "Notice", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Notice(s) updated successfully", response = NoticeResponse.class),
            @ApiResponse(code = 400, message = "Notice(s) updation failed", response = ErrorRes.class) })

    @RequestMapping(value = "/notices/_update", method = RequestMethod.POST)
    ResponseEntity<NoticeResponse> noticesUpdatePost(
            @ApiParam(value = "Details of Notice(s) + RequestInfo meta data.", required = true) @Valid @RequestBody NoticeRequest noticeRequest);

}