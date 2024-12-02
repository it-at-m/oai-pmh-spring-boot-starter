package de.muenchen.oss.oai.pmh.starter.webservice.controller;

import de.muenchen.oss.oai.pmh.exceptions.*;
import de.muenchen.oss.oai.pmh.schema.VerbType;
import de.muenchen.oss.oai.pmh.starter.webservice.schema.request.Argument;
import org.junit.jupiter.params.provider.Arguments;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class TestData {

    private static final Arguments identifyVerbAndIllegalArguments = Arguments.of(
            VerbType.IDENTIFY,
            List.of(
                    Argument.FROM, Argument.UNTIL, Argument.SET, Argument.RESUMPTION_TOKEN,
                    Argument.METADATA_PREFIX, Argument.IDENTIFIER));

    private static final Arguments getRecordVerbAndIllegalArguments = Arguments.of(
            VerbType.GET_RECORD,
            List.of(
                    Argument.FROM, Argument.UNTIL, Argument.SET, Argument.RESUMPTION_TOKEN));

    private static final Arguments listMetadataFormatsVerbAndIllegalArguments = Arguments.of(
            VerbType.LIST_METADATA_FORMATS,
            List.of(
                    Argument.FROM, Argument.UNTIL, Argument.SET,
                    Argument.RESUMPTION_TOKEN, Argument.METADATA_PREFIX, Argument.COMPLETE));

    private static final Arguments listSetsVerbAndIllegalArguments = Arguments.of(
            VerbType.LIST_SETS,
            List.of(
                    Argument.FROM, Argument.UNTIL, Argument.SET,
                    Argument.METADATA_PREFIX, Argument.IDENTIFIER, Argument.COMPLETE));

    private static final Arguments listRecordsVerbAndIllegalArguments = Arguments.of(
            VerbType.LIST_RECORDS,
            List.of(Argument.IDENTIFIER));

    private static final Arguments listIdentifiersVerbAndIllegalArguments = Arguments.of(
            VerbType.LIST_IDENTIFIERS,
            List.of(Argument.IDENTIFIER));

    private static final List<Arguments> legalIdentifyQueryStrings = new ArrayList<>(List.of(
            Arguments.of("?verb=Identify")));

    private static final List<Arguments> legalGetRecordQueryStrings = new ArrayList<>(List.of(
            Arguments.of("?verb=GetRecord&identifier=oai:arXiv.org:cs/0112017&metadataPrefix=oai_dc"),
            Arguments.of("?verb=GetRecord&identifier=oai:arXiv.org:cs/0112017&metadataPrefix=oai_dc&complete=true")));

    private static final List<Arguments> getRecordQueryStringWithMissingRequiredArguments = new ArrayList<>(List.of(
            Arguments.of("?verb=GetRecord"),
            Arguments.of("?verb=GetRecord&identifier=oai:arXiv.org:cs/0112017"),
            Arguments.of("?verb=GetRecord&metadataPrefix=oai_dc")));

    private static final List<Arguments> legalListMetadataFormatsQueryStrings = new ArrayList<>(List.of(
            Arguments.of("?verb=ListMetadataFormats"),
            Arguments.of("?verb=ListMetadataFormats&identifier=oai:perseus.tufts.edu:Perseus:text:1999.02.0119")));

    private static final List<Arguments> legalListSetsQueryStrings = new ArrayList<>(List.of(
            Arguments.of("?verb=ListSets"),
            Arguments.of("?verb=ListSets&resumptionToken=xxx45abttyz")));

    private static final List<Arguments> illegalListSetsQueryStringWithExclusiveArgument = new ArrayList<>(List.of(
            Arguments.of("?verb=ListSets&resumptionToken=xxx45abttyz&set=physics:hep")));

    private static final List<Arguments> legalListRecordsQueryStrings = new ArrayList<>(List.of(
            Arguments.of("?verb=ListRecords&metadataPrefix=oldArXiv"),
            Arguments.of("?verb=ListRecords&metadataPrefix=oldArXiv&set=physics:hep"),
            Arguments.of("?verb=ListRecords&metadataPrefix=oldArXiv&from=1998-01-15"),
            Arguments.of("?verb=ListRecords&metadataPrefix=oldArXiv&until=1998-01-15"),
            Arguments.of("?verb=ListRecords&metadataPrefix=oldArXiv&set=physics:hep&from=1998-01-15"),
            Arguments.of("?verb=ListRecords&metadataPrefix=oldArXiv&set=physics:hep&until=1998-01-15"),
            Arguments.of("?verb=ListRecords&metadataPrefix=oldArXiv&set=physics:hep&from=1998-01-15&until=1998-01-15"),
            Arguments.of("?verb=ListRecords&metadataPrefix=oldArXiv&from=1998-01-15&until=1998-01-15"),
            Arguments.of("?verb=ListRecords&resumptionToken=xxx45abttyz"),
            Arguments.of("?verb=ListRecords&metadataPrefix=oldArXiv&complete=true"),
            Arguments.of("?verb=ListRecords&metadataPrefix=oldArXiv&set=physics:hep&complete=true"),
            Arguments.of("?verb=ListRecords&metadataPrefix=oldArXiv&from=1998-01-15&complete=true"),
            Arguments.of("?verb=ListRecords&metadataPrefix=oldArXiv&until=1998-01-15&complete=false"),
            Arguments.of("?verb=ListRecords&metadataPrefix=oldArXiv&set=physics:hep&from=1998-01-15&complete=false"),
            Arguments.of("?verb=ListRecords&metadataPrefix=oldArXiv&set=physics:hep&until=1998-01-15&complete=false"),
            Arguments.of("?verb=ListRecords&metadataPrefix=oldArXiv&set=physics:hep&from=1998-01-15&until=1998-01-15&complete=false"),
            Arguments.of("?verb=ListRecords&metadataPrefix=oldArXiv&from=1998-01-15&until=1998-01-15&complete=false")));

    private static final List<Arguments> illegalListRecordsQueryStringsWithExclusiveArgument = new ArrayList<>(List.of(
            Arguments.of("?verb=ListRecords&resumptionToken=xxx45abttyz&metadataPrefix=oldArXiv"),
            Arguments.of("?verb=ListRecords&resumptionToken=xxx45abttyz&metadataPrefix=oldArXiv&set=physics:hep"),
            Arguments.of("?verb=ListRecords&resumptionToken=xxx45abttyz&metadataPrefix=oldArXiv&from=1998-01-15"),
            Arguments.of("?verb=ListRecords&resumptionToken=xxx45abttyz&metadataPrefix=oldArXiv&until=1998-01-15"),
            Arguments.of("?verb=ListRecords&resumptionToken=xxx45abttyz&metadataPrefix=oldArXiv&set=physics:hep&from=1998-01-15"),
            Arguments.of("?verb=ListRecords&resumptionToken=xxx45abttyz&metadataPrefix=oldArXiv&set=physics:hep&until=1998-01-15"),
            Arguments.of("?verb=ListRecords&resumptionToken=xxx45abttyz&metadataPrefix=oldArXiv&set=physics:hep&from=1998-01-15&until=1998-01-15"),
            Arguments.of("?verb=ListRecords&resumptionToken=xxx45abttyz&metadataPrefix=oldArXiv&from=1998-01-15&until=1998-01-15"),
            Arguments.of("?verb=ListRecords&resumptionToken=xxx45abttyz&complete=true"),
            Arguments.of("?verb=ListRecords&resumptionToken=xxx45abttyz&metadataPrefix=oldArXivcomplete=true"),
            Arguments.of("?verb=ListRecords&resumptionToken=xxx45abttyz&metadataPrefix=oldArXiv&set=physics:hepcomplete=true"),
            Arguments.of("?verb=ListRecords&resumptionToken=xxx45abttyz&metadataPrefix=oldArXiv&from=1998-01-15complete=true"),
            Arguments.of("?verb=ListRecords&resumptionToken=xxx45abttyz&metadataPrefix=oldArXiv&until=1998-01-15complete=false"),
            Arguments.of("?verb=ListRecords&resumptionToken=xxx45abttyz&metadataPrefix=oldArXiv&set=physics:hep&from=1998-01-15complete=false"),
            Arguments.of("?verb=ListRecords&resumptionToken=xxx45abttyz&metadataPrefix=oldArXiv&set=physics:hep&until=1998-01-15complete=false"),
            Arguments
                    .of("?verb=ListRecords&resumptionToken=xxx45abttyz&metadataPrefix=oldArXiv&set=physics:hep&from=1998-01-15&until=1998-01-15complete=false"),
            Arguments.of("?verb=ListRecords&resumptionToken=xxx45abttyz&metadataPrefix=oldArXiv&from=1998-01-15&until=1998-01-15complete=false")));

    private static final List<Arguments> listRecordsQueryStringWithMissingRequiredArguments = new ArrayList<>(List.of(
            Arguments.of("?verb=ListRecords"),
            Arguments.of("?verb=ListRecords&set=physics:hep"),
            Arguments.of("?verb=ListRecords&from=1998-01-15"),
            Arguments.of("?verb=ListRecords&until=1998-01-15"),
            Arguments.of("?verb=ListRecords&set=physics:hep&from=1998-01-15"),
            Arguments.of("?verb=ListRecords&set=physics:hep&until=1998-01-15"),
            Arguments.of("?verb=ListRecords&set=physics:hep&from=1998-01-15&until=1998-01-15"),
            Arguments.of("?verb=ListRecords&from=1998-01-15&until=1998-01-15"),
            Arguments.of("?verb=ListRecords&complete=true"),
            Arguments.of("?verb=ListRecords&set=physics:hep&complete=true"),
            Arguments.of("?verb=ListRecords&from=1998-01-15&complete=true"),
            Arguments.of("?verb=ListRecords&until=1998-01-15&complete=true"),
            Arguments.of("?verb=ListRecords&set=physics:hep&from=1998-01-15&complete=false"),
            Arguments.of("?verb=ListRecords&set=physics:hep&until=1998-01-15&complete=false"),
            Arguments.of("?verb=ListRecords&set=physics:hep&from=1998-01-15&until=1998-01-15&complete=false"),
            Arguments.of("?verb=ListRecords&from=1998-01-15&until=1998-01-15&complete=false")));

    private static final List<Arguments> legalListIdentifiersQueryStrings = new ArrayList<>(List.of(
            Arguments.of("?verb=ListIdentifiers&metadataPrefix=oldArXiv"),
            Arguments.of("?verb=ListIdentifiers&metadataPrefix=oldArXiv&set=physics:hep"),
            Arguments.of("?verb=ListIdentifiers&metadataPrefix=oldArXiv&from=1998-01-15"),
            Arguments.of("?verb=ListIdentifiers&metadataPrefix=oldArXiv&until=1998-01-15"),
            Arguments.of("?verb=ListIdentifiers&metadataPrefix=oldArXiv&set=physics:hep&from=1998-01-15"),
            Arguments.of("?verb=ListIdentifiers&metadataPrefix=oldArXiv&set=physics:hep&until=1998-01-15"),
            Arguments.of("?verb=ListIdentifiers&metadataPrefix=oldArXiv&set=physics:hep&from=1998-01-15&until=1998-01-15"),
            Arguments.of("?verb=ListIdentifiers&metadataPrefix=oldArXiv&from=1998-01-15&until=1998-01-15"),
            Arguments.of("?verb=ListIdentifiers&resumptionToken=xxx45abttyz"),
            Arguments.of("?verb=ListIdentifiers&metadataPrefix=oldArXiv&complete=true"),
            Arguments.of("?verb=ListIdentifiers&metadataPrefix=oldArXiv&set=physics:hep&complete=true"),
            Arguments.of("?verb=ListIdentifiers&metadataPrefix=oldArXiv&from=1998-01-15&complete=true"),
            Arguments.of("?verb=ListIdentifiers&metadataPrefix=oldArXiv&until=1998-01-15&complete=false"),
            Arguments.of("?verb=ListIdentifiers&metadataPrefix=oldArXiv&set=physics:hep&from=1998-01-15&complete=false"),
            Arguments.of("?verb=ListIdentifiers&metadataPrefix=oldArXiv&set=physics:hep&until=1998-01-15&complete=false"),
            Arguments.of("?verb=ListIdentifiers&metadataPrefix=oldArXiv&set=physics:hep&from=1998-01-15&until=1998-01-15&complete=false"),
            Arguments.of("?verb=ListIdentifiers&metadataPrefix=oldArXiv&from=1998-01-15&until=1998-01-15&complete=false")));

    private static final List<Arguments> illegalListIdentifiersQueryStringsWithExclusiveArgument = new ArrayList<>(List.of(
            Arguments.of("?verb=ListIdentifiers&resumptionToken=xxx45abttyz&metadataPrefix=oldArXiv"),
            Arguments.of("?verb=ListIdentifiers&resumptionToken=xxx45abttyz&metadataPrefix=oldArXiv&set=physics:hep"),
            Arguments.of("?verb=ListIdentifiers&resumptionToken=xxx45abttyz&metadataPrefix=oldArXiv&from=1998-01-15"),
            Arguments.of("?verb=ListIdentifiers&resumptionToken=xxx45abttyz&metadataPrefix=oldArXiv&until=1998-01-15"),
            Arguments.of("?verb=ListIdentifiers&resumptionToken=xxx45abttyz&metadataPrefix=oldArXiv&set=physics:hep&from=1998-01-15"),
            Arguments.of("?verb=ListIdentifiers&resumptionToken=xxx45abttyz&metadataPrefix=oldArXiv&set=physics:hep&until=1998-01-15"),
            Arguments.of("?verb=ListIdentifiers&resumptionToken=xxx45abttyz&metadataPrefix=oldArXiv&set=physics:hep&from=1998-01-15&until=1998-01-15"),
            Arguments.of("?verb=ListIdentifiers&resumptionToken=xxx45abttyz&metadataPrefix=oldArXiv&from=1998-01-15&until=1998-01-15"),
            Arguments.of("?verb=ListIdentifiers&resumptionToken=xxx45abttyz&complete=true"),
            Arguments.of("?verb=ListIdentifiers&resumptionToken=xxx45abttyz&metadataPrefix=oldArXiv&complete=true"),
            Arguments.of("?verb=ListIdentifiers&resumptionToken=xxx45abttyz&metadataPrefix=oldArXiv&set=physics:hep&complete=true"),
            Arguments.of("?verb=ListIdentifiers&resumptionToken=xxx45abttyz&metadataPrefix=oldArXiv&from=1998-01-1&complete=true5"),
            Arguments.of("?verb=ListIdentifiers&resumptionToken=xxx45abttyz&metadataPrefix=oldArXiv&until=1998-01-15&complete=false"),
            Arguments.of("?verb=ListIdentifiers&resumptionToken=xxx45abttyz&metadataPrefix=oldArXiv&set=physics:hep&from=1998-01-15&complete=false"),
            Arguments.of("?verb=ListIdentifiers&resumptionToken=xxx45abttyz&metadataPrefix=oldArXiv&set=physics:hep&until=1998-01-15&complete=false"),
            Arguments.of(
                    "?verb=ListIdentifiers&resumptionToken=xxx45abttyz&metadataPrefix=oldArXiv&set=physics:hep&from=1998-01-15&until=1998-01-15&complete=false"),
            Arguments.of("?verb=ListIdentifiers&resumptionToken=xxx45abttyz&metadataPrefix=oldArXiv&from=1998-01-15&until=1998-01-15&complete=false")));

    private static final List<Arguments> listIdentifiersQueryStringWithMissingRequiredArguments = new ArrayList<>(List.of(
            Arguments.of("?verb=ListIdentifiers"),
            Arguments.of("?verb=ListIdentifiers&set=physics:hep"),
            Arguments.of("?verb=ListIdentifiers&from=1998-01-15"),
            Arguments.of("?verb=ListIdentifiers&until=1998-01-15"),
            Arguments.of("?verb=ListIdentifiers&set=physics:hep&from=1998-01-15"),
            Arguments.of("?verb=ListIdentifiers&set=physics:hep&until=1998-01-15"),
            Arguments.of("?verb=ListIdentifiers&set=physics:hep&from=1998-01-15&until=1998-01-15"),
            Arguments.of("?verb=ListIdentifiers&from=1998-01-15&until=1998-01-15"),
            Arguments.of("?verb=ListIdentifiers&complete=true"),
            Arguments.of("?verb=ListIdentifiers&set=physics:hep&complete=true"),
            Arguments.of("?verb=ListIdentifiers&from=1998-01-15&complete=true"),
            Arguments.of("?verb=ListIdentifiers&until=1998-01-15&complete=false"),
            Arguments.of("?verb=ListIdentifiers&set=physics:hep&from=1998-01-15&complete=false"),
            Arguments.of("?verb=ListIdentifiers&set=physics:hep&until=1998-01-15&complete=false"),
            Arguments.of("?verb=ListIdentifiers&set=physics:hep&from=1998-01-15&until=1998-01-15&complete=false"),
            Arguments.of("?verb=ListIdentifiers&from=1998-01-15&until=1998-01-15&complete=false")));

    private static final Arguments repeatedIdentifyVerb = Arguments.of("?verb=Identify&verb=Identify");
    private static final Arguments repeatedGetRecordVerb = Arguments.of("?verb=GetRecord&verb=GetRecord");
    private static final Arguments repeatedListMetadataFormatsVerb = Arguments.of("?verb=ListMetadataFormats&verb=ListMetadataFormats");
    private static final Arguments repeatedListSetsVerb = Arguments.of("?verb=ListSets&verb=ListSets");
    private static final Arguments repeatedListRecordsVerb = Arguments.of("?verb=ListRecords&verb=ListRecords");
    private static final Arguments repeatedListIdentifiersVerb = Arguments.of("?verb=ListIdentifiers&verb=ListIdentifiers");

    private static final List<Arguments> oaiPmhExceptions = new ArrayList<>(List.of(
            Arguments.of(new BadArgumentException()),
            Arguments.of(new BadResumptionTokenException()),
            Arguments.of(new BadVerbException()),
            Arguments.of(new CannotDisseminateFormatException()),
            Arguments.of(new IdDoesNotExistException()),
            Arguments.of(new NoMetadataFormatsException()),
            Arguments.of(new NoRecordsMatchException()),
            Arguments.of(new NoSetHierarchyException())));

    static Stream<Arguments> getVerbTypesAndIllegalArguments() {
        return Stream.of(
                identifyVerbAndIllegalArguments, getRecordVerbAndIllegalArguments,
                listIdentifiersVerbAndIllegalArguments, listRecordsVerbAndIllegalArguments,
                listSetsVerbAndIllegalArguments, listMetadataFormatsVerbAndIllegalArguments);
    }

    static Stream<Arguments> getLegalQueryStrings() {
        return Stream.of(
                legalIdentifyQueryStrings.stream(), legalGetRecordQueryStrings.stream(),
                legalListIdentifiersQueryStrings.stream(), legalListRecordsQueryStrings.stream(),
                legalListSetsQueryStrings.stream(), legalListMetadataFormatsQueryStrings.stream()).flatMap(Function.identity());
    }

    static Stream<Arguments> getLegalIdentifyQueryStrings() {
        return legalIdentifyQueryStrings.stream();
    }

    static Stream<Arguments> getLegalGetRecordQueryStrings() {
        return legalGetRecordQueryStrings.stream();
    }

    static Stream<Arguments> getLegalListIdentifiersQueryStrings() {
        return legalListIdentifiersQueryStrings.stream();
    }

    static Stream<Arguments> getLegalListRecordsQueryStrings() {
        return legalListRecordsQueryStrings.stream();
    }

    static Stream<Arguments> getLegalListSetsQueryStrings() {
        return legalListSetsQueryStrings.stream();
    }

    static Stream<Arguments> getLegalListMetadataFormatsQueryStrings() {
        return legalListMetadataFormatsQueryStrings.stream();
    }

    static Stream<Arguments> getQueryStringsWithRepeatedVerbArgument() {
        return Stream.of(
                repeatedIdentifyVerb, repeatedGetRecordVerb,
                repeatedListIdentifiersVerb, repeatedListRecordsVerb,
                repeatedListSetsVerb, repeatedListMetadataFormatsVerb);
    }

    static Stream<Arguments> getIllegalQueryStringsWithExclusiveArgument() {
        return Stream.of(
                illegalListIdentifiersQueryStringsWithExclusiveArgument.stream(),
                illegalListRecordsQueryStringsWithExclusiveArgument.stream(),
                illegalListSetsQueryStringWithExclusiveArgument.stream()).flatMap(Function.identity());
    }

    static Stream<Arguments> getQueryStringsWithMissingRequiredArguments() {
        return Stream.of(
                getRecordQueryStringWithMissingRequiredArguments.stream(),
                listRecordsQueryStringWithMissingRequiredArguments.stream(),
                listIdentifiersQueryStringWithMissingRequiredArguments.stream()).flatMap(Function.identity());
    }

    static Stream<Arguments> getOaiPmhExceptions() {
        return oaiPmhExceptions.stream();
    }
}
