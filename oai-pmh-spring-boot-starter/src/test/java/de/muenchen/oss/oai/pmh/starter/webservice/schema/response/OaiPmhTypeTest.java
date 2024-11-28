package de.muenchen.oss.oai.pmh.starter.webservice.schema.response;

import de.muenchen.oss.oai.pmh.schema.OaiPmhType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OaiPmhTypeTest {

    @Test
    void xmlNamespaceAttributesMustMatchConstants() {
        OaiPmhType oaiPmhType = new OaiPmhType();
        assertThat(oaiPmhType.xmlns).isEqualTo("http://www.openarchives.org/OAI/2.0/");
        assertThat(oaiPmhType.xsi).isEqualTo("http://www.w3.org/2001/XMLSchema-instance");
        assertThat(oaiPmhType.schemaLocation).isEqualTo("http://www.openarchives.org/OAI/2.0/ http://www.openarchives.org/OAI/2.0/OAI-PMH.xsd");
    }
}
