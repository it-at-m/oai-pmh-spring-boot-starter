//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0
// See https://eclipse-ee4j.github.io/jaxb-ri
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2023.08.31 at 09:34:26 AM CEST
//

package de.muenchen.oss.oai.pmh.schema;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for OAI-PMHerrorcodeType.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;simpleType name="OAI-PMHerrorcodeType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="cannotDisseminateFormat"/&gt;
 *     &lt;enumeration value="idDoesNotExist"/&gt;
 *     &lt;enumeration value="badArgument"/&gt;
 *     &lt;enumeration value="badVerb"/&gt;
 *     &lt;enumeration value="noMetadataFormats"/&gt;
 *     &lt;enumeration value="noRecordsMatch"/&gt;
 *     &lt;enumeration value="badResumptionToken"/&gt;
 *     &lt;enumeration value="noSetHierarchy"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 */
@XmlType(name = "OAI-PMHerrorcodeType")
@XmlEnum
public enum OaiPmhErrorCodeType {

    @XmlEnumValue("cannotDisseminateFormat") CANNOT_DISSEMINATE_FORMAT("cannotDisseminateFormat"),
    @XmlEnumValue("idDoesNotExist") ID_DOES_NOT_EXIST(
            "idDoesNotExist"),
    @XmlEnumValue("badArgument") BAD_ARGUMENT("badArgument"),
    @XmlEnumValue("badVerb") BAD_VERB(
            "badVerb"),
    @XmlEnumValue("noMetadataFormats") NO_METADATA_FORMATS("noMetadataFormats"),
    @XmlEnumValue("noRecordsMatch") NO_RECORDS_MATCH(
            "noRecordsMatch"),
    @XmlEnumValue("badResumptionToken") BAD_RESUMPTION_TOKEN(
            "badResumptionToken"),
    @XmlEnumValue("noSetHierarchy") NO_SET_HIERARCHY("noSetHierarchy");

    private final String value;

    OaiPmhErrorCodeType(String v) {
        value = v;
    }

    public static OaiPmhErrorCodeType fromValue(String v) {
        for (OaiPmhErrorCodeType c : OaiPmhErrorCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public String value() {
        return value;
    }

}
