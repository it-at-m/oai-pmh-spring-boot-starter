package de.muenchen.oss.oai.pmh.starter.webservice.exceptions;

import de.muenchen.oss.oai.pmh.schema.OaiPmhErrorCodeType;

public class IdDoesNotExistException extends OaiPmhException {
    public static final String DEFAULT_MESSAGE = "The value of the identifier argument is unknown or illegal in this repository.";

    public IdDoesNotExistException() {
        super(DEFAULT_MESSAGE, OaiPmhErrorCodeType.ID_DOES_NOT_EXIST);
    }

    public IdDoesNotExistException(String message) {
        super(message, OaiPmhErrorCodeType.ID_DOES_NOT_EXIST);
    }
}
