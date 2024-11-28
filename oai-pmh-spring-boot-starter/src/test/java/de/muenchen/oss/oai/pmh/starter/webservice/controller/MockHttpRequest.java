package de.muenchen.oss.oai.pmh.starter.webservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.muenchen.oss.oai.pmh.schema.OaiPmhType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.UnsupportedEncodingException;

class MockHttpRequest {

    static final String OAI_PMH_ENDPOINT = "/oai-pmh";

    ResultActions get(MockMvc mockMvc, String url) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.get(url));
    }

    ResultActions post(MockMvc mockMvc, String queryString) throws Exception {
        String requestBody = queryString.startsWith("?") ? queryString.substring(1) : queryString; // remove ? in query string
        return mockMvc.perform(MockMvcRequestBuilders.post(OAI_PMH_ENDPOINT).contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE).content(requestBody));
    }

    OaiPmhType getResponseBody(ResultActions resultActions, ObjectMapper objectMapper) throws UnsupportedEncodingException, JsonProcessingException {
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        return objectMapper.readValue(contentAsString, OaiPmhType.class);
    }

}
