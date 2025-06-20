package dev.alwa.gameoflife.service;

import dev.alwa.gameoflife.error.CustomException;
import dev.alwa.gameoflife.error.ErrorCode;
import dev.alwa.gameoflife.model.Grid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
public class GameOfLifeServiceImpl implements GameOfLifeService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<List<Integer>> simulate(@NotEmpty @Size List<List<Integer>> seed, int iterations) {
        return simulate(seed, iterations, this::toResult);
    }

    @Override
    public String simulateAsText(List<List<Integer>> seed, int iterations) {
        return simulate(seed, iterations, Grid::toString);
    }

    private <T> T simulate(List<List<Integer>> seed, int iterations, Function<Grid, T> responseSupplier) {
        int n = seed.size();
        int m = seed.getFirst().size();

        validate(seed, n, m);

        Grid grid = new Grid(seed).simulate(iterations);

        return responseSupplier.apply(grid);
    }

    /**
     * Validates all the rows of the seed have the same length
     */
    private void validate(List<List<Integer>> seed, int n, int m) {
        for (int i = 0; i < n; i++) {
            var row = seed.get(i);
            if (row.size() != m) {
                throw new CustomException(ErrorCode.INVALID_GRID_DIMENSIONS);
            }
        }
    }

    /**
     * Converts the grid object to the appropriate output response
     */
    private List<List<Integer>> toResult(Grid grid) {
        var result = new ArrayList<List<Integer>>();
        for (int i = 0; i < grid.n(); i++) {
            var row = new ArrayList<Integer>(grid.m());
            result.add(row);
            for (int j = 0; j < grid.m(); j++) {
                row.add(grid.get(i, j) ? 1 : 0);
            }
        }
        return result;
    }
}
