package de.muenchen.oss.oai.pmh.schema.rights;

import de.muenchen.oss.oai.pmh.schema.metadata.dublincore.OaiDcType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

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
 *       &lt;sequence&gt;
 *         &lt;any namespace='##other'/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "", propOrder = {
                "any"
        }
)
public class RightsDefinition {

    @XmlElement(name = "oai_dc:dc")
    public OaiDcType oaiDc;

    /**
     * Gets the value of the oaiDc property.
     *
     * @return possible object is
     *         {@link OaiDcType }
     */
    public OaiDcType getOaiDc() {
        return oaiDc;
    }

    /**
     * Sets the value of the oaiDc property.
     *
     * @param oaiDc allowed object is
     *            {@link OaiDcType }
     */
    public void setOaiDc(OaiDcType oaiDc) {
        this.oaiDc = oaiDc;
    }

}
