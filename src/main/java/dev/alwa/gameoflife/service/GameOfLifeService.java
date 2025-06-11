package dev.alwa.gameoflife.service;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public interface GameOfLifeService {
    /**
     * Performs Game of Life simulation on the given seed for the specified number of iterations
     */
    List<List<Integer>> simulate(@NotEmpty @Size List<List<Integer>> seed, int iterations);

    /**
     * Performs Game of Life simulation on the given seed for the specified number of iterations
     *
     * @return textual representation of the final simulation result
     */
    String simulateAsText(@NotEmpty @Size List<List<Integer>> seed, int iterations);
}
