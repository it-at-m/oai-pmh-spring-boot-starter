//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0
// See https://eclipse-ee4j.github.io/jaxb-ri
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2023.10.23 at 08:28:04 AM CEST
//

package de.muenchen.oss.oai.pmh.schema.rights;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;

import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the de.muenchen.oss.oai.webservice.schema.response.rights package.
 * <p>
 * An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups. Factory methods for each of these are
 * provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Rights_QNAME = new QName("http://www.openarchives.org/OAI/2.0/rights/", "rights");
    private final static QName _RightsManifest_QNAME = new QName("http://www.openarchives.org/OAI/2.0/rights/", "rightsManifest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for
     * package: de.muenchen.oss.oai.webservice.schema.response.rights
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RightsStatementType }
     */
    public RightsStatementType createRightsStatementType() {
        return new RightsStatementType();
    }

    /**
     * Create an instance of {@link RightsManifestType }
     */
    public RightsManifestType createRightsManifestType() {
        return new RightsManifestType();
    }

    /**
     * Create an instance of {@link RightsReference }
     */
    public RightsReference createRightsStatementTypeRightsReference() {
        return new RightsReference();
    }

    /**
     * Create an instance of {@link RightsDefinition }
     */
    public RightsDefinition createRightsStatementTypeRightsDefinition() {
        return new RightsDefinition();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RightsStatementType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link RightsStatementType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.openarchives.org/OAI/2.0/rights/", name = "rights")
    public JAXBElement<RightsStatementType> createRights(RightsStatementType value) {
        return new JAXBElement<RightsStatementType>(_Rights_QNAME, RightsStatementType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RightsManifestType }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link RightsManifestType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.openarchives.org/OAI/2.0/rights/", name = "rightsManifest")
    public JAXBElement<RightsManifestType> createRightsManifest(RightsManifestType value) {
        return new JAXBElement<RightsManifestType>(_RightsManifest_QNAME, RightsManifestType.class, null, value);
    }

}