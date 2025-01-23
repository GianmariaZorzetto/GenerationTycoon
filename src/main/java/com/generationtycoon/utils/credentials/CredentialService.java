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

/**
 * Classe di servizio per la gestione delle credenziali.
 */
@Service
public class CredentialService {
    /**
     * Costante utilizzata per la creazione del token di uno user.
     */
    private final static String CONNECTOR = "-";

    /**
     * Repository degli user.
     */
    private final UserRepository userRepo;

    /**
     * Costruttore della classe.
     *
     * @param userRepo autowired da springboot.
     */
    public CredentialService(@Autowired UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * Metodo che genera un token a partire dall'email di uno user.
     *
     * @param email l'email in ingresso.
     * @return il token generato.
     */
    private String generateToken(String email) {
        StringJoiner sj = new StringJoiner(CONNECTOR);
        int offset = new Random().nextInt(20);
        sj.add(String.valueOf(offset));
        for (char c : email.toCharArray())
            sj.add(String.valueOf(c + offset));
        return sj.toString();
    }

    /**
     * Metodo che converte un token a una email.
     *
     * @param token il token da convertire.
     * @return l'email riconvertita.
     */
    private String convertToken(String token) {
        String[] parts = token.split(CONNECTOR);
        StringBuilder t = new StringBuilder();
        int offset = Integer.parseInt(parts[0]);
        Arrays.stream(parts).skip(1).forEach(part -> {
            int byteInt = Integer.parseInt(part) - offset;
            t.append((char) byteInt);
        });
        return t.toString();
    }

    /**
     * Metodo che effettua il login di uno user.
     *
     * @param dto la richiesta di login da parte del client.
     * @return la risposta alla richiesta.
     * @throws NullPointerException     se {@code dto} è {@code null}.
     * @throws InvalidEmailException    se lo user non esiste.
     * @throws InvalidPasswordException se la password non coincide con la password dello user.
     */
    public UserLoginRespDto login(UserLoginReqDto dto)
            throws NullPointerException, InvalidEmailException, InvalidPasswordException {
        User user = userRepo
                .findByEmail(Objects.requireNonNull(dto, "Request null.").email())
                .orElseThrow(() -> new InvalidEmailException("Utente non trovato."));
        String passwordHashed = DigestUtils.md5Hex(dto.password());
        if (!user.getPassword().equals(passwordHashed))
            throw new InvalidPasswordException("Password non corretta.");
        return UserLoginRespDto.builder()
                .token(generateToken(user.getEmail()))
                .username(user.getUsername())
                .id(user.getId())
                .build();
    }

    /**
     * Metodo che registra uno user nel database.
     *
     * @param dto la richiesta dal client.
     * @return uno {@link User}.
     * @throws NullPointerException     se {@code dto} è {@code null}.
     * @throws InvalidPasswordException se la password nom è valida.
     * @throws InvalidEmailException    se l'email non è valida.
     */
    public User register(UserRegistrationReqDto dto)
            throws NullPointerException, InvalidPasswordException, InvalidEmailException {
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

    /**
     * Metodo che trova uno user dato il suo token.
     *
     * @return lo user nel database.
     * @throws NullPointerException  se la richiesta è {@code null}.
     * @throws InvalidTokenException se il token allegato non è stato allegato o non esiste uno user con il token.
     */
    public User getUserByToken() throws NullPointerException, InvalidTokenException {
        ServletRequestAttributes req = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String tokenReq =
                Objects.requireNonNull(req, "Errore, request null.")
                        .getRequest()
                        .getHeader("token");
        if (tokenReq == null)
            throw new InvalidTokenException("Token non trovato.");
        String token = convertToken(tokenReq);
        return userRepo.findByEmail(token).orElseThrow(() -> new InvalidTokenException("User non trovato"));
    }
}
