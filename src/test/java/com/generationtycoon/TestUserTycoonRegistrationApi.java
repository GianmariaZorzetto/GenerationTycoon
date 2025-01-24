package com.generationtycoon;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.generationtycoon.model.dto.UserLoginReqDto;
import com.generationtycoon.model.dto.UserRegistrationReqDto;
import com.generationtycoon.model.entities.Difficulty;
import com.generationtycoon.model.entities.UserTycoon;
import com.generationtycoon.model.repositories.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONObject;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TestUserTycoonRegistrationApi {
    @Autowired
    private MockMvc mock;

    @Autowired
    private UserRepository userRepo;

    private static String token;

    private static final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        userRepo.deleteAll();
        List<String> usernames = List.of(
                "user1",
                "cool_guy92",
                "john_doe",
                "jane.smith",
                "the_real_deal",
                "user1234",
                "awesome_user",
                "tech_guru",
                "adventurer99",
                "mystery_solver"
        );

        List<String> passwords = List.of(
                "P@ssw0rd123!",
                "C0mpl3x#Passw)ord",
                "S3cur3P@ssw0rd!",
                "Str0ngP@ssw0rd!",
                "P@ssw0rd!2023",
                "MyS3cureP@ss!",
                "P@ssw0rd#1",
                "S@fe&Sound2023",
                "Unbr3akable#Password",
                "P@ssw0rd$2023!"
        );

        List<String> emails = List.of(
                "user1@example.com",
                "coolguy92@example.com",
                "john.doe@example.com",
                "jane.smith@example.com",
                "therealdeal@example.com",
                "user1234@example.com",
                "awesome.user@example.com",
                "tech.guru@example.com",
                "adventurer99@example.com",
                "mystery.solver@example.com"
        );

        for (int i = 0; i < 10; i++) {
            userRepo.save(
                    UserTycoon.builder()
                            .email(emails.get(i))
                            .username(usernames.get(i))
                            .password(DigestUtils.md5Hex(passwords.get(i)))
                            .difficulty(Difficulty.HARD)
                            .score(0.0)
                            .build()
            );
        }
        try {
            UserLoginReqDto dto =
                    UserLoginReqDto.builder()
                            .email("coolguy92@example.com")
                            .password("C0mpl3x#Passw)ord")
                            .build();
            mock.perform(post("/api/users/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(dto))).andDo(
                    result -> {
                        JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
                        token = jsonObject.getString("token");
                    }
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

    @Test
    void testDeleteUser() {
        try {
            var x = mock.perform(delete("/api/users/2").header("token", token))
                    .andExpect(status().isOk()).andReturn();
            assertEquals("2", x.getResponse().getContentAsString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void testDeleteUserWithoutPermission() {
        try {
            mock.perform(delete("/api/users/5").header("token", token))
                    .andExpect(status().isUnauthorized());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}
