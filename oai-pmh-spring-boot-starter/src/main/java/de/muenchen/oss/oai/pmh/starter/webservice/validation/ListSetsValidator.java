package de.muenchen.oss.oai.pmh.starter.webservice.validation;

import de.muenchen.oss.oai.pmh.starter.webservice.schema.request.Argument;

import java.util.EnumSet;
import java.util.Optional;

public class ListSetsValidator extends RequestValidator {
    @Override
    final EnumSet<Argument> listRequiredArguments() {
        return EnumSet.of(Argument.VERB);
    }

    @Override
    final EnumSet<Argument> listOptionalArguments() {
        return EnumSet.noneOf(Argument.class);
    }

    @Override
    final Optional<Argument> listExclusiveArgument() {
        return Optional.of(Argument.RESUMPTION_TOKEN);
    }
}
