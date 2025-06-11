package dev.alwa.gameoflife.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.BitSet;
import java.util.List;

/**
 * Game of Life Grid to hold the data and perform simulation.
 * It assumes the grid is a toroidal array. So, the left and right edges are connected together, as well as the top and
 * bottom edges.
 */
public class Grid {

    private static final Logger log = LoggerFactory.getLogger(Grid.class);

    private final BitSet[] grid;
    private final int n; // rows count
    private final int m; // columns count

    /**
     * Constructor to initialize nxm grid with all cells set to false
     *
     * @param n number of rows
     * @param m number of columns
     */
    public Grid(int n, int m) {
        this.n = n;
        this.m = m;

        grid = new BitSet[n];
        for (int i = 0; i < n; i++) {
            grid[i] = new BitSet(m);
        }
    }

    /**
     * Constructor to initialize a grid based on an input grid
     *
     * @param listGrid input grid represented as a list of boolean lists. All inner lists must have the same length
     */
    public Grid(List<List<Boolean>> listGrid) {
        this.n = listGrid.size();
        this.m = listGrid.getFirst().size();

        grid = new BitSet[n];
        for (int i = 0; i < n; i++) {
            List<Boolean> row = listGrid.get(i);
            grid[i] = new BitSet(m);
            for (int j = 0; j < m; j++) {
                grid[i].set(j, row.get(j));
            }
        }
    }

    /**
     * Perform Game of Life simulation for the given number of iterations
     *
     * @param iterations number of iterations
     * @return final grid after all the iterations elapsed
     */
    public Grid simulate(int iterations) {
        log.debug("Simulating {}", this);
        Grid currentGrid = this;
        for (int iteration = 1; iteration <= iterations; iteration++) {
            Grid nextGrid = new Grid(n, m);

            /*
             prevRow and prevCol track the previous row/column relative to the current cell, while making sure the grid
             is infinite such that left-right and top-bottom edges are connected together yielding a toroidal array.
            */
            int prevRow = n - 1;
            for (int row = 0; row < n; row++) {
                int prevCol = m - 1;
                for (int col = 0; col < m; col++) {
                    boolean nextState = currentGrid.nextCellState(row, col, prevRow, prevCol);
                    nextGrid.set(row, col, nextState);
                    prevCol++;
                }
                prevRow++;
            }

            currentGrid = nextGrid;

            log.debug("Iteration {}\n{}", iteration, nextGrid);
        }
        return currentGrid;
    }

    public boolean get(int row, int col) {
        return grid[row].get(col);
    }

    private void set(int row, int col, boolean value) {
        grid[row].set(col, value);
    }

    /**
     * Calculates the next state after one iteration for the cell at row x col
     *
     * @param row     horizontal position for the cell
     * @param col     vertical position for the cell
     * @param prevRow horizontal position for the diagonal-adjacent top-left cell with respect to the toroidal array rules
     * @param prevCol vertical position for the diagonal-adjacent top-left cell with respect to the toroidal array rules
     * @return next state for the cell; `true` if alive or `false` if dead
     */
    private boolean nextCellState(int row, int col, int prevRow, int prevCol) {
        int aliveCells = surroundingAliveCellsCounts(row, col, prevRow, prevCol);
        if (aliveCells == 3) {
            // Any dead cell with three live neighbors becomes a live cell
            // Any live cell with two or "three" live neighbors survives
            return true;
        } else if (aliveCells == 2 && grid[row].get(col)) {
            // Any live cell with "two" or three live neighbors survives
            return true;
        } else {
            // All other live cells die in the next generation. Similarly, all other dead cells stay dead
            return false;
        }
    }

    /**
     * Calculates the number of alive cells surrounding the cell at row x col with respect to the toroidal array
     * adjacency rules
     *
     * @param row     horizontal position for the cell
     * @param col     vertical position for the cell
     * @param prevRow horizontal position for the diagonal-adjacent top-left cell with respect to the toroidal array rules
     * @param prevCol vertical position for the diagonal-adjacent top-left cell with respect to the toroidal array rules
     * @return number of the alive surrounding cells
     */

    private int surroundingAliveCellsCounts(int row, int col, int prevRow, int prevCol) {
        int count = 0;
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                if (grid[(prevRow + i) % n].get((prevCol + j) % m)) {
                    count++;
                }
            }
        }
        if (grid[row].get(col)) {
            count--;
        }
        return count;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(get(i, j) ? 1 : 0).append(' ');
            }
            if (i < n - 1) {
                sb.append('\n');
            }
        }
        return sb.toString();
    }
}
