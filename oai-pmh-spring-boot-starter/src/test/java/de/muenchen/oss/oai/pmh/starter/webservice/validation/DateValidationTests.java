package de.muenchen.oss.oai.pmh.starter.webservice.validation;

import de.muenchen.oss.oai.pmh.exceptions.BadArgumentException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DateValidationTests {

    private final ListIdentifiersValidator listIdentifiersValidator = new ListIdentifiersValidator();

    @Test
    void fromIsGreaterThanUntilMustFailValidationRule() {
        assertThrows(BadArgumentException.class, checkThatFromIsLessThanOrEqualToUntil("2023-03-10", "2023-03-05"));
    }

    @Test
    void wrongFormatsUsedMustFailValidationRule() {
        assertThrows(BadArgumentException.class, checkThatFromIsLessThanOrEqualToUntil("10.10.2023", "2023-03-05"));
        assertThrows(BadArgumentException.class, checkThatFromIsLessThanOrEqualToUntil("2023-12-30", "2023-30-12"));
    }

    @Test
    void fromIsEqualsUntilMustPassValidationRule() {
        assertDoesNotThrow(checkThatFromIsLessThanOrEqualToUntil("2023-03-10", "2023-03-10"));
    }

    @Test
    void fromIsLessThanUntilMustPassValidationRule() {
        assertDoesNotThrow(checkThatFromIsLessThanOrEqualToUntil("2023-03-05", "2023-03-10"));
    }

    @Test
    void missingFromOrUntilArgumentMustPassValidationRule() {
        assertDoesNotThrow(checkThatFromIsLessThanOrEqualToUntil(null, "2023-03-10"));
        assertDoesNotThrow(checkThatFromIsLessThanOrEqualToUntil("2023-03-10", null));
    }

    private Executable checkThatFromIsLessThanOrEqualToUntil(String from, String until) {
        return () -> this.listIdentifiersValidator.checkThatFromIsLessThanOrEqualToUntil(from, until);
    }
}
