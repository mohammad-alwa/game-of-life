package dev.alwa.gameoflife.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.util.List;

public record SimulationRequest(

        @Schema(description = "Number of iterations the simulation will end after", example = "3")
        @NotNull
        @Positive
        int iterations,

        @Schema(description = """
                The initial (seed) pattern represented as a dense grid.
                Each element value indicates whether the cell is alive (1) or dead (0).
                All rows must have the same length.
                """, example = """
                [
                  [0, 0, 0, 0, 0],
                  [0, 0, 0, 0, 0],
                  [0, 1, 1, 1, 0],
                  [0, 0, 0, 0, 0],
                  [0, 0, 0, 0, 0]
                ]
                """)
        @NotEmpty
        @Size(min = 3)
        @JsonFormat(shape = JsonFormat.Shape.NUMBER)
        List<@Size(min = 3) List<Boolean>> seed
) {
}
