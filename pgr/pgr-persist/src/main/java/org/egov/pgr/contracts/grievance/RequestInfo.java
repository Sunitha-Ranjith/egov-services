package org.egov.pgr.contracts.grievance;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestInfo {
    @JsonProperty("api_id")
    private String apiId;

    @JsonProperty("ver")
    private String ver;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", timezone = "IST")
    @JsonProperty("ts")
    private Date ts;

    @JsonProperty("action")
    private String action;

    @JsonProperty("did")
    private String did;

    @JsonProperty("key")
    private String key;

    @JsonProperty("msg_id")
    private String msgId;

    @JsonProperty("requester_id")
    private String requesterId;

    @JsonProperty("auth_token")
    private String authToken;

    @JsonProperty("tenant_id")
    private String tenantId;

    private String correlationId;
}