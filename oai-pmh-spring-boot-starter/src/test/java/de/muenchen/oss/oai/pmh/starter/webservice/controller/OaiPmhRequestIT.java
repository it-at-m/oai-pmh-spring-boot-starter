package de.muenchen.oss.oai.pmh.starter.webservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.muenchen.oss.oai.pmh.schema.*;
import de.muenchen.oss.oai.pmh.starter.configuration.jackson.JacksonConfiguration;
import de.muenchen.oss.oai.pmh.starter.webservice.RequestProcessorImplementation;
import de.muenchen.oss.oai.pmh.starter.webservice.schema.request.Argument;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Import({JacksonConfiguration.class, RequestProcessorImplementation.class})
@AutoConfigureMockMvc
class OaiPmhRequestIT {

    protected final MockHttpRequest mockHttpRequest = new MockHttpRequest();

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected MappingJackson2XmlHttpMessageConverter mappingJackson2XmlHttpMessageConverter;

    protected ObjectMapper objectMapper;

    private static Map<String, String> parseQueryString(String queryString) {
        Map<String, String> queryParametersMap = new HashMap<>(10);
        queryString = queryString.substring(1); // remove ? character in query string
        String[] parameters = queryString.split("&");

        for (String parameter : parameters) {
            String[] keyValue = parameter.split("=");
            queryParametersMap.put(keyValue[0], keyValue[1]);
        }
        return queryParametersMap;
    }

    @BeforeAll
    protected void beforeAll() {
        this.objectMapper = this.mappingJackson2XmlHttpMessageConverter.getObjectMapper();
    }

    @ParameterizedTest
    @MethodSource("de.muenchen.oss.oai.pmh.starter.webservice.controller.TestData#getVerbTypesAndIllegalArguments")
    void requestWithIllegalArgumentsThrowsBadArgumentException(VerbType verb, List<Argument> illegalArguments) throws Exception {
        for (Argument argument : illegalArguments) {
            String queryString = "?verb=" + verb.value() + "&" + argument.value() + "=value";
            OaiPmhType oaiPmhType = this.performGetRequest(MockHttpRequest.OAI_PMH_ENDPOINT + queryString);
            this.assertThatBadArgumentErrorIsPresent(oaiPmhType);
            oaiPmhType = performPostRequest(queryString);
            this.assertThatBadArgumentErrorIsPresent(oaiPmhType);
        }
    }

    @Test
    void requestWithUnknownArgumentThrowsBadArgumentException() throws Exception {
        String queryString = "?verb=Identify&a=b";
        OaiPmhType oaiPmhType = this.performGetRequest(MockHttpRequest.OAI_PMH_ENDPOINT + queryString);
        this.assertThatBadArgumentErrorIsPresent(oaiPmhType);
        oaiPmhType = performPostRequest(queryString);
        this.assertThatBadArgumentErrorIsPresent(oaiPmhType);
    }

    @ParameterizedTest
    @MethodSource("de.muenchen.oss.oai.pmh.starter.webservice.controller.TestData#getLegalQueryStrings")
    void requestWithLegalArgumentsReturnsAnOaiPmhObjectWithZeroErrors(String queryString) throws Exception {
        OaiPmhType oaiPmhType = this.performGetRequest(MockHttpRequest.OAI_PMH_ENDPOINT + queryString);
        assertThat(oaiPmhType.getError()).isEmpty();
        oaiPmhType = performPostRequest(queryString);
        assertThat(oaiPmhType.getError()).isEmpty();
    }

    @ParameterizedTest
    @MethodSource("de.muenchen.oss.oai.pmh.starter.webservice.controller.TestData#getLegalQueryStrings")
    void requestWithLegalArgumentsReturnsAnOaiPmhObjectWithResponseDateElement(String queryString) throws Exception {
        OaiPmhType oaiPmhType = this.performGetRequest(MockHttpRequest.OAI_PMH_ENDPOINT + queryString);
        assertThat(oaiPmhType.getResponseDate()).isNotNull();
        oaiPmhType = performPostRequest(queryString);
        assertThat(oaiPmhType.getResponseDate()).isNotNull();
    }

    @ParameterizedTest
    @MethodSource("de.muenchen.oss.oai.pmh.starter.webservice.controller.TestData#getLegalQueryStrings")
    void requesWithLegalArgumentsReturnsAnOaiPmhObjectWithRequestTypeElement(String queryString) throws Exception {
        OaiPmhType oaiPmhType = this.performGetRequest(MockHttpRequest.OAI_PMH_ENDPOINT + queryString);
        RequestType requestType = oaiPmhType.getRequest();
        assertThatRequestTypeHoldesQueryStringValues(requestType, queryString);
        oaiPmhType = performPostRequest(queryString);
        requestType = oaiPmhType.getRequest();
        assertThatRequestTypeHoldesQueryStringValues(requestType, queryString);
    }

    @ParameterizedTest
    @MethodSource("de.muenchen.oss.oai.pmh.starter.webservice.controller.TestData#getQueryStringsWithRepeatedVerbArgument")
    void requestWithRepeatedVerbArgumentThrowsBadVerbException(String queryString) throws Exception {
        OaiPmhType oaiPmhType = this.performGetRequest(MockHttpRequest.OAI_PMH_ENDPOINT + queryString);
        this.assertThatBadVerbErrorIsPresent(oaiPmhType);
        oaiPmhType = performPostRequest(queryString);
        this.assertThatBadVerbErrorIsPresent(oaiPmhType);

    }

    @Test
    void requestWithMissingVerbArgumentThrowsBadVerbException() throws Exception {
        OaiPmhType oaiPmhType = this.performGetRequest(MockHttpRequest.OAI_PMH_ENDPOINT);
        this.assertThatBadVerbErrorIsPresent(oaiPmhType);
        oaiPmhType = performPostRequest("");
        this.assertThatBadVerbErrorIsPresent(oaiPmhType);
    }

    @Test
    void requestWithUnknownVerbArgumentThrowsBadVerbException() throws Exception {
        String queryString = "?verb=UnknownVerb";
        OaiPmhType oaiPmhType = this.performGetRequest(MockHttpRequest.OAI_PMH_ENDPOINT + queryString);
        this.assertThatBadVerbErrorIsPresent(oaiPmhType);
        oaiPmhType = performPostRequest(queryString);
        this.assertThatBadVerbErrorIsPresent(oaiPmhType);
    }

    @ParameterizedTest
    @MethodSource("de.muenchen.oss.oai.pmh.starter.webservice.controller.TestData#getIllegalQueryStringsWithExclusiveArgument")
    void requestWithExclusiveArgumentThrowsExceptionIfOtherArgumentsArePresent(String queryString) throws Exception {
        OaiPmhType oaiPmhType = this.performGetRequest(MockHttpRequest.OAI_PMH_ENDPOINT + queryString);
        assertThatBadArgumentErrorIsPresent(oaiPmhType);
        oaiPmhType = performPostRequest(queryString);
        assertThatBadArgumentErrorIsPresent(oaiPmhType);
    }

    @ParameterizedTest
    @MethodSource("de.muenchen.oss.oai.pmh.starter.webservice.controller.TestData#getQueryStringsWithMissingRequiredArguments")
    void requestWithMissingRequiredArgumentsThrowsBadArgumentException(String queryString) throws Exception {
        OaiPmhType oaiPmhType = performGetRequest(MockHttpRequest.OAI_PMH_ENDPOINT + queryString);
        assertThatBadArgumentErrorIsPresent(oaiPmhType);
        oaiPmhType = performPostRequest(queryString);
        assertThatBadArgumentErrorIsPresent(oaiPmhType);
    }

    @ParameterizedTest
    @MethodSource("de.muenchen.oss.oai.pmh.starter.webservice.controller.TestData#getLegalListMetadataFormatsQueryStrings")
    void requestReturnsOaiPmhObjectWithListMetadataFormatsElement(String queryString) throws Exception {
        OaiPmhType oaiPmhType = performGetRequest(MockHttpRequest.OAI_PMH_ENDPOINT + queryString);
        assertThat(oaiPmhType.getListMetadataFormats()).isNotNull();
        oaiPmhType = performPostRequest(queryString);
        assertThat(oaiPmhType.getListMetadataFormats()).isNotNull();
    }

    @ParameterizedTest
    @MethodSource("de.muenchen.oss.oai.pmh.starter.webservice.controller.TestData#getLegalGetRecordQueryStrings")
    void requestReturnsOaiPmhObjectWithGetRecordElement(String queryString) throws Exception {
        OaiPmhType oaiPmhType = performGetRequest(MockHttpRequest.OAI_PMH_ENDPOINT + queryString);
        assertThat(oaiPmhType.getGetRecord()).isNotNull();
        oaiPmhType = performPostRequest(queryString);
        assertThat(oaiPmhType.getGetRecord()).isNotNull();
    }

    @ParameterizedTest
    @MethodSource("de.muenchen.oss.oai.pmh.starter.webservice.controller.TestData#getLegalIdentifyQueryStrings")
    void requestReturnsOaiPmhObjectWithIdentifyElement(String queryString) throws Exception {
        OaiPmhType oaiPmhType = performGetRequest(MockHttpRequest.OAI_PMH_ENDPOINT + queryString);
        assertThat(oaiPmhType.getIdentify()).isNotNull();
        oaiPmhType = performPostRequest(queryString);
        assertThat(oaiPmhType.getIdentify()).isNotNull();
    }

    @ParameterizedTest
    @MethodSource("de.muenchen.oss.oai.pmh.starter.webservice.controller.TestData#getLegalListIdentifiersQueryStrings")
    void requestReturnsOaiPmhObjectWithListIdentifiersElement(String queryString) throws Exception {
        OaiPmhType oaiPmhType = performGetRequest(MockHttpRequest.OAI_PMH_ENDPOINT + queryString);
        assertThat(oaiPmhType.getListIdentifiers()).isNotNull();
        oaiPmhType = performPostRequest(queryString);
        assertThat(oaiPmhType.getListIdentifiers()).isNotNull();
    }

    @ParameterizedTest
    @MethodSource("de.muenchen.oss.oai.pmh.starter.webservice.controller.TestData#getLegalListRecordsQueryStrings")
    void requestReturnsOaiPmhObjectWithListRecordsElement(String queryString) throws Exception {
        OaiPmhType oaiPmhType = performGetRequest(MockHttpRequest.OAI_PMH_ENDPOINT + queryString);
        assertThat(oaiPmhType.getListRecords()).isNotNull();
        oaiPmhType = performPostRequest(queryString);
        assertThat(oaiPmhType.getListRecords()).isNotNull();
    }

    @ParameterizedTest
    @MethodSource("de.muenchen.oss.oai.pmh.starter.webservice.controller.TestData#getLegalListSetsQueryStrings")
    void requestReturnsOaiPmhObjectWithListSetsElement(String queryString) throws Exception {
        OaiPmhType oaiPmhType = performGetRequest(MockHttpRequest.OAI_PMH_ENDPOINT + queryString);
        assertThat(oaiPmhType.getListSets()).isNotNull();
        oaiPmhType = performPostRequest(queryString);
        assertThat(oaiPmhType.getListSets()).isNotNull();
    }

    protected void assertThatBadArgumentErrorIsPresent(OaiPmhType oaiPmhType) {
        List<OaiPmhErrorType> oaiPmhErrorTypes = oaiPmhType.getError();
        assertThat(oaiPmhErrorTypes.getFirst().getCode()).isEqualTo(OaiPmhErrorCodeType.BAD_ARGUMENT);
    }

    private void assertThatStatusCodeIs200(ResultActions resultActions) throws Exception {
        resultActions.andExpect(status().isOk());
    }

    private void assertThatContentTypeIsXML(ResultActions resultActions) throws Exception {
        resultActions.andExpect(content().contentType(MediaType.TEXT_XML_VALUE));
    }

    private void assertThatBadVerbErrorIsPresent(OaiPmhType oaiPmhType) {
        List<OaiPmhErrorType> oaiPmhErrorTypes = oaiPmhType.getError();
        assertThat(oaiPmhErrorTypes).hasSize(1);
        assertThat(oaiPmhErrorTypes.getFirst().getCode()).isEqualTo(OaiPmhErrorCodeType.BAD_VERB);
    }

    private void assertThatRequestTypeHoldesQueryStringValues(RequestType requestType, String queryString) {
        Map<String, String> queryParametersMap = parseQueryString(queryString);

        assertThat(requestType.getResumptionToken()).isEqualTo(queryParametersMap.get(Argument.RESUMPTION_TOKEN.value()));
        assertThat(requestType.getMetadataPrefix()).isEqualTo(queryParametersMap.get(Argument.METADATA_PREFIX.value()));
        assertThat(requestType.getSet()).isEqualTo(queryParametersMap.get(Argument.SET.value()));
        assertThat(requestType.getUntil()).isEqualTo(queryParametersMap.get(Argument.UNTIL.value()));
        assertThat(requestType.getIdentifier()).isEqualTo(queryParametersMap.get(Argument.IDENTIFIER.value()));
        assertThat(requestType.getFrom()).isEqualTo(queryParametersMap.get(Argument.FROM.value()));
        assertThat(requestType.getVerb()).isEqualTo(VerbType.fromValue(queryParametersMap.get(Argument.VERB.value())));
    }

    protected OaiPmhType performGetRequest(String url) throws Exception {
        ResultActions resultActions = mockHttpRequest.get(this.mockMvc, url);
        return getResponseBody(resultActions);
    }

    protected OaiPmhType performPostRequest(String queryString) throws Exception {
        ResultActions resultActions = mockHttpRequest.post(this.mockMvc, queryString);
        return getResponseBody(resultActions);
    }

    private OaiPmhType getResponseBody(ResultActions resultActions) throws Exception {
        this.assertThatStatusCodeIs200(resultActions);
        this.assertThatContentTypeIsXML(resultActions);
        return mockHttpRequest.getResponseBody(resultActions, this.objectMapper);
    }
}
