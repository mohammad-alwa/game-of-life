package dev.alwa.gameoflife.service;

import dev.alwa.gameoflife.error.CustomException;
import dev.alwa.gameoflife.error.ErrorCode;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GameOfLifeServiceImplTest {
    final GameOfLifeService service = new GameOfLifeServiceImpl();

    @Test
    void validatesAllThePatternRowsHaveTheSameLength() {
        var pattern = List.of(
                List.of(true, true, true),
                List.of(true, true, true),
                List.of(true, true, true, true)
        );

        assertThatThrownBy(() -> service.simulate(pattern, 3))
                .isInstanceOf(CustomException.class)
                .hasFieldOrPropertyWithValue("errorCode", ErrorCode.INVALID_GRID_DIMENSIONS);
    }
}
