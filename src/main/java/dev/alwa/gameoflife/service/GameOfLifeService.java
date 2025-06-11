package dev.alwa.gameoflife.service;

import java.util.List;

public interface GameOfLifeService {
    /**
     * Performs Game of Life simulation on the given seed for the specified number of iterations
     */
    List<List<Boolean>> simulate(List<List<Boolean>> seed, int iterations);
}
