package com.generationtycoon;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.generationtycoon.model.dto.UserLoginReqDto;
import com.generationtycoon.model.dto.UserScoreReqDto;
import com.generationtycoon.model.dto.UserUpdateScoreReqDto;
import com.generationtycoon.model.entities.Difficulty;
import com.generationtycoon.model.entities.UserTycoon;
import com.generationtycoon.model.repositories.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TestScoreApi {
    @Autowired
    private MockMvc mock;

    @Autowired
    private UserRepository userRepo;

    private String token;

    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        userRepo.deleteAll();
        List<String> usernames = List.of(
                "user1",
                "cool_guy92",
                "john_doe",
                "adventurer99",
                "mystery_solver"
        );

        List<String> passwords = List.of(
                "P@ssw0rd123!",
                "C0mpl3x#Passw)ord",
                "S3cur3P@ssw0rd!",
                "Str0ngP@ssw0rd!",
                "P@ssw0rd!2023"
        );

        List<String> emails = List.of(
                "user1@example.com",
                "coolguy92@example.com",
                "john.doe@example.com",
                "jane.smith@example.com",
                "therealdeal@example.com"
        );

        for (int i = 0; i < 5; i++) {
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
    void testCalculateScore() {
        try {
            UserScoreReqDto dto =
                    new UserScoreReqDto(
                            LocalDateTime.of(2024, 1, 1, 1, 0, 0),
                            LocalDateTime.of(2024, 1, 1, 1, 30, 0),
                            2,
                            Difficulty.MEDIUM
                    );
            mock.perform(post("/api/users/score")
                            .header("token", token)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(dto))
                    ).andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.score").value(2540));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testFailScoreByDates() {
        try {
            UserScoreReqDto dto =
                    new UserScoreReqDto(
                            LocalDateTime.of(2024, 1, 1, 1, 30, 0),
                            LocalDateTime.of(2024, 1, 1, 1, 0, 0),
                            2,
                            Difficulty.MEDIUM
                    );
            mock.perform(post("/api/users/score")
                    .header("token", token)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(dto))
            ).andExpect(status().isBadRequest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testFailScoreByHp() {
        try {
            UserScoreReqDto dto =
                    new UserScoreReqDto(
                            LocalDateTime.of(2024, 1, 1, 1, 30, 0),
                            LocalDateTime.of(2024, 1, 1, 1, 0, 0),
                            0,
                            Difficulty.MEDIUM
                    );
            mock.perform(post("/api/users/score")
                    .header("token", token)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(dto))
            ).andExpect(status().isBadRequest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testFailScoreByDifficulty() {
        try {
            UserScoreReqDto dto =
                    new UserScoreReqDto(
                            LocalDateTime.of(2024, 1, 1, 1, 30, 0),
                            LocalDateTime.of(2024, 1, 1, 1, 0, 0),
                            2,
                            Difficulty.fromString("CIao")
                    );
            mock.perform(post("/api/users/score")
                    .header("token", token)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(dto))
            ).andExpect(status().isBadRequest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testUpdateScore() {
        try {
            UserUpdateScoreReqDto dto =
                    new UserUpdateScoreReqDto(2L, 120.6);
            mock.perform(put("/api/users/newScore")
                            .header("token", token)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(dto))
                    ).andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.username").value("cool_guy92"))
                    .andExpect(jsonPath("$.score").value(120.6));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
