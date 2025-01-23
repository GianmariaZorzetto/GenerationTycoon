package com.generationtycoon;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.generationtycoon.model.dto.UserLoginReqDto;
import com.generationtycoon.model.dto.UserLoginRespDto;
import com.generationtycoon.model.entities.Difficulty;
import com.generationtycoon.model.entities.UserTycoon;
import com.generationtycoon.model.repositories.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TestUserTycoonLoginApi {
    @Autowired
    private MockMvc mock;

    @Autowired
    private UserRepository userRepo;

    private final ObjectMapper mapper = new ObjectMapper();


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
    }

    @Test
    void testUserLogin() {
        try {
            UserLoginReqDto dto =
                    UserLoginReqDto.builder()
                            .email("coolguy92@example.com")
                            .password("C0mpl3x#Passw)ord")
                            .build();
            mock.perform(post("/api/users/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(dto)))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.token").isNotEmpty())
                    .andExpect(jsonPath("$.username").value("cool_guy92"))
                    .andExpect(jsonPath("$.id").value(2));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "user3@example.com",
            "xXcoolguy92Xx@example.com",
            "john.does@example.com",
            "jane.smithy@example.com",
            "therealVdeal@example.com",
            "1234user1234@example.com",
            "awesome.user@example.com",
            "tech.guru3@example.com",
            "adventurr99@example.com",
            "mysterySolver@example.com"
    })
    void testUserFailLogin(String email) {
        try {
            UserLoginReqDto dto =
                    UserLoginReqDto.builder()
                            .email(email)
                            .password("C0mpl3x#Passw)ord")
                            .build();
            mock.perform(post("/api/users/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(dto)))
                    .andExpect(status().isNotAcceptable());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
