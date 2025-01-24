package com.generationtycoon.model.dto;

import com.generationtycoon.model.entities.Brainj;
import com.generationtycoon.model.entities.Kaboom;
import com.generationtycoon.model.entities.UserTycoon;
import org.springframework.stereotype.Service;

/**
 * Classe utils per la conversione dei DTO.
 */
@Service
public class DtoConverter {

    /**
     * Metodo che converte un {@link UserLoginReqDto} in un {@link UserTycoon}.
     *
     * @param dto l'oggetto in ingresso.
     * @return un {@code User} con i campi impostati.
     * @throws NullPointerException     se un parametro di {@code dto} è {@code null}.
     * @throws IllegalArgumentException se un parametro di {@code dto} non è conforme.
     */
    public UserTycoon userLoginReqDtoToEntity(UserLoginReqDto dto) throws NullPointerException, IllegalArgumentException {
        return UserTycoon.builder()
                .email(dto.email())
                .password(dto.password())
                .build();
    }

    /**
     * Metodo che converte un {@link UserRegistrationReqDto} in un {@link UserTycoon}.
     *
     * @param dto l'oggetto in ingresso.
     * @return un {@code User} con i campi impostati.
     * @throws NullPointerException     se un parametro di {@code dto} è {@code null}.
     * @throws IllegalArgumentException se un parametro di {@code dto} non è conforme.
     */
    public UserTycoon userRegistrationReqDtoToEntity(UserRegistrationReqDto dto) throws NullPointerException, IllegalArgumentException {
        return UserTycoon.builder()
                .email(dto.email())
                .username(dto.username())
                .password(dto.password())
                .difficulty(dto.difficulty())
                .score(0.0).build();
    }

    /**
     * Metodo che converte un {@link UserLeaderboardReqDto} in uno {@link UserTycoon}.
     *
     * @param dto l'oggetto in ingresso.
     * @return un {@code User} con i campi impostati.
     * @throws NullPointerException     se un parametro di {@code dto} è {@code null}.
     * @throws IllegalArgumentException se un parametro di {@code dto} non è conforme.
     */
    public UserTycoon userLeaderboardReqDtoToEntity(UserLeaderboardReqDto dto) throws NullPointerException, IllegalArgumentException {
        return UserTycoon.builder().username(dto.username()).build();
    }

    /**
     * Metodo che converte un {@link UserTycoon} in uno {@link UserLeaderboardRespDto}.
     *
     * @param userTycoon l'oggetto in ingresso.
     * @return un {@code UserLeaderboardRespDto} con i campi impostati.
     */
    public UserLeaderboardRespDto toUserLeaderboardRespDto(UserTycoon userTycoon) {
        return UserLeaderboardRespDto.builder()
                .username(userTycoon.getUsername())
                .difficulty(userTycoon.getDifficulty())
                .score(userTycoon.getScore())
                .build();
    }

    /**
     * Metodo che converte un {@link Kaboom} in un {@link KaboomRespDto}.
     *
     * @param kaboom l'oggetto in ingresso.
     * @return un {@code KaboomRespDto} con i campi impostati.
     */
    public KaboomRespDto toKaboomRespDto(Kaboom kaboom) {
        return KaboomRespDto.builder()
                .question(kaboom.getQuestion())
                .answer1(kaboom.getAnswer1())
                .answer2(kaboom.getAnswer2())
                .answer3(kaboom.getAnswer3())
                .answer4(kaboom.getAnswer4())
                .correctColor(kaboom.getCorrectColor())
                .build();
    }

    /**
     * Metodo che converte un {@link UserUpdateScoreReqDto} in un {@link UserTycoon}.
     *
     * @param dto il dto in ingresso.
     * @return uno {@code User} con le proprietà impostate.
     */
    public UserTycoon toUser(UserUpdateScoreReqDto dto) {
        return UserTycoon.builder().id(dto.id()).score(dto.score()).build();
    }

    /**
     * Metodo che converte {@link Brainj} in un {@link BrainjRespDto}.
     *
     * @param brainj il {@code Brainj} in ingresso.
     * @return uno {@code BrainjRespDto} con le proprietà impostate.
     */
    public BrainjRespDto toBrainjRespDto(Brainj brainj) {
        return BrainjRespDto.builder().question(brainj.getQuestion()).answer(brainj.getAnswer()).build();
    }

    public UserTycoon userReseReqDtoToEntity(UserResetReqDto dto)
    {
        return UserTycoon.builder().id(dto.id()).difficulty(dto.difficulty()).score(0.0).build();
    }
}
