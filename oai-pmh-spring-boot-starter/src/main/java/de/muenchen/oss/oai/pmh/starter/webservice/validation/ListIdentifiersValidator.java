package de.muenchen.oss.oai.pmh.starter.webservice.validation;

import de.muenchen.oss.oai.pmh.starter.webservice.schema.request.Argument;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Optional;

public class ListIdentifiersValidator extends RequestValidator {
    @Override
    final EnumSet<Argument> listRequiredArguments() {
        return EnumSet.of(Argument.VERB, Argument.METADATA_PREFIX);
    }

    @Override
    final EnumSet<Argument> listOptionalArguments() {
        return EnumSet.of(Argument.FROM, Argument.UNTIL, Argument.SET, Argument.COMPLETE);
    }

    @Override
    final Optional<Argument> listExclusiveArgument() {
        return Optional.of(Argument.RESUMPTION_TOKEN);
    }

    @Override
    void runArgumentChecks(EnumMap<Argument, String> requestArguments) {
        super.runArgumentChecks(requestArguments);
        this.checkThatFromIsLessThanOrEqualToUntil(requestArguments.get(Argument.FROM), requestArguments.get(Argument.UNTIL));
    }
}
