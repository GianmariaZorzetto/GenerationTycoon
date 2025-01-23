package com.generationtycoon;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.generationtycoon.model.entities.Difficulty;
import com.generationtycoon.model.entities.Kaboom;
import com.generationtycoon.model.entities.User;
import com.generationtycoon.model.repositories.KaboomRepository;
import com.generationtycoon.model.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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
                User.builder()
                        .email("test@domain.com")
                        .password("9$gT!3kZ@7mQ^1x")
                        .username("Admin")
                        .difficulty(Difficulty.HARD)
                        .build()
        );
    }

    @Test
    public void testGetAll() {
        try {
            mock.perform(get("/api/kabooms"))
                    .andExpect(status().isUnauthorized());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
