package de.muenchen.oss.oai.pmh.starter.webservice.validation;

import de.muenchen.oss.oai.pmh.starter.webservice.schema.request.Argument;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Optional;

public class GetRecordValidator extends RequestValidator {
    @Override
    final EnumSet<Argument> listRequiredArguments() {
        return EnumSet.of(Argument.VERB, Argument.IDENTIFIER, Argument.METADATA_PREFIX);
    }

    @Override
    final EnumSet<Argument> listOptionalArguments() {
        return EnumSet.of(Argument.COMPLETE);
    }

    @Override
    final Optional<Argument> listExclusiveArgument() {
        return Optional.empty();
    }

    @Override
    void runArgumentChecks(EnumMap<Argument, String> requestArguments) {
        super.runArgumentChecks(requestArguments);
        this.checkIfIdentifierHasUriSyntax(requestArguments.get(Argument.IDENTIFIER));
    }
}
