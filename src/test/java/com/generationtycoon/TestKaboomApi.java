package com.generationtycoon;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.generationtycoon.model.dto.UserLoginReqDto;
import com.generationtycoon.model.entities.Difficulty;
import com.generationtycoon.model.entities.Kaboom;
import com.generationtycoon.model.entities.UserTycoon;
import com.generationtycoon.model.repositories.KaboomRepository;
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
public class TestKaboomApi {
    @Autowired
    private MockMvc mock;

    @Autowired
    private KaboomRepository kRepo;

    @Autowired
    private UserRepository userRepo;

    private String token;

    @BeforeEach
    public void setUp() {
        kRepo.deleteAll();
        userRepo.deleteAll();
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
