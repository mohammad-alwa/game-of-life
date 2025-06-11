package dev.alwa.gameoflife.service;

import java.util.List;

public interface GameOfLifeService {
    List<List<Boolean>> simulate(List<List<Boolean>> seed, int iterations);
}
