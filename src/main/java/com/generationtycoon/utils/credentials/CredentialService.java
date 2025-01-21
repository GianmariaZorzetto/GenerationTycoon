package com.generationtycoon.utils.credentials;

import com.generationtycoon.controllers.exceptions.InvalidEmailException;
import com.generationtycoon.controllers.exceptions.InvalidPasswordException;
import com.generationtycoon.controllers.exceptions.InvalidTokenException;
import com.generationtycoon.controllers.exceptions.UserMissingException;
import com.generationtycoon.model.dto.UserLoginReqDto;
import com.generationtycoon.model.dto.UserLoginRespDto;
import com.generationtycoon.model.dto.UserRegistrationReqDto;
import com.generationtycoon.model.entities.User;
import com.generationtycoon.model.repositories.UserRepository;
import com.generationtycoon.utils.validator.Validator;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.StringJoiner;

@Service
public class CredentialService {
    private final UserRepository userRepo;

    public CredentialService(@Autowired UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    private String generateToken(String email) {
        StringJoiner sj = new StringJoiner("-");
        int offset = new Random().nextInt(20);
        sj.add(String.valueOf(offset));
        for (char c : email.toCharArray())
            sj.add(String.valueOf(c + offset));
        return sj.toString();
    }

    private String convertToken(String token) {
        String[] parts = token.split("-");
        StringBuilder t = new StringBuilder();
        int offset = Integer.parseInt(parts[0]);
        Arrays.stream(parts).skip(1).forEach(part -> {
            int byteInt = Integer.parseInt(part) - offset;
            t.append((char) byteInt);
        });
        return t.toString();
    }

    public UserLoginRespDto login(UserLoginReqDto dto)
            throws NullPointerException, InvalidEmailException, InvalidPasswordException {
        User user = userRepo
                .findByEmail(Objects.requireNonNull(dto, "Request null.").email())
                .orElseThrow(() -> new InvalidEmailException("Utente non trovato."));
        String passwordHashed = DigestUtils.md5Hex(dto.password());
        if (!user.getPassword().equals(passwordHashed))
            throw new InvalidPasswordException("Password non corretta.");
        return new UserLoginRespDto(generateToken(user.getEmail()));
    }

    public User register(UserRegistrationReqDto dto)
            throws NullPointerException, InvalidPasswordException, IllegalArgumentException {
        String psw = Objects.requireNonNull(dto, "Dto null").password();
        if (!Validator.PASSWORD.validate(psw))
            throw new InvalidPasswordException("Password non corretta.");
        String passwordHashed = DigestUtils.md5Hex(psw);
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

    public User getUserByToken() throws NullPointerException, InvalidTokenException, UserMissingException {
        ServletRequestAttributes req = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String tokenReq =
                Objects.requireNonNull(req, "Errore, request null.")
                        .getRequest()
                        .getHeader("token");
        if (tokenReq == null)
            throw new InvalidTokenException("Token non trovato.");
        String token = convertToken(tokenReq);
        return userRepo.findByEmail(token).orElseThrow(() -> new UserMissingException("User non trovato"));
    }
}
