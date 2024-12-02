package de.muenchen.oss.oai.pmh.starter.webservice.controller;

import de.muenchen.oss.oai.pmh.RequestProcessor;
import de.muenchen.oss.oai.pmh.exceptions.BadVerbException;
import de.muenchen.oss.oai.pmh.schema.OaiPmhType;
import de.muenchen.oss.oai.pmh.starter.webservice.schema.response.OaiPmhTypeFactory;
import de.muenchen.oss.oai.pmh.starter.webservice.validation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.datatype.XMLGregorianCalendar;

@RestController
@RequestMapping(value = "/oai-pmh", produces = MediaType.TEXT_XML_VALUE, method = { RequestMethod.GET, RequestMethod.POST })
@Slf4j
@Tag(
        name = "OAI-PMH API",
        description = "This API allows for the collection of metadata in line with the OAI-PMH standard. It supports both GET and POST HTTP methods to ensure full compliance with the protocol guidelines; both methods provide identical functionality and results. The API enables users to carry out operations such as Identify, GetRecord, ListIdentifiers, ListMetadataFormats, ListRecords, and ListSets."
)
public class OaiPmhController {

    private final IdentifyValidator identifyValidator;
    private final GetRecordValidator getRecordValidator;
    private final ListIdentifiersValidator listIdentifiersValidator;
    private final ListMetadataFormatsValidator listMetadataFormatsValidator;
    private final ListRecordsValidator listRecordsValidator;
    private final ListSetsValidator listSetsValidator;
    @Autowired
    private RequestProcessor requestProcessor;

    public OaiPmhController() {
        this.identifyValidator = new IdentifyValidator();
        this.getRecordValidator = new GetRecordValidator();
        this.listIdentifiersValidator = new ListIdentifiersValidator();
        this.listMetadataFormatsValidator = new ListMetadataFormatsValidator();
        this.listRecordsValidator = new ListRecordsValidator();
        this.listSetsValidator = new ListSetsValidator();
    }

    @Operation(
            summary = "Identify",
            description = "Retrieves general information about the OAI-PMH repository, such as the repository name, base URL, and protocol version."
    )
    @RequestMapping(params = { "verb=Identify" })
    public ResponseEntity<OaiPmhType> identify(HttpServletRequest request,
            @RequestParam(required = false) boolean complete) {
        this.identifyValidator.checkRequest(request.getParameterMap());
        OaiPmhType identifyResponseType = OaiPmhTypeFactory.createOaiPmhIdentifyResponseType(request);
        OaiPmhType oaiPmhType = requestProcessor.processIdentifyRequest(identifyResponseType, complete);
        oaiPmhType.setResponseDateToCurrentDate();
        return ResponseEntity.ok().body(oaiPmhType);
    }

    @Operation(
            summary = "GetRecord",
            description = "Fetches a single metadata record from the repository by its unique identifier, supporting selective retrieval within the metadata framework."
    )
    @RequestMapping(params = { "verb=GetRecord" })
    public ResponseEntity<OaiPmhType> getRecord(
            HttpServletRequest request,
            @RequestParam(required = false) String identifier,
            @RequestParam(required = false) String metadataPrefix,
            @RequestParam(required = false) boolean complete) {
        this.getRecordValidator.checkRequest(request.getParameterMap());
        OaiPmhType getRecordResponseType = OaiPmhTypeFactory.createOaiPmhGetRecordResponseType(request);
        OaiPmhType oaiPmhType = requestProcessor.processGetRecordRequest(identifier, metadataPrefix, complete, getRecordResponseType);
        oaiPmhType.setResponseDateToCurrentDate();
        return ResponseEntity.ok().body(oaiPmhType);
    }

    @Operation(
            summary = "ListIdentifiers",
            description = "Lists the identifiers of metadata records, allowing for incremental updates and synchronization within a specified date range or set."
    )
    @RequestMapping(params = { "verb=ListIdentifiers" })
    public ResponseEntity<OaiPmhType> listIdentifiers(
            HttpServletRequest request,
            @RequestParam(required = false) XMLGregorianCalendar from,
            @RequestParam(required = false) XMLGregorianCalendar until,
            @RequestParam(required = false) String metadataPrefix,
            @RequestParam(required = false) String set,
            @RequestParam(required = false) String resumptionToken,
            @RequestParam(required = false) boolean complete) {
        this.listIdentifiersValidator.checkRequest(request.getParameterMap());
        OaiPmhType listIdentifiersType = OaiPmhTypeFactory.createOaiPmhListIdentifiersType(request);
        OaiPmhType oaiPmhType = requestProcessor.processListIdentifiersRequest(from, until, metadataPrefix, set, resumptionToken, complete,
                listIdentifiersType);
        oaiPmhType.setResponseDateToCurrentDate();
        return ResponseEntity.ok().body(oaiPmhType);
    }

    @Operation(
            summary = "ListMetadataFormats",
            description = "Provides a list of metadata formats available for harvesting from the repository, enabling data providers to extend their service offerings."
    )
    @RequestMapping(params = { "verb=ListMetadataFormats" })
    public ResponseEntity<OaiPmhType> listMetadataFormats(
            HttpServletRequest request,
            @RequestParam(required = false) String identifier) {
        this.listMetadataFormatsValidator.checkRequest(request.getParameterMap());
        OaiPmhType listMetaDataFormatsType = OaiPmhTypeFactory.createOaiPmhListMetaDataFormatsType(request);
        OaiPmhType oaiPmhType = requestProcessor.processListMetadataFormatsRequest(identifier, listMetaDataFormatsType);
        oaiPmhType.setResponseDateToCurrentDate();
        return ResponseEntity.ok().body(oaiPmhType);
    }

    @Operation(
            summary = "ListRecords",
            description = "Harvests metadata records in bulk, with optional date-range filtering for the incremental update of large data sets."
    )
    @RequestMapping(params = { "verb=ListRecords" })
    public ResponseEntity<OaiPmhType> listRecords(
            HttpServletRequest request,
            @RequestParam(required = false) XMLGregorianCalendar from,
            @RequestParam(required = false) XMLGregorianCalendar until,
            @RequestParam(required = false) String metadataPrefix,
            @RequestParam(required = false) String set,
            @RequestParam(required = false) String resumptionToken,
            @RequestParam(required = false) boolean complete) {
        this.listRecordsValidator.checkRequest(request.getParameterMap());
        OaiPmhType listRecordsType = OaiPmhTypeFactory.createOaiPmhListRecordsType(request);
        OaiPmhType oaiPmhType = requestProcessor.processListRecordsRequest(from, until, metadataPrefix, set, resumptionToken, complete,
                listRecordsType);
        oaiPmhType.setResponseDateToCurrentDate();
        return ResponseEntity.ok().body(oaiPmhType);
    }

    @Operation(
            summary = "ListSets",
            description = "Offers information on all data sets (or 'sets') available in the OAI-PMH repository, facilitating selective harvesting based on specific collections or categories."
    )
    @RequestMapping(params = { "verb=ListSets" })
    public ResponseEntity<OaiPmhType> listSets(
            HttpServletRequest request,
            @RequestParam(required = false) String resumptionToken) {
        this.listSetsValidator.checkRequest(request.getParameterMap());
        OaiPmhType pmhListSetsType = OaiPmhTypeFactory.createOaiPmhListSetsType(request);
        OaiPmhType oaiPmhType = requestProcessor.processListSetsRequest(resumptionToken, pmhListSetsType);
        oaiPmhType.setResponseDateToCurrentDate();
        return ResponseEntity.ok().body(oaiPmhType);
    }

    @Operation(
            summary = "BadVerb",
            description = "Handles requests with invalid or unrecognized OAI-PMH verbs, ensuring robust error management and clear client communication."
    )
    @RequestMapping("")
    public ResponseEntity<OaiPmhType> badVerb() {
        throw new BadVerbException();
    }
}
