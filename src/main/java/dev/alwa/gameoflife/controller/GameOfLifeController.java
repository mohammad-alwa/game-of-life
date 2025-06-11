package dev.alwa.gameoflife.controller;

import dev.alwa.gameoflife.model.SimulationRequest;
import dev.alwa.gameoflife.service.GameOfLifeService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public record GameOfLifeController(GameOfLifeService service) {

    @PostMapping("/simulation")

    @Operation(summary = "Game of Life Simulation",
            description = """
                    Performs a Game of Life simulation on the given grid and returns the final grid
                    status after the number of given iterations elapsed.
                    """)
    public List<List<Integer>> simulate(@Valid @RequestBody SimulationRequest request) {
        return service.simulate(request.seed(), request.iterations());
    }
}
