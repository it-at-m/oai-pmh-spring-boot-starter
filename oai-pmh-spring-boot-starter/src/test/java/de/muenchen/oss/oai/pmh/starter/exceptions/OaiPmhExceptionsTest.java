package de.muenchen.oss.oai.pmh.starter.exceptions;

import de.muenchen.oss.oai.pmh.starter.webservice.exceptions.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class OaiPmhExceptionsTest {

    @Test
    void testType() {
        assertInstanceOf(RuntimeException.class, new OaiPmhException("", null));
        assertInstanceOf(OaiPmhException.class, new BadArgumentException(""));
        assertInstanceOf(OaiPmhException.class, new BadResumptionTokenException(""));
        assertInstanceOf(OaiPmhException.class, new BadVerbException(""));
        assertInstanceOf(OaiPmhException.class, new CannotDisseminateFormatException(""));
        assertInstanceOf(OaiPmhException.class, new IdDoesNotExistException(""));
        assertInstanceOf(OaiPmhException.class, new NoMetadataFormatsException(""));
        assertInstanceOf(OaiPmhException.class, new NoRecordsMatchException(""));
        assertInstanceOf(OaiPmhException.class, new NoSetHierarchyException(""));
    }

    @Test
    void testDefaultMessage() {
        assertEquals(BadArgumentException.DEFAULT_MESSAGE, new BadArgumentException().getMessage());
        assertEquals(BadResumptionTokenException.DEFAULT_MESSAGE, new BadResumptionTokenException().getMessage());
        assertEquals(BadVerbException.DEFAULT_MESSAGE, new BadVerbException().getMessage());
        assertEquals(CannotDisseminateFormatException.DEFAULT_MESSAGE, new CannotDisseminateFormatException().getMessage());
        assertEquals(IdDoesNotExistException.DEFAULT_MESSAGE, new IdDoesNotExistException().getMessage());
        assertEquals(NoMetadataFormatsException.DEFAULT_MESSAGE, new NoMetadataFormatsException().getMessage());
        assertEquals(NoRecordsMatchException.DEFAULT_MESSAGE, new NoRecordsMatchException().getMessage());
        assertEquals(NoSetHierarchyException.DEFAULT_MESSAGE, new NoSetHierarchyException().getMessage());
    }
}
