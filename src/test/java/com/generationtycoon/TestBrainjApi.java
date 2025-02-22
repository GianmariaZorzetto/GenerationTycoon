package com.generationtycoon;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.generationtycoon.model.dto.UserLoginReqDto;
import com.generationtycoon.model.entities.Brainj;
import com.generationtycoon.model.entities.Difficulty;
import com.generationtycoon.model.entities.UserTycoon;
import com.generationtycoon.model.repositories.BrainjRepository;
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

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class TestBrainjApi {
    @Autowired
    private MockMvc mock;

    @Autowired
    private BrainjRepository bRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private EntityManager entityManager;

    private String token;

    @Transactional
    void recreateTable() {
        String creation = "CREATE TABLE brainj (id BIGINT AUTO_INCREMENT PRIMARY KEY, question VARCHAR(255) NOT NULL, answer VARCHAR(255) NOT NULL);";
        String drop = "DROP TABLE brainj;";
        entityManager.createNativeQuery(drop).executeUpdate();
        entityManager.createNativeQuery(creation).executeUpdate();
        String creationUser = "CREATE TABLE user_tycoon (id BIGINT AUTO_INCREMENT PRIMARY KEY, email VARCHAR(255) NOT NULL UNIQUE, password VARCHAR(255) NOT NULL, username VARCHAR(255) NOT NULL, difficulty VARCHAR(50) NOT NULL, score DOUBLE NOT NULL CHECK (score >= 0));";
        String dropUser = "DROP TABLE user_tycoon;";
        entityManager.createNativeQuery(dropUser).executeUpdate();
        entityManager.createNativeQuery(creationUser).executeUpdate();
    }

    @BeforeEach
    @Transactional
    public void setUp() {
        recreateTable();
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream file = new FileInputStream("src/test/resources/brainjs/brainjs.json")) {
            List<Brainj> brainjs = mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, Brainj.class));
            bRepo.saveAll(brainjs);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        userRepo.save(
                UserTycoon.builder()
                        .email("coolguy92@example.com")
                        .password(DigestUtils.md5Hex("C0mpl3x#Passw)ord"))
                        .username("cool_guy92")
                        .difficulty(Difficulty.HARD)
                        .score(0.0)
                        .build()
        );
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
    void testGetAllBrainjs() {
        try {
            mock.perform(get("/api/brainjs").header("token", token))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$").isArray());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testGetBrainjById() {
        try {
            mock.perform(get("/api/brainjs/1").header("token", token))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.answer").value("6765\n"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
