package de.muenchen.oss.oai.pmh.starter.webservice.validation;

import de.muenchen.oss.oai.pmh.exceptions.BadArgumentException;
import de.muenchen.oss.oai.pmh.starter.webservice.schema.request.Argument;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.EnumMap;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ListIdentifiersAndListRecordsValidatorTest {

    private static Executable runArgumentChecks(EnumMap<Argument, String> map) {
        return () -> {
            new ListIdentifiersValidator().runArgumentChecks(map);
            new ListRecordsValidator().runArgumentChecks(map);
        };
    }

    @Test
    void runArgumentChecks() {
        EnumMap<Argument, String> map = new EnumMap<>(Argument.class);
        map.put(Argument.METADATA_PREFIX, "prefix");
        map.put(Argument.VERB, "test");
        map.put(Argument.FROM, "2023-03-10");
        map.put(Argument.UNTIL, "2023-03-11");
        map.put(Argument.COMPLETE, "false");
        assertDoesNotThrow(runArgumentChecks(map));
    }

    @Test
    void runArgumentChecksWithWrongFromUntil() {
        EnumMap<Argument, String> map = new EnumMap<>(Argument.class);
        map.put(Argument.METADATA_PREFIX, "prefix");
        map.put(Argument.VERB, "test");
        map.put(Argument.UNTIL, "2023-03-10");
        map.put(Argument.FROM, "2023-03-11");
        map.put(Argument.COMPLETE, "true");
        assertThrows(BadArgumentException.class, runArgumentChecks(map));
    }
}
