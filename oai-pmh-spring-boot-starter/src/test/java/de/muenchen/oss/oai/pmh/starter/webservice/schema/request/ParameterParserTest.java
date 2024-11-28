package de.muenchen.oss.oai.pmh.starter.webservice.schema.request;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class ParameterParserTest {

    @Test
    void parsingRequestParametersToAnArgumentEnumMap() {
        Map<String, String[]> givenParameterMap = Map.of(
                "verb", new String[]{"ListRecords"},
                "from", new String[]{"1998-01-15"},
                "until", new String[]{"1998-01-20"},
                "set", new String[]{"DE-M36:DE-M36a"},
                "resumptionToken", new String[]{"xxx45abttyz"},
                "metadataPrefix", new String[]{"oai_rfc1807"},
                "identifier", new String[]{"oai:arXiv.org:hep-th/9901001"},
                "complete", new String[]{"true"});

        EnumMap<Argument, String> expectedArgumentMap = new EnumMap<>(Map.of(
                Argument.VERB, "ListRecords",
                Argument.FROM, "1998-01-15",
                Argument.UNTIL, "1998-01-20",
                Argument.SET, "DE-M36:DE-M36a",
                Argument.RESUMPTION_TOKEN, "xxx45abttyz",
                Argument.METADATA_PREFIX, "oai_rfc1807",
                Argument.IDENTIFIER, "oai:arXiv.org:hep-th/9901001",
                Argument.COMPLETE, "true"));

        EnumMap<Argument, String> givenArgumentMap = ParameterParser.parseParameterMapToArgumentEnumMap(givenParameterMap);
        Assertions.assertThat(givenArgumentMap).isEqualTo(expectedArgumentMap);
    }

    @Test
    void parsingRequestWithIllegalParametersThrowsIllegalArgumentException() {
        Map<String, String[]> givenParameterMap = Map.of(
                "verbb", new String[]{"GetRecord"},
                "identifier", new String[]{"oai:arXiv.org:hep-th/9901001"});
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> ParameterParser.parseParameterMapToArgumentEnumMap(givenParameterMap));
    }
}
