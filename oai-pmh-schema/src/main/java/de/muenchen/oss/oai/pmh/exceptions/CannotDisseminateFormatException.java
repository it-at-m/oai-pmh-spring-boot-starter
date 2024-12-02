package de.muenchen.oss.oai.pmh.exceptions;

import de.muenchen.oss.oai.pmh.schema.OaiPmhErrorCodeType;

public class CannotDisseminateFormatException extends OaiPmhException {
    public static final String DEFAULT_MESSAGE = "The metadata format identified by the value given for the metadataPrefix argument is not supported by the item or by the repository.";

    public CannotDisseminateFormatException() {
        super(DEFAULT_MESSAGE, OaiPmhErrorCodeType.CANNOT_DISSEMINATE_FORMAT);
    }

    public CannotDisseminateFormatException(String message) {
        super(message, OaiPmhErrorCodeType.CANNOT_DISSEMINATE_FORMAT);
    }
}
