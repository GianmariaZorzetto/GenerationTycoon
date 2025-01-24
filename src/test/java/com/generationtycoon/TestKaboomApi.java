package com.generationtycoon;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.generationtycoon.model.dto.UserLoginReqDto;
import com.generationtycoon.model.entities.Difficulty;
import com.generationtycoon.model.entities.Kaboom;
import com.generationtycoon.model.entities.UserTycoon;
import com.generationtycoon.model.repositories.KaboomRepository;
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
public class TestKaboomApi {
    @Autowired
    private MockMvc mock;

    @Autowired
    private KaboomRepository kRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private EntityManager entityManager;

    private String token;

    @Transactional
    void recreateTable() {
        String creation = "CREATE TABLE user_tycoon (id BIGINT AUTO_INCREMENT PRIMARY KEY, email VARCHAR(255) NOT NULL UNIQUE, password VARCHAR(255) NOT NULL, username VARCHAR(255) NOT NULL, difficulty VARCHAR(50) NOT NULL, score DOUBLE NOT NULL CHECK (score >= 0));";
        String drop = "DROP TABLE user_tycoon;";
        entityManager.createNativeQuery(drop).executeUpdate();
        entityManager.createNativeQuery(creation).executeUpdate();
        String creationKaboom = "CREATE TABLE kaboom (id BIGINT AUTO_INCREMENT PRIMARY KEY, question VARCHAR(255) NOT NULL, answer1 VARCHAR(255) NOT NULL, answer2 VARCHAR(255) NOT NULL, answer3 VARCHAR(255) NOT NULL, answer4 VARCHAR(255) NOT NULL, correct_color VARCHAR(50) NOT NULL);";
        String dropKaboom = "DROP TABLE kaboom;";
        entityManager.createNativeQuery(dropKaboom).executeUpdate();
        entityManager.createNativeQuery(creationKaboom).executeUpdate();
    }

    @BeforeEach
    @Transactional
    public void setUp() {
        recreateTable();
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream file = new FileInputStream("src/test/resources/kabooms/kabooms.json")) {
            List<Kaboom> kabooms = mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, Kaboom.class));
            kRepo.saveAll(kabooms);
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
    void testGetAllKabooms() {
        try {
            mock.perform(get("/api/kabooms").header("token", token))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$").isArray());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testGetKaboomById() {
        try {
            mock.perform(get("/api/kabooms/2").header("token", token))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.correctColor").value("GREEN"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testFailGetAll() {
        try {
            mock.perform(get("/api/kabooms"))
                    .andExpect(status().isUnauthorized());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
