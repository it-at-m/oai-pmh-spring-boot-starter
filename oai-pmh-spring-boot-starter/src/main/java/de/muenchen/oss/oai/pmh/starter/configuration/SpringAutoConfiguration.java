package de.muenchen.oss.oai.pmh.starter.configuration;

import de.muenchen.oss.oai.pmh.starter.configuration.jackson.JacksonConfiguration;
import de.muenchen.oss.oai.pmh.starter.configuration.web.WebMvcConfiguration;
import de.muenchen.oss.oai.pmh.starter.webservice.RequestProcessor;
import de.muenchen.oss.oai.pmh.starter.webservice.RequestProcessorImplementation;
import de.muenchen.oss.oai.pmh.starter.webservice.controller.OaiPmhController;
import de.muenchen.oss.oai.pmh.starter.webservice.controller.OaiPmhControllerAdvice;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@Import({ JacksonConfiguration.class, WebMvcConfiguration.class, SpringdocParamsConfiguration.class })
public class SpringAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    OaiPmhController oaiPmhController() {
        return new OaiPmhController();
    }

    @Bean
    @ConditionalOnMissingBean
    OaiPmhControllerAdvice oaiPmhControllerAdvice() {
        return new OaiPmhControllerAdvice();
    }

    @Bean
    @ConditionalOnMissingBean(RequestProcessor.class)
    RequestProcessor requestProcessor() {
        return new RequestProcessorImplementation();
    }
}
