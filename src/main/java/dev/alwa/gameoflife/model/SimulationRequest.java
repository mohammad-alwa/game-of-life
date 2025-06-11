package dev.alwa.gameoflife.model;

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
                """, example = Constants.JSON_GRID)
        @NotEmpty
        @Size(min = 3)
        List<@Size(min = 3) List<@Min(0) @Max(1) Integer>> seed
) {
}
