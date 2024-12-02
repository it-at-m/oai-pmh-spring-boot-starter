package de.muenchen.oss.oai.pmh.starter.webservice.controller;

import de.muenchen.oss.oai.pmh.exceptions.OaiPmhException;
import de.muenchen.oss.oai.pmh.schema.OaiPmhType;
import de.muenchen.oss.oai.pmh.starter.webservice.schema.response.OaiPmhTypeFactory;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OaiPmhControllerAdvice {

    @ExceptionHandler(OaiPmhException.class)
    public ResponseEntity<OaiPmhType> oaiPmhExceptionHandler(HttpServletRequest request, OaiPmhException oaiPmhException) {
        OaiPmhType oaiPmhType = OaiPmhTypeFactory.createOaiPmhErrorType(request, oaiPmhException);
        oaiPmhType.setResponseDateToCurrentDate();
        return ResponseEntity.ok().contentType(MediaType.TEXT_XML).body(oaiPmhType);
    }
}
