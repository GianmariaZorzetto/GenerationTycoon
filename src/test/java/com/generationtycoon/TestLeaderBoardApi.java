package com.generationtycoon;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.generationtycoon.model.dto.UserLoginReqDto;
import com.generationtycoon.model.entities.Difficulty;
import com.generationtycoon.model.entities.UserTycoon;
import com.generationtycoon.model.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
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

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TestLeaderBoardApi {
    @Autowired
    private MockMvc mock;

    @Autowired
    private UserRepository userRepo;


    @Autowired
    private EntityManager entityManager;

    private String token;

    private final ObjectMapper mapper = new ObjectMapper();

    @Transactional
    void recreateTable() {
        String creation = "CREATE TABLE user_tycoon (id BIGINT AUTO_INCREMENT PRIMARY KEY, email VARCHAR(255) NOT NULL UNIQUE, password VARCHAR(255) NOT NULL, username VARCHAR(255) NOT NULL, difficulty VARCHAR(50) NOT NULL, score DOUBLE NOT NULL CHECK (score >= 0));";
        String drop = "DROP TABLE user_tycoon;";
        entityManager.createNativeQuery(drop).executeUpdate();
        entityManager.createNativeQuery(creation).executeUpdate();
    }


    @BeforeEach
    @Transactional
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
                            .difficulty(Difficulty.MEDIUM)
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
    void getAllFromLeaderboard() {
        try {
            mock.perform(get("/api/users").header("token", token))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$").isArray());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getAllFromLeaderboardByDifficulty() {
        try {
            mock.perform(get("/api/users/medium").header("token", token))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$[0].difficulty").value("MEDIUM"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
