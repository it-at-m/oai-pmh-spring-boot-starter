package de.muenchen.oss.oai.pmh;

import de.muenchen.oss.oai.pmh.schema.OaiPmhType;

import javax.xml.datatype.XMLGregorianCalendar;

public interface RequestProcessor {

    default OaiPmhType processIdentifyRequest(OaiPmhType oaiPmhType, boolean complete) {
        return oaiPmhType;
    }

    default OaiPmhType processGetRecordRequest(String identifier, String metadataPrefix, boolean complete, OaiPmhType oaiPmhType) {
        return oaiPmhType;
    }

    default OaiPmhType processListIdentifiersRequest(XMLGregorianCalendar from, XMLGregorianCalendar until, String metadataPrefix, String set,
            String resumptionToken, boolean complete,
            OaiPmhType oaiPmhType) {
        return oaiPmhType;
    }

    default OaiPmhType processListMetadataFormatsRequest(String identifier, OaiPmhType oaiPmhType) {
        return oaiPmhType;
    }

    default OaiPmhType processListRecordsRequest(XMLGregorianCalendar from, XMLGregorianCalendar until, String metadataPrefix, String set,
            String resumptionToken, boolean complete,
            OaiPmhType oaiPmhType) {
        return oaiPmhType;
    }

    default OaiPmhType processListSetsRequest(String resumptionToken, OaiPmhType oaiPmhType) {
        return oaiPmhType;
    }

}
