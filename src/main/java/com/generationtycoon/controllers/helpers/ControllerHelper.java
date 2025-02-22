package com.generationtycoon.controllers.helpers;

import com.generationtycoon.controllers.exceptions.BrainjMissingException;
import com.generationtycoon.controllers.exceptions.KaboomMissingException;
import com.generationtycoon.controllers.exceptions.UserMissingException;
import com.generationtycoon.model.dto.*;
import com.generationtycoon.model.entities.Difficulty;
import com.generationtycoon.model.entities.UserTycoon;

import java.util.List;

/**
 * Interfaccia che definisce i metodi di un controller helper.
 */
public interface ControllerHelper {
    // TODO implementare i metodi non appena abbiamo fatto i DTO

    /**
     * Metodo per restituire tutti i kabooms.
     *
     * @return una lista con tutti i kabooms.
     */
    List<KaboomRespDto> getAllKabooms();

    /**
     * Metodo che restituisce un kaboom dato il suo id.
     *
     * @param id l'id del kaboom da cercare.
     * @return il kaboom.
     * @throws KaboomMissingException se il kaboom non è presente.
     */
    KaboomRespDto getKaboomById(Long id) throws KaboomMissingException;

    /**
     * Metodo che restituisce tutti i punteggi degli user per la leaderboard.
     *
     * @return la lista con tutti i punteggi.
     */
    List<UserLeaderboardRespDto> getAllUsersOnTheLeaderboard();


    /**
     * Metodo che restituisce tutti gli user data una {@link Difficulty}.
     *
     * @param difficulty la difficoltà in ingresso.
     * @return la lista con tutti gli user.
     */
    List<UserLeaderboardRespDto> getUsersByDifficulty(Difficulty difficulty);

    /**
     * Metodo che cancella un utente dato il suo id.
     *
     * @param id l'id.
     * @throws UserMissingException se lo user non è presente.
     */
    void deleteUser(Long id) throws UserMissingException;

    /**
     * Metodo che restituisce tutti i quiz di brainj.
     *
     * @return la lista con tutti i brainj.
     */
    List<BrainjRespDto> getAllBrainjs();

    /**
     * Metodo che restituisce un brainj dato il suo id.
     *
     * @param id l'id in ingresso.
     * @return il brainj.
     * @throws BrainjMissingException se il brainj non esiste.
     */
    BrainjRespDto getBrainjById(Long id) throws BrainjMissingException;

    /**
     * Metodo che aggiorna il punteggio di un utente.
     * Se l'utente non esiste, viene lanciata un'eccezione.
     *
     * @param userTycoon l'utente con le informazioni aggiornate.
     * @return l'utente aggiornato dopo il salvataggio nel database.
     * @throws UserMissingException se l'utente con l'id specificato non esiste.
     */
    UserTycoon updateUser(UserTycoon userTycoon);

    /**
     * Metodo che resetta i dati di un utente.
     * I campi email, password e username dell'utente originale vengono mantenuti,
     * mentre gli altri vengono sovrascritti con i valori forniti nella richiesta.
     * Se l'utente non esiste, viene lanciata un'eccezione.
     *
     * @param userResetReqDto i dati necessari per effettuare il reset.
     * @return l'utente resettato dopo il salvataggio nel database.
     * @throws UserMissingException se l'utente con l'id specificato non esiste.
     */
    UserTycoon resetUser(UserResetReqDto userResetReqDto);
}
