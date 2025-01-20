package com.generationtycoon.model.dto;

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
        User user = new User();
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        return user;
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
        User user = new User();
        user.setEmail(dto.email());
        user.setUsername(dto.username());
        user.setPassword(dto.password());
        user.setDifficulty(dto.difficulty());
        user.setScore(0.0);
        return user;
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
        User user = new User();
        user.setUsername(dto.username());
        return user;
    }

    /**
     * Metodo che converte un {@link User} in uno {@link UserLeaderboardRespDto}.
     *
     * @param user l'oggetto in ingresso.
     * @return un {@code UserLeaderboardRespDto} con i campi impostati.
     */
    public UserLeaderboardRespDto toUserLeaderboardRespDto(User user) {
        return UserLeaderboardRespDto.of()
                .username(user.getUsername())
                .difficulty(user.getDifficulty())
                .score(user.getScore())
                .build();
    }


}
