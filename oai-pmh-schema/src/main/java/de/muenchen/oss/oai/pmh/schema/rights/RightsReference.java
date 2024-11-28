package de.muenchen.oss.oai.pmh.schema.rights;

import jakarta.xml.bind.annotation.*;

/**
 * <p>
 * Java class for anonymous complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="ref" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class RightsReference {

    @XmlAttribute(name = "ref", required = true)
    @XmlSchemaType(name = "anyURI")
    public String ref;

    /**
     * Gets the value of the ref property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getRef() {
        return ref;
    }

    /**
     * Sets the value of the ref property.
     *
     * @param value allowed object is
     *            {@link String }
     */
    public void setRef(String value) {
        this.ref = value;
    }

}
