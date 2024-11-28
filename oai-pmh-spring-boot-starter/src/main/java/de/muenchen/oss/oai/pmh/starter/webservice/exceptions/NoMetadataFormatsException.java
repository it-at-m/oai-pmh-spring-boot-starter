package de.muenchen.oss.oai.pmh.starter.webservice.exceptions;

import de.muenchen.oss.oai.pmh.schema.OaiPmhErrorCodeType;

public class NoMetadataFormatsException extends OaiPmhException {
    public static final String DEFAULT_MESSAGE = "There are no metadata formats available for the specified item.";

    public NoMetadataFormatsException() {
        super(DEFAULT_MESSAGE, OaiPmhErrorCodeType.NO_METADATA_FORMATS);
    }

    public NoMetadataFormatsException(String message) {
        super(message, OaiPmhErrorCodeType.NO_METADATA_FORMATS);
    }
}
