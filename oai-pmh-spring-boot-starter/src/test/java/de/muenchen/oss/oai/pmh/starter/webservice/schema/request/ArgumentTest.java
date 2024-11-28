package de.muenchen.oss.oai.pmh.starter.webservice.schema.request;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class ArgumentTest {

    private static final String[] legalArguments = new String[]{"verb", "from", "until", "set", "metadataPrefix", "resumptionToken", "identifier",
            "complete"};

    @Test
    void parsingLegalArgumentStringsToArgumentObjects() {
        Arrays.stream(legalArguments).forEach(givenValue -> {
            Argument givenArgument = Argument.fromValue(givenValue);
            assertThat(givenArgument).isNotNull();
            assertThat(givenArgument.value()).isEqualTo(givenValue);
        });
    }

    @Test
    void parsingIllegalArgumentStringsThrowsIllegalArgumentException() {
        String givenValue = "Apfelkuchen";
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> Argument.fromValue(givenValue));
    }

    @Test
    void argumentEnumHoldsOnlyLegalOaiPmhArguments() {
        List<Argument> expectedArguments = new ArrayList<>(List.of(
                Argument.VERB, Argument.FROM, Argument.UNTIL, Argument.IDENTIFIER,
                Argument.SET, Argument.RESUMPTION_TOKEN, Argument.METADATA_PREFIX, Argument.COMPLETE));
        Argument[] givenArguments = Argument.values();

        Assertions.assertThat(givenArguments).containsOnlyElementsOf(expectedArguments);
    }
}
