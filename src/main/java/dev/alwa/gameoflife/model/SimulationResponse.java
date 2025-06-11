package dev.alwa.gameoflife.model;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.alwa.gameoflife.config.BooleanMatrixNumericSerializer;

import java.util.List;

public record SimulationResponse(
        @JsonValue @JsonSerialize(using = BooleanMatrixNumericSerializer.class)
        List<List<Boolean>> grid) {
}
