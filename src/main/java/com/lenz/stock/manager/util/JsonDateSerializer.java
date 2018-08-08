package com.lenz.stock.manager.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Used to serialize Java.util.Date, which is not a common JSON type, so we have
 * to create a custom serialize method;.
 */
@Component
public class JsonDateSerializer extends JsonSerializer<Date> {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    @Override
    public void serialize(final Date date, final JsonGenerator generator, final SerializerProvider provider) throws IOException, JsonProcessingException {
	String formattedDate = dateFormat.format(date);
	generator.writeString(formattedDate);
    }
}