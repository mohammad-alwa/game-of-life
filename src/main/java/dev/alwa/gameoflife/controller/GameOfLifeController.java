package dev.alwa.gameoflife.controller;

import dev.alwa.gameoflife.model.Constants;
import dev.alwa.gameoflife.model.SimulationRequest;
import dev.alwa.gameoflife.service.GameOfLifeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@RestController
public record GameOfLifeController(GameOfLifeService service) {

    @PostMapping(value = "/simulation", produces = {APPLICATION_JSON_VALUE, TEXT_PLAIN_VALUE})
    @Operation(summary = "Game of Life Simulation",
            description = """
                    Performs a Game of Life simulation on the given grid and returns the final grid
                    status after the number of given iterations elapsed.
                    This API can return Json or text response based on the `Accept` header.
                    """,
            responses = @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(type = "array", implementation = List.class, example = Constants.JSON_GRID)),
                    @Content(mediaType = TEXT_PLAIN_VALUE,
                            schema = @Schema(type = "string"))
            })
    )
    public ResponseEntity<?> simulate(@Valid @RequestBody SimulationRequest simulationRequest,
                                      HttpServletRequest httpRequest) {
        String accept = httpRequest.getHeader("accept");
        if (accept != null && accept.contains(TEXT_PLAIN_VALUE)) {
            var res = service.simulateAsText(simulationRequest.seed(), simulationRequest.iterations());
            return ResponseEntity.ok(res);
        } else {
            var res = service.simulate(simulationRequest.seed(), simulationRequest.iterations());
            return ResponseEntity.ok(res);
        }
    }
}
