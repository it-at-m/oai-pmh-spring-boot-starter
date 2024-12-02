package de.muenchen.oss.oai.pmh.starter.webservice.validation;

import de.muenchen.oss.oai.pmh.exceptions.BadArgumentException;
import de.muenchen.oss.oai.pmh.exceptions.BadVerbException;
import de.muenchen.oss.oai.pmh.starter.configuration.web.StringToXmlGregorianCalendarConverter;
import de.muenchen.oss.oai.pmh.starter.webservice.schema.request.Argument;
import de.muenchen.oss.oai.pmh.starter.webservice.schema.request.ParameterParser;

import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.XMLGregorianCalendar;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

abstract class RequestValidator {

    private final EnumSet<Argument> requiredArguments;
    private final EnumSet<Argument> optionalArguments;
    private final Optional<Argument> exclusiveArgument;

    RequestValidator() {
        this.requiredArguments = this.listRequiredArguments();
        this.optionalArguments = this.listOptionalArguments();
        this.exclusiveArgument = this.listExclusiveArgument();
    }

    abstract EnumSet<Argument> listRequiredArguments();

    abstract EnumSet<Argument> listOptionalArguments();

    abstract Optional<Argument> listExclusiveArgument();

    public void checkRequest(Map<String, String[]> requestArguments) {
        checkForRepeatedParameters(requestArguments);

        EnumMap<Argument, String> parsedRequestArguments = parseAndCheckRequestArguments(requestArguments);

        this.runArgumentChecks(parsedRequestArguments);
    }

    // calls parseRequestArguments and throws BadArgumentException if request contains an argument which isn't in the Argument enum
    EnumMap<Argument, String> parseAndCheckRequestArguments(Map<String, String[]> requestArguments) {
        try {
            return ParameterParser.parseParameterMapToArgumentEnumMap(requestArguments);
        } catch (IllegalArgumentException illegalArgumentException) {
            String errorMsg = "The request includes an illegal argument: %s".formatted(illegalArgumentException.getMessage());
            throw new BadArgumentException(errorMsg);
        }
    }

    void checkForRepeatedParameters(Map<String, String[]> parameterMap) {
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            if (entry.getValue().length > 1) {
                String errorMsg = "Repeated Argument found: %s".formatted(entry.getKey());
                if (Objects.equals(entry.getKey(), "verb")) {
                    throw new BadVerbException(errorMsg);
                }
                throw new BadArgumentException(errorMsg);
            }
        }
    }

    void runArgumentChecks(EnumMap<Argument, String> requestArguments) {
        checkForUnknownArguments(requestArguments);

        checkForMisuseOfExclusiveArgument(requestArguments);

        checkThatAllRequiredArgumentsArePresent(requestArguments);
    }

    void checkThatFromIsLessThanOrEqualToUntil(String from, String until) {
        if (from == null || until == null) {
            // Validate dates only if from and until are present in the URL
            return;
        }

        StringToXmlGregorianCalendarConverter stringToXmlGregorianCalendarConverter = new StringToXmlGregorianCalendarConverter();
        XMLGregorianCalendar fromDate = stringToXmlGregorianCalendarConverter.convert(from);
        XMLGregorianCalendar untilDate = stringToXmlGregorianCalendarConverter.convert(until);

        int delta = fromDate.compare(untilDate);

        if (delta == DatatypeConstants.GREATER) {
            throw new BadArgumentException("The 'from' argument must be less than or equal to the 'until' argument");
        }
    }

    void checkIfIdentifierHasUriSyntax(String identifier) {
        if (identifier == null) {
            // identifier argument is not present as query parameter in the URL -> nothing to validate
            return;
        }

        try {
            new URI(identifier);
        } catch (URISyntaxException e) {
            throw new BadArgumentException(
                    "The format of the identifier '%s' doesn't correspond to that of the URI (Uniform Resource Identifier) syntax".formatted(identifier));
        }
    }

    private void checkThatAllRequiredArgumentsArePresent(EnumMap<Argument, String> requestArguments) {
        // there are no required arguments if the exclusive argument is present
        if (exclusiveArgument.isPresent() && requestArguments.containsKey(exclusiveArgument.get())) {
            return;
        }

        requiredArguments.forEach(argument -> {
            if (requestArguments.get(argument) == null) {
                String errorMsg = "The request is missing a required argument: %s".formatted(argument.value());
                throw new BadArgumentException(errorMsg);
            }
        });
    }

    void checkForMisuseOfExclusiveArgument(EnumMap<Argument, String> requestArguments) {
        // the verb argument is always present, therefore you'll have two if an exclusive argument is present
        if (exclusiveArgument.isPresent() && requestArguments.containsKey(exclusiveArgument.get()) && requestArguments.size() > 2) {
            String errorMsg = "The request contains the exclusive argument: %s. Other arguments next to 'verb' and this exclusive argument are not allowed."
                    .formatted(
                            exclusiveArgument.get());
            throw new BadArgumentException(errorMsg);
        }
    }

    void checkForUnknownArguments(EnumMap<Argument, String> requestArguments) {
        EnumSet<Argument> allLegalArguments = getAllLegalArguments();

        requestArguments.keySet().forEach(argument -> {
            if (!allLegalArguments.contains(argument)) {
                String errorMsg = "The request contains an illegal argument: %s".formatted(argument.value());
                throw new BadArgumentException(errorMsg);
            }
        });
    }

    private EnumSet<Argument> getAllLegalArguments() {
        EnumSet<Argument> allLegalArguments = EnumSet.noneOf(Argument.class);
        allLegalArguments.addAll(requiredArguments);
        allLegalArguments.addAll(optionalArguments);
        exclusiveArgument.ifPresent(allLegalArguments::add);
        return allLegalArguments;
    }
}
