package org.egov.user.encryption;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import lombok.extern.slf4j.Slf4j;
import org.egov.user.web.contract.EncReqObject;
import org.egov.user.web.contract.EncryptionRequest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static org.junit.Assert.*;

@Slf4j
public class EncryptionServiceRestInterfaceTest {

    @Mock
    private EncryptionServiceRestInterface encryptionServiceRestInterface;

    private RestTemplate restTemplate;
    private ObjectMapper mapper;

    @Before
    public void initialize() {
        encryptionServiceRestInterface = new EncryptionServiceRestInterface();
        restTemplate = new RestTemplate();
        mapper = new ObjectMapper(new JsonFactory());
    }

    @Test
    public void test() {
        String tenantId = "pb";
        String type = "Normal";

        ObjectNode value = mapper.createObjectNode();
        value.set("asd", new TextNode("asd"));

        EncReqObject encReqObject = new EncReqObject(tenantId, type, value);
        EncryptionRequest encryptionRequest = new EncryptionRequest();
        encryptionRequest.setEncryptionRequests(new ArrayList<EncReqObject>(Collections.singleton(encReqObject)));

        ResponseEntity response = restTemplate.postForEntity("http://localhost:1234/egov-enc-service/crypto/v1/_encrypt",
                encryptionRequest, Object.class);

        response.getBody();

    }




}