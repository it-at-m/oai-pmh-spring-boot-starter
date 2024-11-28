package de.muenchen.oss.oai.pmh.starter.webservice.validation;

import de.muenchen.oss.oai.pmh.starter.webservice.exceptions.BadArgumentException;
import de.muenchen.oss.oai.pmh.starter.webservice.schema.request.Argument;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.EnumMap;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ListSetsValidatorTest {

    private static Executable runArgumentChecks(EnumMap<Argument, String> map) {
        return () -> new ListSetsValidator().runArgumentChecks(map);
    }

    @Test
    void runArgumentChecks() {
        EnumMap<Argument, String> map = new EnumMap<>(Argument.class);
        map.put(Argument.VERB, "test");
        assertDoesNotThrow(runArgumentChecks(map));
    }

    @Test
    void runArgumentChecksWithoutRequired() {
        EnumMap<Argument, String> map = new EnumMap<>(Argument.class);
        assertThrows(BadArgumentException.class, runArgumentChecks(map));
    }

}
