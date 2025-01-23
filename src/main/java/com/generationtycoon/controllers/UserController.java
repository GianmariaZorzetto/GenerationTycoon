package com.generationtycoon.controllers;

import com.generationtycoon.controllers.exceptions.*;
import com.generationtycoon.controllers.helpers.ControllerHelper;
import com.generationtycoon.model.dto.*;
import com.generationtycoon.model.entities.Difficulty;
import com.generationtycoon.model.entities.User;
import com.generationtycoon.utils.credentials.CredentialService;
import com.generationtycoon.utils.score.ScoreLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public UserLeaderboardRespDto register(@RequestBody UserRegistrationReqDto dto) {
        // TODO pensare a qualcosa di ritorno.
        User user = cs.register(dto);
        return converter.toUserLeaderboardRespDto(user);
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
        User user = cs.getUserByToken();
        if (!user.getId().equals(id))
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
        User user = converter.toUser(dto);
        User updated = ch.updateUser(user);
        return converter.toUserLeaderboardRespDto(updated);
    }

    @ExceptionHandler(InvalidTokenException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleInvalidEmailException(InvalidTokenException e) {
        return e.getMessage();
    }

    @ExceptionHandler(InvalidPasswordException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public String handleInvalidPasswordException(InvalidPasswordException e) {
        return e.getMessage();
    }

    @ExceptionHandler(InvalidUsernameException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public String handleInvalidUsernameException(InvalidUsernameException e) {
        return e.getMessage();
    }

    @ExceptionHandler(UserMissingException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleUserMissingException(UserMissingException e) {
        return e.getMessage();
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleNullPointerException(NullPointerException e) {
        return e.getMessage();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleIllegalArgumentException(IllegalArgumentException e) {
        return e.getMessage();
    }

    @ExceptionHandler(InvalidEstimateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInvalidEstimateException(InvalidEstimateException e) {
        return e.getMessage();
    }
}
