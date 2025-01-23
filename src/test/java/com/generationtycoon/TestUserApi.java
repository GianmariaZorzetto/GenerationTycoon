package com.generationtycoon;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.generationtycoon.model.dto.UserRegistrationReqDto;
import com.generationtycoon.model.entities.Difficulty;
import com.generationtycoon.model.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.StringJoiner;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TestUserApi {
    @Autowired
    private MockMvc mock;

    @Autowired
    private UserRepository userRepo;

    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        userRepo.deleteAll();
    }

    private String convertDtoToJson(UserRegistrationReqDto userRegistrationReqDto) {
        StringJoiner sj = new StringJoiner(",", "{", "}");

        return sj.toString();
    }

    @Test
    void testCreateUser() {
        try {
            UserRegistrationReqDto dto =
                    UserRegistrationReqDto.builder()
                            .email("test@domain.com")
                            .password("9$gT!3kZ@7mQ^1x")
                            .username("Admin")
                            .difficulty(Difficulty.HARD)
                            .build();
            mock.perform(post("/api/users/register")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(dto)))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.score").isNumber());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "marco.rossi@.com",
            "luca.bianchi@esempio",
            "alessandro.verdi@nonverificato,net",
            "matteo.gialli@spammail..org",
            "simone.neri@cattivoemail@.com",
            "davide.ferrari@falsomail@xyz",
            "andrea.conti@.nonesiste.com",
            "giovanni.martini@erroreemail..com",
            "federico.russo@prova123",
            "stefano.leone@cattivonome@com"
    })
    void testFailCreationByEmail(String email) {
        try {
            UserRegistrationReqDto dto =
                    UserRegistrationReqDto.builder()
                            .email(email)
                            .password("9$gT!3kZ@7mQ^1x")
                            .username("test")
                            .difficulty(Difficulty.EASY)
                            .build();
            mock.perform(post("/api/users/register")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(dto)))
                    .andExpect(status().isNotAcceptable());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"123456", "password", "qwerty", "abc123", "letmein", "iloveyou", "admin", "welcome", "12345", "user1"})
    void testFailCreationByPassword(String password) {
        try {
            UserRegistrationReqDto dto =
                    UserRegistrationReqDto.builder()
                            .email("test@domain.com")
                            .password(password)
                            .username("Admin")
                            .difficulty(Difficulty.MEDIUM)
                            .build();
            mock.perform(post("/api/users/register").contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(dto)))
                    .andExpect(status().isNotAcceptable());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
