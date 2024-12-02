package de.muenchen.oss.oai.pmh.exceptions;

import de.muenchen.oss.oai.pmh.schema.OaiPmhErrorCodeType;

public class BadVerbException extends OaiPmhException {
    public static final String DEFAULT_MESSAGE = "Value of the verb argument is not a legal OAI-PMH verb, the verb argument is missing, or the verb argument is repeated.";

    public BadVerbException() {
        super(DEFAULT_MESSAGE, OaiPmhErrorCodeType.BAD_VERB);
    }

    public BadVerbException(String message) {
        super(message, OaiPmhErrorCodeType.BAD_VERB);
    }
}
