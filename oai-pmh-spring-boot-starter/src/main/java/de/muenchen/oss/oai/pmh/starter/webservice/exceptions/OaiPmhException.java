package de.muenchen.oss.oai.pmh.starter.webservice.exceptions;

import de.muenchen.oss.oai.pmh.schema.OaiPmhErrorCodeType;
import lombok.Getter;

@Getter
public class OaiPmhException extends RuntimeException {
    private final OaiPmhErrorCodeType errorCode;

    public OaiPmhException(String message, OaiPmhErrorCodeType errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
