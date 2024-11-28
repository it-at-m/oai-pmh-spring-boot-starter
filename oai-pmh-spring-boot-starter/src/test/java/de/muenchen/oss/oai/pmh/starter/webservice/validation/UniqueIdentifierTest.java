package de.muenchen.oss.oai.pmh.starter.webservice.validation;

import de.muenchen.oss.oai.pmh.starter.webservice.exceptions.BadArgumentException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UniqueIdentifierTest {

    private final GetRecordValidator getRecordValidator = new GetRecordValidator();

    @Test
    void identifierWithValidSyntaxMustPassValidationCheck() {
        String identifierWithValidSyntax = "oai:arXiv.org:cs/0112017";
        assertDoesNotThrow(checkIfIdentifierHasUriSyntax(identifierWithValidSyntax));
    }

    @Test
    void identifierWithInvalidSyntaxMustFailValidationCheck() {
        String identifierWithInvalidSyntax = "bla%&/()=?#";
        assertThrows(BadArgumentException.class, checkIfIdentifierHasUriSyntax(identifierWithInvalidSyntax));
    }

    @Test
    void missingIdentifierArgumentMustPassValiationCheck() {
        String identifier = null;
        assertDoesNotThrow(checkIfIdentifierHasUriSyntax((identifier)));
    }

    private Executable checkIfIdentifierHasUriSyntax(String identifierWithValidSyntax) {
        return () -> this.getRecordValidator.checkIfIdentifierHasUriSyntax(identifierWithValidSyntax);
    }
}
