package de.muenchen.oss.oai.pmh.exceptions;

import de.muenchen.oss.oai.pmh.schema.OaiPmhErrorCodeType;

public class BadArgumentException extends OaiPmhException {
    public static final String DEFAULT_MESSAGE = "The request includes illegal arguments, is missing required arguments, includes a repeated argument, or values for arguments have an illegal syntax.";

    public BadArgumentException() {
        super(DEFAULT_MESSAGE, OaiPmhErrorCodeType.BAD_ARGUMENT);
    }

    public BadArgumentException(String message) {
        super(message, OaiPmhErrorCodeType.BAD_ARGUMENT);
    }
}
