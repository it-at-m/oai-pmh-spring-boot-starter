package de.muenchen.oss.oai.pmh.starter.webservice.validation;

import de.muenchen.oss.oai.pmh.starter.webservice.exceptions.BadArgumentException;
import de.muenchen.oss.oai.pmh.starter.webservice.exceptions.BadVerbException;
import de.muenchen.oss.oai.pmh.starter.webservice.schema.request.Argument;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RequestValidatorTest {

    @Test
    void checkForRepeatedParameters() {
        RequestValidator validator = new GetRecordValidator();

        HashMap<String, String[]> parameterMap = new HashMap<>();
        parameterMap.put("Hello", new String[]{"World"});
        assertDoesNotThrow(() -> validator.checkForRepeatedParameters(parameterMap));
    }

    @Test
    void checkForRepeatedParametersThrowsBadArgumentException() {
        RequestValidator validator = new GetRecordValidator();

        HashMap<String, String[]> parameterMap = new HashMap<>();
        parameterMap.put("Hello", new String[]{"World", "World"});
        assertThrows(BadArgumentException.class, () -> validator.checkForRepeatedParameters(parameterMap));
    }

    @Test
    void checkForRepeatedParametersThrowsBadVerbException() {
        RequestValidator validator = new GetRecordValidator();

        HashMap<String, String[]> parameterMap = new HashMap<>();
        parameterMap.put("verb", new String[]{"World", "World"});
        assertThrows(BadVerbException.class, () -> validator.checkForRepeatedParameters(parameterMap));
    }

    @Test
    void checkSuccessfulRequest() {
        RequestValidator validator = new IdentifyValidator();

        Map<String, String[]> requestArguments = new HashMap<>();
        requestArguments.put("verb", new String[]{"Identify"});
        assertDoesNotThrow(() -> validator.checkRequest(requestArguments));
    }

    @Test
    void parseAndCheckRequestArguments() {
        RequestValidator validator = new GetRecordValidator();

        Map<String, String[]> requestArguments = new HashMap<>();
        requestArguments.put("identifier", new String[]{"World"});
        Assertions.assertDoesNotThrow(() -> validator.parseAndCheckRequestArguments(requestArguments));

        requestArguments.put("Hello", new String[]{"World"});
        assertThrows(BadArgumentException.class, () -> validator.parseAndCheckRequestArguments(requestArguments));
    }

    @Test
    void checkForMisuseOfExclusiveArgument() {
        RequestValidator validator = new ListIdentifiersValidator();

        EnumMap<Argument, String> requestArguments = new EnumMap<>(Argument.class);
        requestArguments.put(Argument.RESUMPTION_TOKEN, "test");
        requestArguments.put(Argument.VERB, "test2");
        requestArguments.put(Argument.IDENTIFIER, "test3");

        assertThrows(BadArgumentException.class, () -> validator.checkForMisuseOfExclusiveArgument(requestArguments));
    }

    @Test
    void checkForUnknownArgumentsWithIllegalArgument() {
        RequestValidator validator = new IdentifyValidator();

        EnumMap<Argument, String> requestArguments = new EnumMap<>(Argument.class);
        requestArguments.put(Argument.VERB, "Identify");
        requestArguments.put(Argument.METADATA_PREFIX, "abc");

        assertThrows(BadArgumentException.class, () -> validator.checkForUnknownArguments(requestArguments));
    }
}
