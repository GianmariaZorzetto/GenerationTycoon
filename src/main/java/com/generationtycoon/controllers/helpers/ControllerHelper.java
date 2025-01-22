package com.generationtycoon.controllers.helpers;

import com.generationtycoon.model.dto.*;
import com.generationtycoon.model.entities.Difficulty;

import java.util.List;

/**
 * Interfaccia che definisce i metodi di un controller helper.
 */
public interface ControllerHelper {
    // TODO implementare i metodi non appena abbiamo fatto i DTO

    List<KaboomRespDto> getAllKabooms();

    KaboomRespDto getKaboomById(Long id);

    List<UserLeaderboardRespDto> getAllUsersOnTheLeaderboard();

    UserLoginRespDto getUserById(Long id);

    List<UserLeaderboardRespDto> getUsersByDifficulty(Difficulty difficulty);

    void deleteUser(Long id);

    List<BrainjRespDto> getAllBrainjs();

    BrainjRespDto getBrainjById(Long id);
}
