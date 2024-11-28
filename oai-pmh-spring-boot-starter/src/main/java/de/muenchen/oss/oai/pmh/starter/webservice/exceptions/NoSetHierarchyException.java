package de.muenchen.oss.oai.pmh.starter.webservice.exceptions;

import de.muenchen.oss.oai.pmh.schema.OaiPmhErrorCodeType;

public class NoSetHierarchyException extends OaiPmhException {
    public static final String DEFAULT_MESSAGE = "The repository does not support sets.";

    public NoSetHierarchyException() {
        super(DEFAULT_MESSAGE, OaiPmhErrorCodeType.NO_SET_HIERARCHY);
    }

    public NoSetHierarchyException(String message) {
        super(message, OaiPmhErrorCodeType.NO_SET_HIERARCHY);
    }
}
