package com.generationtycoon.model.dto;

import com.generationtycoon.model.entities.Kaboom;
import com.generationtycoon.model.entities.User;
import org.springframework.stereotype.Service;

/**
 * Classe utils per la conversione dei DTO.
 */
@Service
public class DtoConverter {

    /**
     * Metodo che converte un {@link UserLoginReqDto} in un {@link User}.
     *
     * @param dto l'oggetto in ingresso.
     * @return un {@code User} con i campi impostati.
     * @throws NullPointerException     se un parametro di {@code dto} è {@code null}.
     * @throws IllegalArgumentException se un parametro di {@code dto} non è conforme.
     */
    public User userLoginReqDtoToEntity(UserLoginReqDto dto) throws NullPointerException, IllegalArgumentException {
        return User.builder()
                .email(dto.email())
                .password(dto.password())
                .build();
    }

    public UserLoginRespDto toUserLoginRespDto(User user) {
        //TODO genera token
        return null;
    }

    /**
     * Metodo che converte un {@link UserRegistrationReqDto} in un {@link User}.
     *
     * @param dto l'oggetto in ingresso.
     * @return un {@code User} con i campi impostati.
     * @throws NullPointerException     se un parametro di {@code dto} è {@code null}.
     * @throws IllegalArgumentException se un parametro di {@code dto} non è conforme.
     */
    public User userRegistrationReqDtoToEntity(UserRegistrationReqDto dto) throws NullPointerException, IllegalArgumentException {
        return User.builder()
                .email(dto.email())
                .username(dto.username())
                .password(dto.password())
                .difficulty(dto.difficulty())
                .score(0.0).build();
    }

    /**
     * Metodo che converte un {@link UserLeaderboardReqDto} in uno {@link User}.
     *
     * @param dto l'oggetto in ingresso.
     * @return un {@code User} con i campi impostati.
     * @throws NullPointerException     se un parametro di {@code dto} è {@code null}.
     * @throws IllegalArgumentException se un parametro di {@code dto} non è conforme.
     */
    public User userLeaderboardReqDtoToEntity(UserLeaderboardReqDto dto) throws NullPointerException, IllegalArgumentException {
        return User.builder().username(dto.username()).build();
    }

    /**
     * Metodo che converte un {@link User} in uno {@link UserLeaderboardRespDto}.
     *
     * @param user l'oggetto in ingresso.
     * @return un {@code UserLeaderboardRespDto} con i campi impostati.
     */
    public UserLeaderboardRespDto toUserLeaderboardRespDto(User user) {
        return UserLeaderboardRespDto.builder()
                .username(user.getUsername())
                .difficulty(user.getDifficulty())
                .score(user.getScore())
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

    public User toUser(UserUpdateScoreReqDto dto) {
        return User.builder().id(dto.id()).score(dto.score()).build();
    }
}
