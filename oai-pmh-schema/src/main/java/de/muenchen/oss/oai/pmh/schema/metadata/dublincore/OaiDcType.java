package de.muenchen.oss.oai.pmh.schema.metadata.dublincore;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "oai_dcType", namespace = "http://www.openarchives.org/OAI/2.0/oai_dc/", propOrder = {
                "title", "creator", "subject", "description", "publisher", "contributor", "date", "type", "format",
                "identifier", "source", "language", "relation", "coverage", "rights"
        }
)
@Getter
@Setter
public class OaiDcType implements Serializable {

    @Serial
    private static final long serialVersionUID = 3797612289566533492L;

    @XmlAttribute(name = "xmlns:oai_dc")
    private final String xmlnsAoiDc = "http://www.openarchives.org/OAI/2.0/oai_dc/";

    @XmlAttribute(name = "xmlns:dc")
    private final String xmlnsDc = "http://purl.org/dc/elements/1.1/";

    @XmlAttribute(name = "xmlns:xsi")
    private final String xmlnsXsi = "http://www.w3.org/2001/XMLSchema-instance";

    @XmlAttribute(name = "xsi:schemaLocation")
    private final String schemaLocation = "http://www.openarchives.org/OAI/2.0/oai_dc/ http://www.openarchives.org/OAI/2.0/oai_dc.xsd";

    @XmlElement(name = "dc:title")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<String> title;
    @XmlElement(name = "dc:creator")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<String> creator;
    @XmlElement(name = "dc:subject")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<String> subject;
    @XmlElement(name = "dc:description")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<String> description;
    @XmlElement(name = "dc:publisher")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<String> publisher;
    @XmlElement(name = "dc:contributor")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<String> contributor;
    @XmlElement(name = "dc:date")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<String> date;
    @XmlElement(name = "dc:type")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<String> type;
    @XmlElement(name = "dc:format")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<String> format;
    @XmlElement(name = "dc:identifier")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<String> identifier;
    @XmlElement(name = "dc:source")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<String> source;
    @XmlElement(name = "dc:language")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<String> language;
    @XmlElement(name = "dc_relation")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<String> relation;
    @XmlElement(name = "dc:coverage")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<String> coverage;
    @XmlElement(name = "dc:rights")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<String> rights;
}
