package de.muenchen.oss.oai.pmh.starter.webservice.controller;

import de.muenchen.oss.oai.pmh.exceptions.OaiPmhException;
import de.muenchen.oss.oai.pmh.schema.OaiPmhErrorType;
import de.muenchen.oss.oai.pmh.schema.OaiPmhType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OaiPmhControllerAdviceTest {

    private final OaiPmhControllerAdvice oaiPmhControllerAdvice = new OaiPmhControllerAdvice();
    private MockHttpServletRequest request;

    @BeforeAll
    void beforeAll() {
        this.request = new MockHttpServletRequest();
        request.setRequestURI("/oai-pmh?verb=Identify&kuchen=sachertorte");
    }

    @ParameterizedTest
    @MethodSource("de.muenchen.oss.oai.pmh.starter.webservice.controller.TestData#getOaiPmhExceptions")
    void exceptionHandlerReturnsHttpStatusCode200(OaiPmhException oaiPmhException) {
        ResponseEntity<OaiPmhType> responseEntity = oaiPmhControllerAdvice.oaiPmhExceptionHandler(this.request, oaiPmhException);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
    }

    @ParameterizedTest
    @MethodSource("de.muenchen.oss.oai.pmh.starter.webservice.controller.TestData#getOaiPmhExceptions")
    void exceptionHandlerReturnsXml(OaiPmhException oaiPmhException) {
        ResponseEntity<OaiPmhType> responseEntity = oaiPmhControllerAdvice.oaiPmhExceptionHandler(this.request, oaiPmhException);
        MediaType responseContentType = responseEntity.getHeaders().getContentType();
        assertThat(responseContentType).isEqualTo(MediaType.TEXT_XML);
    }

    @ParameterizedTest
    @MethodSource("de.muenchen.oss.oai.pmh.starter.webservice.controller.TestData#getOaiPmhExceptions")
    void exceptionHandlerReturnsAnOaiPmhObject(OaiPmhException oaiPmhException) {
        ResponseEntity<OaiPmhType> responseEntity = oaiPmhControllerAdvice.oaiPmhExceptionHandler(this.request, oaiPmhException);
        assertThat(responseEntity.getBody()).isNotNull();

    }

    @ParameterizedTest
    @MethodSource("de.muenchen.oss.oai.pmh.starter.webservice.controller.TestData#getOaiPmhExceptions")
    void exceptionHandlerSetsOnlyOneErrorObject(OaiPmhException oaiPmhException) {
        ResponseEntity<OaiPmhType> responseEntity = oaiPmhControllerAdvice.oaiPmhExceptionHandler(this.request, oaiPmhException);
        List<OaiPmhErrorType> oaiPmhErrorTypeList = Objects.requireNonNull(responseEntity.getBody()).getError();
        assertThat(oaiPmhErrorTypeList).hasSize(1);
    }

    @ParameterizedTest
    @MethodSource("de.muenchen.oss.oai.pmh.starter.webservice.controller.TestData#getOaiPmhExceptions")
    void exceptionHandlerSetsExceptionMessageAndErrorCodeToTheErrorObject(OaiPmhException oaiPmhException) {
        ResponseEntity<OaiPmhType> responseEntity = oaiPmhControllerAdvice.oaiPmhExceptionHandler(this.request, oaiPmhException);
        OaiPmhErrorType oaiPmhErrorType = Objects.requireNonNull(responseEntity.getBody()).getError().getFirst();
        assertThat(oaiPmhErrorType.getValue()).isEqualTo(oaiPmhException.getMessage());
        assertThat(oaiPmhErrorType.getCode()).isEqualTo(oaiPmhException.getErrorCode());
    }

    @ParameterizedTest
    @MethodSource("de.muenchen.oss.oai.pmh.starter.webservice.controller.TestData#getOaiPmhExceptions")
    void exceptionHandlerSetsTheResponseDateInTheOaiPmhObject(OaiPmhException oaiPmhException) {
        ResponseEntity<OaiPmhType> responseEntity = oaiPmhControllerAdvice.oaiPmhExceptionHandler(this.request, oaiPmhException);
        OaiPmhType oaiPmhType = Objects.requireNonNull(responseEntity.getBody());
        assertThat(oaiPmhType.getResponseDate()).isNotNull();
    }

    @ParameterizedTest
    @MethodSource("de.muenchen.oss.oai.pmh.starter.webservice.controller.TestData#getOaiPmhExceptions")
    void exceptionHandlerSetsTheBaseUrlInTheOaiPmhObject(OaiPmhException oaiPmhException) {
        ResponseEntity<OaiPmhType> responseEntity = oaiPmhControllerAdvice.oaiPmhExceptionHandler(this.request, oaiPmhException);
        OaiPmhType oaiPmhType = Objects.requireNonNull(responseEntity.getBody());
        String baseUrl = oaiPmhType.getRequest().getValue();
        assertThat(baseUrl).isEqualTo(this.request.getRequestURL().toString());
    }

    @ParameterizedTest
    @MethodSource("de.muenchen.oss.oai.pmh.starter.webservice.controller.TestData#getOaiPmhExceptions")
    void requestSpecificElementsInOaiPmhObjectsAreNotAllowedInCaseOfAnError(OaiPmhException oaiPmhException) {
        ResponseEntity<OaiPmhType> responseEntity = oaiPmhControllerAdvice.oaiPmhExceptionHandler(this.request, oaiPmhException);
        OaiPmhType oaiPmhType = Objects.requireNonNull(responseEntity.getBody());
        assertThat(oaiPmhType.getIdentify()).isNull();
        assertThat(oaiPmhType.getGetRecord()).isNull();
        assertThat(oaiPmhType.getListMetadataFormats()).isNull();
        assertThat(oaiPmhType.getListIdentifiers()).isNull();
        assertThat(oaiPmhType.getListRecords()).isNull();
        assertThat(oaiPmhType.getListSets()).isNull();
    }
}
