package de.muenchen.oss.oai.pmh.starter.configuration.web;

import de.muenchen.oss.oai.pmh.starter.webservice.exceptions.BadArgumentException;
import org.springframework.core.convert.converter.Converter;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class StringToXmlGregorianCalendarConverter implements Converter<String, XMLGregorianCalendar> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public XMLGregorianCalendar convert(String source) {
        try {
            LocalDate.parse(source, formatter);
            DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
            return datatypeFactory.newXMLGregorianCalendar(source);
        } catch (DatatypeConfigurationException | IllegalArgumentException | NullPointerException | DateTimeParseException e) {
            throw new BadArgumentException("The value '%s' is not valid. Supported granularity is YYYY-MM-DD".formatted(source));
        }
    }
}
