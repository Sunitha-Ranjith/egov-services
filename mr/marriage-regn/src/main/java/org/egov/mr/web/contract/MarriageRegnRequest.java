package org.egov.mr.web.contract;

import javax.validation.Valid;

import org.egov.common.contract.request.RequestInfo;
import org.egov.mr.model.MarriageRegn;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Builder
public class MarriageRegnRequest {
	@JsonProperty("RequestInfo")
	@Valid
	private RequestInfo requestInfo = null;

	@JsonProperty("MarriageRegn")
	@Valid
	private MarriageRegn marriageRegn = null;
}
