package dev.alwa.gameoflife.service;

import dev.alwa.gameoflife.error.CustomException;
import dev.alwa.gameoflife.error.ErrorCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameOfLifeServiceImpl implements GameOfLifeService {

    @Override
    public List<List<Boolean>> simulate(List<List<Boolean>> seed, int iterations) {
        int n = seed.size();
        int m = seed.getFirst().size();

        for (List<Boolean> row : seed) {
            if (row.size() != m) {
                throw new CustomException(ErrorCode.INVALID_GRID_DIMENSIONS);
            }
        }

        return List.of();
    }
}
