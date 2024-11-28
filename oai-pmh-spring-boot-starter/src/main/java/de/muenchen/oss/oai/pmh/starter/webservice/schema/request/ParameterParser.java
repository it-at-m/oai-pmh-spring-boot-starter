package de.muenchen.oss.oai.pmh.starter.webservice.schema.request;

import java.util.EnumMap;
import java.util.Map;

public class ParameterParser {

    private ParameterParser() {
    }

    public static EnumMap<Argument, String> parseParameterMapToArgumentEnumMap(Map<String, String[]> requestParameterMap) throws IllegalArgumentException {
        EnumMap<Argument, String> requestArguments = new EnumMap<>(Argument.class);
        // ignore duplicated arguments --> take the first value
        requestParameterMap.forEach((key, values) -> requestArguments.put(Argument.fromValue(key), values[0]));
        return requestArguments;
    }
}
