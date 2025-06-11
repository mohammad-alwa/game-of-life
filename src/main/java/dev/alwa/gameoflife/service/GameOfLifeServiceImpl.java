package dev.alwa.gameoflife.service;

import dev.alwa.gameoflife.error.CustomException;
import dev.alwa.gameoflife.error.ErrorCode;
import dev.alwa.gameoflife.model.Grid;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameOfLifeServiceImpl implements GameOfLifeService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<List<Boolean>> simulate(List<List<Boolean>> seed, int iterations) {
        int n = seed.size();
        int m = seed.getFirst().size();

        validate(seed, n, m);

        Grid grid = new Grid(seed).simulate(iterations);

        return toResult(grid, n, m);
    }

    /**
     * Validates all the rows of the seed have the same length
     */
    private void validate(List<List<Boolean>> seed, int n, int m) {
        for (int i = 0; i < n; i++) {
            List<Boolean> row = seed.get(i);
            if (row.size() != m) {
                throw new CustomException(ErrorCode.INVALID_GRID_DIMENSIONS);
            }
        }
    }

    /**
     * Converts the grid object to the appropriate output response
     */
    private List<List<Boolean>> toResult(Grid grid, int n, int m) {
        var result = new ArrayList<List<Boolean>>();
        for (int i = 0; i < n; i++) {
            var row = new ArrayList<Boolean>(m);
            result.add(row);
            for (int j = 0; j < m; j++) {
                row.add(grid.get(i, j));
            }
        }
        return result;
    }
}
