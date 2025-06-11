package dev.alwa.gameoflife.service;

import dev.alwa.gameoflife.error.CustomException;
import dev.alwa.gameoflife.error.ErrorCode;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GameOfLifeServiceImplTest {
    final GameOfLifeService service = new GameOfLifeServiceImpl();

    @Test
    void validatesAllThePatternRowsHaveTheSameLength() {
        var pattern = List.of(
                List.of(1, 1, 1),
                List.of(1, 1, 1),
                List.of(1, 1, 1, 1)
        );

        assertThatThrownBy(() -> service.simulate(pattern, 3))
                .isInstanceOf(CustomException.class)
                .hasFieldOrPropertyWithValue("errorCode", ErrorCode.INVALID_GRID_DIMENSIONS);
    }

    @Test
    void simulatesStillLife_Beehive() {
        var pattern = toPattern(new int[][]{
                {0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 0, 0},
                {0, 1, 0, 0, 1, 0},
                {0, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0},
        });

        assertThat(service.simulate(pattern, 1)).isEqualTo(pattern);
        assertThat(service.simulate(pattern, 2)).isEqualTo(pattern);
        assertThat(service.simulate(pattern, 100)).isEqualTo(pattern);
    }

    @Test
    void simulatesOscillator_2period_blinker() {
        var period1 = toPattern(new int[][]{
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        });
        var period2 = toPattern(new int[][]{
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0}
        });

        assertThat(service.simulate(period1, 1)).isEqualTo(period2);
        assertThat(service.simulate(period1, 2)).isEqualTo(period1);
        assertThat(service.simulate(period1, 99)).isEqualTo(period2);
        assertThat(service.simulate(period1, 100)).isEqualTo(period1);
    }

    @Test
    void simulatesSpaceship_glider() {
        var period1 = toPattern(new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0},
                {0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
        });

        var period2 = toPattern(new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 1, 0, 0},
                {0, 0, 0, 1, 1, 0, 0},
                {0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
        });

        var period3 = toPattern(new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0},
                {0, 0, 1, 0, 1, 0, 0},
                {0, 0, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
        });

        var period4 = toPattern(new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 0},
                {0, 0, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
        });

        var period5 = toPattern(new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
        });

        assertThat(service.simulate(period1, 1)).isEqualTo(period2);
        assertThat(service.simulate(period1, 2)).isEqualTo(period3);
        assertThat(service.simulate(period1, 3)).isEqualTo(period4);
        assertThat(service.simulate(period1, 4)).isEqualTo(period5);
    }

    List<List<Integer>> toPattern(int[][] pattern) {
        var res = new ArrayList<List<Integer>>();
        for (int[] p : pattern) {
            List<Integer> row = Arrays.stream(p)
                    .boxed()
                    .toList();
            res.add(row);
        }
        return res;
    }
}
