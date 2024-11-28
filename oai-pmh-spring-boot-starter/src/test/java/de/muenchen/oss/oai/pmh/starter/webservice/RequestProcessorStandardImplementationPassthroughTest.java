package de.muenchen.oss.oai.pmh.starter.webservice;

import de.muenchen.oss.oai.pmh.schema.OaiPmhType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RequestProcessorStandardImplementationPassthroughTest {

    RequestProcessor requestProcessor = new RequestProcessorImplementation();

    @Test
    void processIdentifyRequest() {
        OaiPmhType oaiPmhType = new OaiPmhType();
        assertEquals(requestProcessor.processIdentifyRequest(oaiPmhType, false), oaiPmhType);
    }

    @Test
    void processGetRecordRequest() {
        OaiPmhType oaiPmhType = new OaiPmhType();
        assertEquals(requestProcessor.processGetRecordRequest("", "", false, oaiPmhType), oaiPmhType);
    }

    @Test
    void processListIdentifiersRequest() {
        OaiPmhType oaiPmhType = new OaiPmhType();
        assertEquals(requestProcessor.processListIdentifiersRequest(null, null, "", "", "", false, oaiPmhType), oaiPmhType);
    }

    @Test
    void processListMetadataFormatsRequest() {
        OaiPmhType oaiPmhType = new OaiPmhType();
        assertEquals(requestProcessor.processListMetadataFormatsRequest("", oaiPmhType), oaiPmhType);
    }

    @Test
    void processListRecordsRequest() {
        OaiPmhType oaiPmhType = new OaiPmhType();
        assertEquals(requestProcessor.processListRecordsRequest(null, null, "", "", "", false, oaiPmhType), oaiPmhType);
    }

    @Test
    void processListSetsRequest() {
        OaiPmhType oaiPmhType = new OaiPmhType();
        assertEquals(requestProcessor.processListSetsRequest("", oaiPmhType), oaiPmhType);
    }

    static class RequestProcessorImplementation implements RequestProcessor {
    }
}
