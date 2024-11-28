package de.muenchen.oss.oai.pmh.starter.configuration.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import javax.xml.datatype.XMLGregorianCalendar;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class XMLGregorianCalendarSerializer extends JsonSerializer<XMLGregorianCalendar> {
    private final SimpleDateFormat iso8601DateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    @Override
    public void serialize(XMLGregorianCalendar value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // Convert XMLGregorianCalendar object to ISO8601-Format in UTC
        iso8601DateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String iso8601Date = iso8601DateFormat.format(value.toGregorianCalendar().getTime());
        gen.writeString(iso8601Date);
    }
}
