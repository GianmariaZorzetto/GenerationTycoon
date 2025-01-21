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

	KaboomRespDto getKaboomById(long id);

	List<UserLeaderboardRespDto> getAllUsersOnTheLeaderboard();

	UserLoginRespDto getUserById(long id);

	List<UserLeaderboardRespDto> getUsersByDifficulty(Difficulty difficulty);

	UserLeaderboardRespDto modifyUserInLeaderboard(long id, UserLeaderboardReqDto dtoUser);

	void deleteUser(long id);
}
