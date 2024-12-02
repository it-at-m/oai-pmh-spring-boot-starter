package de.muenchen.oss.oai.pmh.starter.webservice;

import de.muenchen.oss.oai.pmh.RequestProcessor;
import de.muenchen.oss.oai.pmh.schema.*;
import de.muenchen.oss.oai.pmh.schema.metadata.dublincore.OaiDcType;

import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigInteger;

public class RequestProcessorImplementation implements RequestProcessor {
    @Override
    public OaiPmhType processIdentifyRequest(OaiPmhType oaiPmhType, boolean complete) {
        IdentifyType identifyType = oaiPmhType.getIdentify();
        identifyType.setBaseURL("http://localhost:8080/oai-pmh");
        identifyType.setDeletedRecord(DeletedRecordType.TRANSIENT);
        identifyType.getAdminEmail().add("opensource@muenchen.de");
        identifyType.getAdminEmail().add("not.a.real.email.address@muenchen.de");
        identifyType.setGranularity(GranularityType.YYYY_MM_DD);
        identifyType.setEarliestDatestamp("1960-01-01");
        identifyType.setProtocolVersion("v2.0");
        identifyType.setRepositoryName("Münchner Stadtbibliotheken");
        return oaiPmhType;
    }

    @Override
    public OaiPmhType processGetRecordRequest(String identifier, String metadataPrefix, boolean complete, OaiPmhType oaiPmhType) {
        GetRecordType getRecordType = oaiPmhType.getGetRecord();
        RecordType recordType = new RecordType();
        HeaderType headerType = new HeaderType();
        headerType.setIdentifier("oai:arXiv.org:hep-th/9801001");
        headerType.setDatestamp("1999-02-23");
        headerType.setStatus(StatusType.DELETED);
        headerType.getSetSpec().add("physic:hep");
        headerType.getSetSpec().add("physic:exp");
        recordType.setHeader(headerType);
        MetadataType metadataType = new MetadataType();
        metadataType.setOaiDcType(new OaiDcType());
        recordType.setMetadata(metadataType);
        AboutType aboutType = new AboutType();
        aboutType.setAny("about");
        recordType.getAbout().add(aboutType);
        getRecordType.setRecordType(recordType);
        return oaiPmhType;
    }

    @Override
    public OaiPmhType processListIdentifiersRequest(XMLGregorianCalendar from, XMLGregorianCalendar until, String metadataPrefix, String set,
            String resumptionToken, boolean complete, OaiPmhType oaiPmhType) {
        ListIdentifiersType listIdentifiersType = oaiPmhType.getListIdentifiers();
        ResumptionTokenType resumptionTokenType = new ResumptionTokenType();
        resumptionTokenType.setCursor(BigInteger.TEN);
        resumptionTokenType.setValue("fjkldsfljksdlkjfjdkljf");
        resumptionTokenType.setCompleteListSize(BigInteger.valueOf(100));
        HeaderType headerType = new HeaderType();
        headerType.setIdentifier("oai:arXiv.org:hep-th/9801001");
        headerType.setDatestamp("1999-02-23");
        headerType.setStatus(StatusType.DELETED);
        headerType.getSetSpec().add("physic:hep");
        headerType.getSetSpec().add("physic:exp");
        HeaderType headerType2 = new HeaderType();
        headerType2.setIdentifier("oai:arXiv.org:hep-th/9801001");
        headerType2.setDatestamp("1999-02-23");
        headerType2.setStatus(StatusType.DELETED);
        headerType2.getSetSpec().add("physic:hep");
        headerType2.getSetSpec().add("physic:exp");
        listIdentifiersType.getHeader().add(headerType);
        listIdentifiersType.getHeader().add(headerType2);
        listIdentifiersType.setResumptionToken(resumptionTokenType);
        return oaiPmhType;
    }

    @Override
    public OaiPmhType processListMetadataFormatsRequest(String identifier, OaiPmhType oaiPmhType) {
        ListMetadataFormatsType listMetadataFormatsType = oaiPmhType.getListMetadataFormats();
        MetadataFormatType metadataFormatType = new MetadataFormatType();
        metadataFormatType.setMetadataNamespace("a");
        metadataFormatType.setMetadataPrefix("b");
        metadataFormatType.setSchema("c");
        listMetadataFormatsType.getMetadataFormat().add(metadataFormatType);
        return oaiPmhType;
    }

    @Override
    public OaiPmhType processListRecordsRequest(XMLGregorianCalendar from, XMLGregorianCalendar until, String metadataPrefix, String set,
            String resumptionToken, boolean complete, OaiPmhType oaiPmhType) {
        ListRecordsType listRecordsType = oaiPmhType.getListRecords();
        RecordType recordType = new RecordType();
        HeaderType headerType = new HeaderType();
        headerType.setIdentifier("oai:arXiv.org:hep-th/9801001");
        headerType.setDatestamp("1999-02-23");
        headerType.setStatus(StatusType.DELETED);
        headerType.getSetSpec().add("physic:hep");
        headerType.getSetSpec().add("physic:exp");
        recordType.setHeader(headerType);
        MetadataType metadataType = new MetadataType();
        metadataType.setOaiDcType(new OaiDcType());
        recordType.setMetadata(metadataType);
        AboutType aboutType = new AboutType();
        aboutType.setAny("about");
        recordType.getAbout().add(aboutType);
        listRecordsType.getRecordTypes().add(recordType);
        ResumptionTokenType resumptionTokenType = new ResumptionTokenType();
        resumptionTokenType.setCursor(BigInteger.TEN);
        resumptionTokenType.setValue("fjkldsfljksdlkjfjdkljf");
        resumptionTokenType.setCompleteListSize(BigInteger.valueOf(100));
        listRecordsType.setResumptionToken(resumptionTokenType);
        return oaiPmhType;
    }

    @Override
    public OaiPmhType processListSetsRequest(String resumptionToken, OaiPmhType oaiPmhType) {
        ListSetsType listSetsType = oaiPmhType.getListSets();
        SetType setType = new SetType();
        setType.setSetSpec("DE-M36");
        setType.setSetName("Münchner Stadttbibliothek");
        ResumptionTokenType resumptionTokenType = new ResumptionTokenType();
        resumptionTokenType.setCursor(BigInteger.TEN);
        resumptionTokenType.setValue("fjkldsfljksdlkjfjdkljf");
        resumptionTokenType.setCompleteListSize(BigInteger.valueOf(100));
        listSetsType.getSet().add(setType);
        listSetsType.setResumptionToken(resumptionTokenType);
        return oaiPmhType;
    }
}
