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
 * Java class for granularityType.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;simpleType name="granularityType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="YYYY-MM-DD"/&gt;
 *     &lt;enumeration value="YYYY-MM-DDThh:mm:ssZ"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 */
@XmlType(name = "granularityType")
@XmlEnum
public enum GranularityType {

    @XmlEnumValue("YYYY-MM-DD") YYYY_MM_DD("YYYY-MM-DD"),
    @XmlEnumValue("YYYY-MM-DDThh:mm:ssZ") YYYY_MM_DD_THH_MM_SS_Z("YYYY-MM-DDThh:mm:ssZ");

    private final String value;

    GranularityType(String v) {
        value = v;
    }

    public static GranularityType fromValue(String v) {
        for (GranularityType c : GranularityType.values()) {
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