package com.generationtycoon.utils.score;

import com.generationtycoon.model.dto.UserScoreReqDto;
import com.generationtycoon.model.entities.Difficulty;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.FieldSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ScoreLogicTest {
    @Autowired
    private ScoreLogic scoreLogic;

    private static final List<UserScoreReqDto> SCORE_OK =
            List.of(
                    new UserScoreReqDto(LocalDateTime.of(2023, 10, 1, 10, 0),
                            LocalDateTime.of(2023, 10, 1, 11, 0),
                            1,
                            Difficulty.EASY),
                    new UserScoreReqDto(LocalDateTime.of(2023, 10, 1, 12, 0),
                            LocalDateTime.of(2023, 10, 1, 13, 0),
                            2,
                            Difficulty.MEDIUM),
                    new UserScoreReqDto(LocalDateTime.of(2023, 10, 1, 14, 0),
                            LocalDateTime.of(2023, 10, 1, 15, 0),
                            3,
                            Difficulty.HARD),
                    new UserScoreReqDto(LocalDateTime.of(2023, 10, 2, 10, 0),
                            LocalDateTime.of(2023, 10, 2, 11, 0),
                            1,
                            Difficulty.EASY),
                    new UserScoreReqDto(LocalDateTime.of(2023, 10, 2, 12, 0),
                            LocalDateTime.of(2023, 10, 2, 13, 0),
                            2,
                            Difficulty.MEDIUM),
                    new UserScoreReqDto(LocalDateTime.of(2023, 10, 2, 14, 0),
                            LocalDateTime.of(2023, 10, 2, 15, 0),
                            3,
                            Difficulty.HARD),
                    new UserScoreReqDto(LocalDateTime.of(2023, 10, 3, 10, 0),
                            LocalDateTime.of(2023, 10, 3, 11, 0),
                            1,
                            Difficulty.EASY),
                    new UserScoreReqDto(LocalDateTime.of(2023, 10, 3, 12, 0),
                            LocalDateTime.of(2023, 10, 3, 13, 0),
                            2,
                            Difficulty.MEDIUM),
                    new UserScoreReqDto(LocalDateTime.of(2023, 10, 3, 14, 0),
                            LocalDateTime.of(2023, 10, 3, 15, 0),
                            3,
                            Difficulty.HARD),
                    new UserScoreReqDto(LocalDateTime.of(2023, 10, 4, 10, 0),
                            LocalDateTime.of(2023, 10, 4, 11, 0),
                            1,
                            Difficulty.EASY)
            );

    private static final List<UserScoreReqDto> SCORE_NULL =
            List.of(
                    new UserScoreReqDto(null, LocalDateTime.now(), 1, Difficulty.EASY),
                    new UserScoreReqDto(LocalDateTime.now(), null, 1, Difficulty.EASY),
                    new UserScoreReqDto(LocalDateTime.now(), LocalDateTime.now().plusHours(1), 0, Difficulty.EASY),
                    new UserScoreReqDto(LocalDateTime.now(), LocalDateTime.now().plusHours(1), 1, null),
                    new UserScoreReqDto(LocalDateTime.now().plusHours(1), LocalDateTime.now(), 1, Difficulty.EASY),
                    new UserScoreReqDto(LocalDateTime.now(), LocalDateTime.now().plusHours(1), -1, Difficulty.EASY),
                    new UserScoreReqDto(LocalDateTime.now(), LocalDateTime.now().plusHours(1), 0, Difficulty.MEDIUM),
                    new UserScoreReqDto(null, LocalDateTime.now(), 1, null),
                    new UserScoreReqDto(LocalDateTime.now(), null, -1, Difficulty.HARD),
                    new UserScoreReqDto(LocalDateTime.now().plusHours(1), LocalDateTime.now(), 1, null),
                    new UserScoreReqDto(null, null, 1, Difficulty.EASY),
                    new UserScoreReqDto(LocalDateTime.now(), LocalDateTime.now().plusHours(1), -1, null),
                    new UserScoreReqDto(LocalDateTime.now().plusHours(1), LocalDateTime.now(), 0, Difficulty.MEDIUM),
                    new UserScoreReqDto(LocalDateTime.now(), null, 1, null),
                    new UserScoreReqDto(null, LocalDateTime.now().plusHours(1), 0, Difficulty.EASY)
            );

    @ParameterizedTest
    @FieldSource(value = "SCORE_OK")
    void testScore(UserScoreReqDto dto) {
        assertNotEquals(null, scoreLogic.calculateScore(dto));
    }

    @ParameterizedTest
    @FieldSource(value = "SCORE_NULL")
    void testScoreNull(UserScoreReqDto dto) {
        assertNull(scoreLogic.calculateScore(dto));
    }
}