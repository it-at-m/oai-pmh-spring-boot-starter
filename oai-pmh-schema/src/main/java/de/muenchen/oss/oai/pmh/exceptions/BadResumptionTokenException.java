package de.muenchen.oss.oai.pmh.exceptions;

import de.muenchen.oss.oai.pmh.schema.OaiPmhErrorCodeType;

public class BadResumptionTokenException extends OaiPmhException {
    public static final String DEFAULT_MESSAGE = "The value of the resumptionToken argument is invalid or expired.";

    public BadResumptionTokenException() {
        super(DEFAULT_MESSAGE, OaiPmhErrorCodeType.BAD_RESUMPTION_TOKEN);
    }

    public BadResumptionTokenException(String message) {
        super(message, OaiPmhErrorCodeType.BAD_RESUMPTION_TOKEN);
    }
}
