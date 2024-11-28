package de.muenchen.oss.oai.pmh.starter.webservice.exceptions;

import de.muenchen.oss.oai.pmh.schema.OaiPmhErrorCodeType;

public class NoRecordsMatchException extends OaiPmhException {
    public static final String DEFAULT_MESSAGE = "The combination of the values of the from, until, set and metadataPrefix arguments results in an empty list.";

    public NoRecordsMatchException() {
        super(DEFAULT_MESSAGE, OaiPmhErrorCodeType.NO_RECORDS_MATCH);
    }

    public NoRecordsMatchException(String message) {
        super(message, OaiPmhErrorCodeType.NO_RECORDS_MATCH);
    }
}
