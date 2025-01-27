package com.generationtycoon.controllers;

import com.generationtycoon.controllers.exceptions.InvalidEstimateException;
import com.generationtycoon.controllers.exceptions.InvalidTokenException;
import com.generationtycoon.controllers.helpers.ControllerHelper;
import com.generationtycoon.model.dto.*;
import com.generationtycoon.model.entities.Difficulty;
import com.generationtycoon.model.entities.UserTycoon;
import com.generationtycoon.utils.credentials.CredentialService;
import com.generationtycoon.utils.score.ScoreLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final ControllerHelper ch;
    private final CredentialService cs;
    private final ScoreLogic sl;
    private final DtoConverter converter;

    @Autowired
    public UserController(ControllerHelper ch,
                          CredentialService cs,
                          ScoreLogic sl,
                          DtoConverter converter) {
        this.ch = ch;
        this.cs = cs;
        this.sl = sl;
        this.converter = converter;
    }

    @PostMapping("/register")
    public UserLoginRespDto register(@RequestBody UserRegistrationReqDto dto) {
        cs.register(dto);
        UserLoginReqDto dtoLogin =
                UserLoginReqDto.builder().email(dto.email()).password(dto.password()).build();
        return cs.login(dtoLogin);
    }

    @PostMapping("/login")
    public UserLoginRespDto login(@RequestBody UserLoginReqDto dto) {
        return cs.login(dto);
    }


    @PostMapping("/score")
    public UserScoreRespDto calculateScore(@RequestBody UserScoreReqDto dto) {
        cs.getUserByToken();
        Double score = sl.calculateScore(dto);
        if (score == null)
            throw new InvalidEstimateException("Impossibile calcolare lo score");
        return new UserScoreRespDto(score);
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        UserTycoon userTycoon = cs.getUserByToken();
        if (!userTycoon.getId().equals(id))
            throw new InvalidTokenException("Non puoi cancellare questo utente.");
        ch.deleteUser(id);
        return id;
    }

    @GetMapping
    public List<UserLeaderboardRespDto> getAll() {
        cs.getUserByToken();
        return ch.getAllUsersOnTheLeaderboard();
    }

    @GetMapping("/{difficulty}")
    public List<UserLeaderboardRespDto> getAllByDifficulty(@PathVariable String difficulty) {
        cs.getUserByToken();
        return ch.getUsersByDifficulty(Difficulty.fromString(difficulty));
    }

    @PutMapping("/newScore")
    public UserLeaderboardRespDto updateScore(@RequestBody UserUpdateScoreReqDto dto) {
        cs.getUserByToken();
        UserTycoon userTycoon = converter.toUser(dto);
        UserTycoon updated = ch.updateUser(userTycoon);
        return converter.toUserLeaderboardRespDto(updated);
    }

    @PutMapping("/reset")
    public UserLoginRespDto reset(@RequestBody UserResetReqDto dto) {
        cs.getUserByToken();
        UserTycoon userTycoon = ch.resetUser(dto);
        return UserLoginRespDto.builder()
                .id(userTycoon.getId())
                .token(dto.token())
                .score(userTycoon.getScore())
                .username(userTycoon.getUsername())
                .difficulty(userTycoon.getDifficulty())
                .build();
    }
}
