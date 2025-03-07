//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0
// See https://eclipse-ee4j.github.io/jaxb-ri
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2023.10.23 at 08:06:18 AM CEST
//

package de.muenchen.oss.oai.pmh.schema.metadata.marc21;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Java class for recordType complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="recordType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence minOccurs="0"&gt;
 *         &lt;element name="leader" type="{http://www.loc.gov/MARC21/slim}leaderFieldType"/&gt;
 *         &lt;element name="controlfield" type="{http://www.loc.gov/MARC21/slim}controlFieldType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="datafield" type="{http://www.loc.gov/MARC21/slim}dataFieldType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="type" type="{http://www.loc.gov/MARC21/slim}recordTypeType" /&gt;
 *       &lt;attribute name="id" type="{http://www.loc.gov/MARC21/slim}idDataType" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "recordType", propOrder = {
                "leader",
                "controlfield",
                "datafield"
        }
)
@JacksonXmlRootElement(localName = "record")
public class RecordType implements Serializable {

    @Serial
    private static final long serialVersionUID = 3797612289566533481L;
    @XmlAttribute(name = "xmlns")
    public final String xmlns = "http://www.loc.gov/MARC21/slim";

    public LeaderFieldType leader;
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<ControlFieldType> controlfield;
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<DataFieldType> datafield;
    @XmlAttribute(name = "type")
    public RecordTypeType type;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    public String id;

    /**
     * Gets the value of the leader property.
     *
     * @return possible object is
     *         {@link LeaderFieldType }
     */
    public LeaderFieldType getLeader() {
        return leader;
    }

    /**
     * Sets the value of the leader property.
     *
     * @param value allowed object is
     *            {@link LeaderFieldType }
     */
    public void setLeader(LeaderFieldType value) {
        this.leader = value;
    }

    /**
     * Gets the value of the controlfield property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the controlfield property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     *
     * <pre>
     * getControlfield().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ControlFieldType }
     */
    public List<ControlFieldType> getControlfield() {
        if (controlfield == null) {
            controlfield = new ArrayList<ControlFieldType>();
        }
        return this.controlfield;
    }

    public void setControlfield(List<ControlFieldType> controlfield) {
        this.controlfield = controlfield;
    }

    /**
     * Gets the value of the datafield property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the datafield property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     *
     * <pre>
     * getDatafield().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataFieldType }
     */
    public List<DataFieldType> getDatafield() {
        if (datafield == null) {
            datafield = new ArrayList<DataFieldType>();
        }
        return this.datafield;
    }

    public void setDatafield(List<DataFieldType> datafield) {
        this.datafield = datafield;
    }

    /**
     * Gets the value of the type property.
     *
     * @return possible object is
     *         {@link RecordTypeType }
     */
    public RecordTypeType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     *
     * @param value allowed object is
     *            {@link RecordTypeType }
     */
    public void setType(RecordTypeType value) {
        this.type = value;
    }

    /**
     * Gets the value of the id property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value allowed object is
     *            {@link String }
     */
    public void setId(String value) {
        this.id = value;
    }

}
