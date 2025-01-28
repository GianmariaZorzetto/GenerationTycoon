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

/**
 * Controller per la gestione delle operazioni relative agli utenti nel sistema.
 * <p>
 * Questo controller si occupa delle operazioni di login, registrazione, aggiornamento e cancellazione degli utenti.
 * Inoltre, fornisce metodi per recuperare i punteggi degli utenti e altre informazioni relative.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final ControllerHelper ch;
    private final CredentialService cs;
    private final ScoreLogic sl;
    private final DtoConverter converter;

    /**
     * Costruttore per l'iniezione delle dipendenze.
     *
     * @param ch      il {@link ControllerHelper} per eseguire le operazioni logiche.
     * @param cs il {@link CredentialService} per la gestione delle credenziali.
     * @param sl      lo {@link ScoreLogic} per calcolare e gestire i punteggi degli utenti.
     * @param converter il {@link DtoConverter} per la conversione dei DTO tra il modello di dominio e le risposte del controller.
     */
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


    /**
     * Endpoint per la registrazione di un nuovo utente.
     * <p>
     * Questo metodo gestisce la registrazione di un utente attraverso i dati forniti dal client.
     * Una volta registrato l'utente, il metodo genera automaticamente una richiesta di login per autenticare l'utente
     * appena registrato e restituisce un token di accesso.
     *
     * @param dto il {@link UserRegistrationReqDto} contenente i dati per la registrazione dell'utente,
     *            inclusi l'email, il nome utente, la password e la difficoltà selezionata.
     *
     * @return un oggetto {@link UserLoginRespDto} contenente il token di accesso dell'utente,
     *         il suo ID, il nome utente, il punteggio e la difficoltà selezionata.
     *
     * @throws NullPointerException se {@code dto} è {@code null}.
     * @throws com.generationtycoon.controllers.exceptions.InvalidEmailException se l'email fornita non è valida.
     * @throws com.generationtycoon.controllers.exceptions.InvalidPasswordException se la password non soddisfa i criteri di validità.
     */
    @PostMapping("/register")
    public UserLoginRespDto register(@RequestBody UserRegistrationReqDto dto) {
        cs.register(dto);
        UserLoginReqDto dtoLogin =
                UserLoginReqDto.builder().email(dto.email()).password(dto.password()).build();
        return cs.login(dtoLogin);
    }

    /**
     * Endpoint per effettuare il login di un utente.
     *<p>
     * Questo metodo riceve una richiesta di login contenente l'email e la password dell'utente,
     * e restituisce un oggetto {@link UserLoginRespDto} contenente i dati dell'utente loggato.
     * Se le credenziali sono errate, vengono sollevate delle eccezioni appropriate.
     *
     * @param dto Oggetto contenente le credenziali di login dell'utente. Non può essere null.
     * @return Un oggetto {@link UserLoginRespDto} contenente il token di autenticazione, l'id, l'username,
     *         il punteggio e la difficoltà dell'utente. Non può essere null.
     * @throws NullPointerException se {@code dto} è null.
     * @throws com.generationtycoon.controllers.exceptions.InvalidEmailException se l'email fornita non corrisponde a nessun utente registrato.
     * @throws com.generationtycoon.controllers.exceptions.InvalidPasswordException se la password fornita non corrisponde a quella dell'utente.
     */
    @PostMapping("/login")
    public UserLoginRespDto login(@RequestBody UserLoginReqDto dto) {
        return cs.login(dto);
    }

    /**
     * Endpoint per calcolare lo score di un utente in base ai dati forniti.
     *<p>
     * Questo metodo riceve una richiesta contenente i dati relativi a un quiz completato,
     * come gli orari di inizio e fine, gli hp rimanenti e la difficoltà del quiz.
     * Calcola lo score dell'utente utilizzando la logica di calcolo definita nel servizio {@link ScoreLogic}.
     *
     * @param dto Oggetto contenente i dati del quiz. Non può essere null.
     * @return Un oggetto {@link UserScoreRespDto} contenente il punteggio calcolato. Non può essere null.
     * @throws InvalidEstimateException se il calcolo dello score non è possibile (ad esempio, se i dati sono incompleti o non validi).
     */
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
