package com.generationtycoon.utils.credentials;

import com.generationtycoon.controllers.exceptions.InvalidEmailException;
import com.generationtycoon.controllers.exceptions.InvalidPasswordException;
import com.generationtycoon.model.dto.UserLoginReqDto;
import com.generationtycoon.model.dto.UserLoginRespDto;
import com.generationtycoon.model.dto.UserRegistrationReqDto;
import com.generationtycoon.model.entities.User;
import com.generationtycoon.model.repositories.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

@Service
public class CredentialService {
    private final UserRepository userRepo;

    public CredentialService(@Autowired UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public UserLoginRespDto login(UserLoginReqDto dto)
            throws NullPointerException, InvalidEmailException, InvalidPasswordException {
        User user = userRepo
                .findByEmail(Objects.requireNonNull(dto, "Request null.").email())
                .orElseThrow(() -> new InvalidEmailException("Utente non trovato."));
        String passwordHashed = DigestUtils.md5Hex(dto.password());
        if (!user.getPassword().equals(passwordHashed))
            throw new InvalidPasswordException("Password non corretta.");
        // TODO creazione di un token.
        return new UserLoginRespDto(user.getEmail());
    }

    public User register(UserRegistrationReqDto dto) throws NullPointerException, IllegalArgumentException {
        String passwordHashed = DigestUtils.md5Hex(Objects.requireNonNull(dto, "Dto null").password());
        return userRepo.save(
                User.builder()
                        .email(dto.email())
                        .password(passwordHashed)
                        .username(dto.username())
                        .score(0.0)
                        .difficulty(dto.difficulty())
                        .build()
        );
    }

    public User getUserByToken() {
        // TODO fare logica non appena fatta logica creazione di un token.
        ServletRequestAttributes req = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String token =
                Objects.requireNonNull(req, "Errore, request null.")
                        .getRequest()
                        .getHeader("token");

        return null;
    }
}
