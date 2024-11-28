package de.muenchen.oss.oai.pmh.starter.webservice.validation;

import de.muenchen.oss.oai.pmh.starter.webservice.exceptions.BadArgumentException;
import de.muenchen.oss.oai.pmh.starter.webservice.schema.request.Argument;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.EnumMap;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ListMetadataFormatsValidatorTest {

    private static Executable runArgumentChecks(EnumMap<Argument, String> map) {
        return () -> new ListMetadataFormatsValidator().runArgumentChecks(map);
    }

    @Test
    void runArgumentChecks() {
        EnumMap<Argument, String> map = new EnumMap<>(Argument.class);
        map.put(Argument.IDENTIFIER, "oiapmh://hello.world.de:8080");
        map.put(Argument.VERB, "test");
        assertDoesNotThrow(runArgumentChecks(map));
    }

    @Test
    void runArgumentChecksWithWrongIdentifier() {
        EnumMap<Argument, String> map = new EnumMap<>(Argument.class);
        map.put(Argument.IDENTIFIER, "bla%&/()=?#");
        map.put(Argument.VERB, "test");
        assertThrows(BadArgumentException.class, runArgumentChecks(map));
    }
}
