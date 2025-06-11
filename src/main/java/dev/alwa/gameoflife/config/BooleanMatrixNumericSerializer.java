package dev.alwa.gameoflife.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

/**
 * Serializer for a boolean matrix as a numeric 1/0 matrix.
 */
public class BooleanMatrixNumericSerializer extends JsonSerializer<List<List<Boolean>>> {

    @Override
    public void serialize(List<List<Boolean>> value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartArray();
        for (List<Boolean> booleans : value) {
            gen.writeStartArray();
            for (Boolean b : booleans) {
                gen.writeNumber(Boolean.TRUE.equals(b) ? 1 : 0);
            }
            gen.writeEndArray();
        }
        gen.writeEndArray();
    }
}
