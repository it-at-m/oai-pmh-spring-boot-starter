package de.muenchen.oss.oai.pmh.starter.configuration.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.fasterxml.jackson.module.jakarta.xmlbind.JakartaXmlBindAnnotationModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;

import javax.xml.datatype.XMLGregorianCalendar;

/*
 * Any beans of type com.fasterxml.jackson.databind.Module are automatically registered with the
 * autoconfigured Jackson2ObjectMapperBuilder and
 * are applied to any ObjectMapper instances that it creates. This provides a global mechanism for
 * contributing custom modules when you add new features to your application.
 */
@Configuration
public class JacksonConfiguration {

    /*
     * Register JakartaXmlBindAnnotationModule to the ObjectMapper.
     * This Jackson extension module provides support for using Jakarta Java XML Binding
     * (jakarta.xml.bind) annotations
     * as an alternative to native Jackson annotations.
     *
     * https://github.com/FasterXML/jackson-modules-base/tree/2.16/jakarta-xmlbind
     */
    @Bean
    Module jakartaXmlBindAnnotationModule() {
        return new JakartaXmlBindAnnotationModule();
    }

    /*
     * Enable jackson Feature 'WRITE_XML_DECLARATION'.
     * This feature writes the xml declaration <?xml version="1.0" encoding="UTF-8"?>
     * automatically to the response body
     */
    @Bean
    public MappingJackson2XmlHttpMessageConverter mappingJackson2XmlHttpMessageConverter(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper mapper = builder.createXmlMapper(true).build();

        // Enable jackson Feature 'WRITE_XML_DECLARATION'. This feature writes the xml declaration <?xml version="1.0" encoding="UTF-8"?> automatically to the response body
        ((XmlMapper) mapper).enable(ToXmlGenerator.Feature.WRITE_XML_DECLARATION);

        // the output won't be in just one line with this feature
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        // Add a serializer for XMLGregorianCalendar objects
        // This serializer converts XMLGregorianCalendar objects to an ISO8601 string
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(XMLGregorianCalendar.class, new XMLGregorianCalendarSerializer());
        mapper.registerModule(simpleModule);

        // don't include empty properties in xml
        mapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_EMPTY);

        return new MappingJackson2XmlHttpMessageConverter(mapper);
    }
}
