package de.muenchen.oss.oai.pmh.schema.metadata.dublincore;

import jakarta.xml.bind.annotation.*;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(
        name = "oai_dcType", namespace = "http://www.openarchives.org/OAI/2.0/oai_dc/", propOrder = {
                "title", "creator", "subject", "description", "publisher", "contributor", "date", "type", "format",
                "identifier", "source", "language", "relation", "coverage", "rights"
        }
)
@XmlRootElement(name = "dc")
@ToString
public class OaiDcXslTransformationType implements Serializable {

    @Serial
    private static final long serialVersionUID = 3797612289566533482L;
    private final String xmlnsOaiDc = "http://www.openarchives.org/OAI/2.0/oai_dc/";
    private final String xmlnsDc = "http://purl.org/dc/elements/1.1/";
    private final String xmlnsXsi = "http://www.w3.org/2001/XMLSchema-instance";
    private final String schemaLocation = "http://www.openarchives.org/OAI/2.0/oai_dc/ http://www.openarchives.org/OAI/2.0/oai_dc.xsd";
    private List<String> title;
    private List<String> creator;
    private List<String> subject;
    private List<String> description;
    private List<String> publisher;
    private List<String> contributor;
    private List<String> date;
    private List<String> type;
    private List<String> format;
    private List<String> identifier;
    private List<String> source;
    private List<String> language;
    private List<String> relation;
    private List<String> coverage;
    private List<String> rights;

    @XmlAttribute(name = "oai_dc", namespace = "http://www.w3.org/2000/xmlns/")
    public String getXmlnsOaiDc() {
        return xmlnsOaiDc;
    }

    @XmlAttribute(name = "dc", namespace = "http://www.w3.org/2000/xmlns/")
    public String getXmlnsDc() {
        return xmlnsDc;
    }

    @XmlAttribute(name = "xsi", namespace = "http://www.w3.org/2000/xmlns/")
    public String getXmlnsXsi() {
        return xmlnsXsi;
    }

    @XmlAttribute(name = "xsi:schemaLocation")
    public String getSchemaLocation() {
        return schemaLocation;
    }

    @XmlElement(name = "title", namespace = "http://purl.org/dc/elements/1.1/")
    public List<String> getTitle() {
        return title;
    }

    @XmlElement(name = "creator", namespace = "http://purl.org/dc/elements/1.1/")
    public List<String> getCreator() {
        return creator;
    }

    @XmlElement(name = "subject", namespace = "http://purl.org/dc/elements/1.1/")
    public List<String> getSubject() {
        return subject;
    }

    @XmlElement(name = "description", namespace = "http://purl.org/dc/elements/1.1/")
    public List<String> getDescription() {
        return description;
    }

    @XmlElement(name = "publisher", namespace = "http://purl.org/dc/elements/1.1/")
    public List<String> getPublisher() {
        return publisher;
    }

    @XmlElement(name = "contributor", namespace = "http://purl.org/dc/elements/1.1/")
    public List<String> getContributor() {
        return contributor;
    }

    @XmlElement(name = "date", namespace = "http://purl.org/dc/elements/1.1/")
    public List<String> getDate() {
        return date;
    }

    @XmlElement(name = "type", namespace = "http://purl.org/dc/elements/1.1/")
    public List<String> getType() {
        return type;
    }

    @XmlElement(name = "format", namespace = "http://purl.org/dc/elements/1.1/")
    public List<String> getFormat() {
        return format;
    }

    @XmlElement(name = "identifier", namespace = "http://purl.org/dc/elements/1.1/")
    public List<String> getIdentifier() {
        return identifier;
    }

    @XmlElement(name = "source", namespace = "http://purl.org/dc/elements/1.1/")
    public List<String> getSource() {
        return source;
    }

    @XmlElement(name = "language", namespace = "http://purl.org/dc/elements/1.1/")
    public List<String> getLanguage() {
        return language;
    }

    @XmlElement(name = "relation", namespace = "http://purl.org/dc/elements/1.1/")
    public List<String> getRelation() {
        return relation;
    }

    @XmlElement(name = "coverage", namespace = "http://purl.org/dc/elements/1.1/")
    public List<String> getCoverage() {
        return coverage;
    }

    @XmlElement(name = "rights", namespace = "http://purl.org/dc/elements/1.1/")
    public List<String> getRights() {
        return rights;
    }

    public void setTitle(List<String> title) {
        if (this.title == null) {
            this.title = new ArrayList<>(title.size());
        }
        this.title.addAll(title);
    }

    public void setCreator(List<String> creator) {
        if (this.creator == null) {
            this.creator = new ArrayList<>(creator.size());
        }
        this.creator.addAll(creator);
    }

    public void setSubject(List<String> subject) {
        if (this.subject == null) {
            this.subject = new ArrayList<>(subject.size());
        }
        this.subject.addAll(subject);
    }

    public void setDescription(List<String> description) {
        if (this.description == null) {
            this.description = new ArrayList<>(description.size());
        }
        this.description.addAll(description);
    }

    public void setPublisher(List<String> publisher) {
        if (this.publisher == null) {
            this.publisher = new ArrayList<>(publisher.size());
        }
        this.publisher.addAll(publisher);
    }

    public void setContributor(List<String> contributor) {
        if (this.contributor == null) {
            this.contributor = new ArrayList<>(contributor.size());
        }
        this.contributor.addAll(contributor);
    }

    public void setDate(List<String> date) {
        if (this.date == null) {
            this.date = new ArrayList<>(date.size());
        }
        this.date.addAll(date);
    }

    public void setType(List<String> type) {
        if (this.type == null) {
            this.type = new ArrayList<>(type.size());
        }
        this.type.addAll(type);
    }

    public void setFormat(List<String> format) {
        if (this.format == null) {
            this.format = new ArrayList<>(format.size());
        }
        this.format.addAll(format);
    }

    public void setIdentifier(List<String> identifier) {
        if (this.identifier == null) {
            this.identifier = new ArrayList<>(identifier.size());
        }
        this.identifier.addAll(identifier);
    }

    public void setSource(List<String> source) {
        if (this.source == null) {
            this.source = new ArrayList<>(source.size());
        }
        this.source.addAll(source);
    }

    public void setLanguage(List<String> language) {
        if (this.language == null) {
            this.language = new ArrayList<>(language.size());
        }
        this.language.addAll(language);
    }

    public void setRelation(List<String> relation) {
        if (this.relation == null) {
            this.relation = new ArrayList<>(relation.size());
        }
        this.relation.addAll(relation);
    }

    public void setCoverage(List<String> coverage) {
        if (this.coverage == null) {
            this.coverage = new ArrayList<>(coverage.size());
        }
        this.coverage.addAll(coverage);
    }

    public void setRights(List<String> rights) {
        if (this.rights == null) {
            this.rights = new ArrayList<>(rights.size());
        }
        this.rights.addAll(rights);
    }
}
