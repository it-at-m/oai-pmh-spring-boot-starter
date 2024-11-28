package de.muenchen.oss.oai.pmh.starter.webservice.validation;

import de.muenchen.oss.oai.pmh.starter.webservice.schema.request.Argument;

import java.util.EnumSet;
import java.util.Optional;

public class IdentifyValidator extends RequestValidator {

    @Override
    final EnumSet<Argument> listOptionalArguments() {
        return EnumSet.of(Argument.COMPLETE);
    }

    @Override
    final Optional<Argument> listExclusiveArgument() {
        return Optional.empty();
    }

    @Override
    final EnumSet<Argument> listRequiredArguments() {
        return EnumSet.of(Argument.VERB);
    }

}
