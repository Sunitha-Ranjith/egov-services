package org.egov.wcms.web.contract;

import java.util.ArrayList;
import java.util.List;

import org.egov.common.contract.response.ResponseInfo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProcessInstanceResponse {

    @JsonProperty("responseInfo")
    private ResponseInfo responseInfo = null;

    @JsonProperty("processInstances")
    private List<ProcessInstance> processInstances = new ArrayList<>();

    @JsonProperty("processInstance")
    private ProcessInstance processInstance = null;

}