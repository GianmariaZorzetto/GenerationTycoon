package com.generationtycoon.controllers.helpers;

import com.generationtycoon.controllers.exceptions.BrainjMissingException;
import com.generationtycoon.controllers.exceptions.KaboomMissingException;
import com.generationtycoon.controllers.exceptions.UserMissingException;
import com.generationtycoon.model.dto.*;
import com.generationtycoon.model.entities.Brainj;
import com.generationtycoon.model.entities.Difficulty;
import com.generationtycoon.model.entities.Kaboom;
import com.generationtycoon.model.entities.UserTycoon;
import com.generationtycoon.model.repositories.BrainjRepository;
import com.generationtycoon.model.repositories.KaboomRepository;
import com.generationtycoon.model.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementazione di {@link ControllerHelper}.
 */
@Service
public class ControllerHelperImpl implements ControllerHelper {

    /**
     * Repository dei {@link Brainj}.
     */
    private final BrainjRepository bRepo;

    /**
     * Repository dei {@link Kaboom}.
     */
    private final KaboomRepository kRepo;

    /**
     * Repository dei {@link UserTycoon}.
     */
    private final UserRepository uRepo;

    /**
     * Converter dei dto.
     */
    private final DtoConverter converter;

    /**
     * Costruttore.
     *
     * @param bRepo     autowired da springboot.
     * @param kRepo     autowired da springboot.
     * @param uRepo     autowired da springboot.
     * @param converter autowired da springboot.
     */
    public ControllerHelperImpl(@Autowired BrainjRepository bRepo,
                                @Autowired KaboomRepository kRepo,
                                @Autowired UserRepository uRepo,
                                @Autowired DtoConverter converter) {
        this.bRepo = bRepo;
        this.kRepo = kRepo;
        this.uRepo = uRepo;
        this.converter = converter;
    }

    @Override
    public List<KaboomRespDto> getAllKabooms() {
        return kRepo.findAll().stream().map(kaboom -> converter.toKaboomRespDto(kaboom)).toList();
    }

    @Override
    public KaboomRespDto getKaboomById(Long id) {
        Optional<Kaboom> kaboom = kRepo.findById(id);

        if (kaboom.isEmpty())
            throw new KaboomMissingException("Kaboom non presente");

        return converter.toKaboomRespDto(kaboom.get());
    }

    @Override
    public List<UserLeaderboardRespDto> getAllUsersOnTheLeaderboard() {
        return uRepo.findAll().stream().map(user -> converter.toUserLeaderboardRespDto(user)).toList();
    }

    @Override
    public List<UserLeaderboardRespDto> getUsersByDifficulty(Difficulty difficulty) {
        if (difficulty == null) return List.of();
        return uRepo.findByDifficulty(difficulty).stream().map(user -> converter.toUserLeaderboardRespDto(user)).toList();
    }


    @Override
    public void deleteUser(Long id) {
        Optional<UserTycoon> user = uRepo.findById(id);

        if (user.isEmpty())
            throw new UserMissingException("User non presente");

        uRepo.deleteById(id);
    }

    @Override
    public List<BrainjRespDto> getAllBrainjs() {
        return bRepo.findAll().stream().map(brainj -> converter.toBrainjRespDto(brainj)).toList();
    }

    @Override
    public BrainjRespDto getBrainjById(Long id) {
        Optional<Brainj> brainj = bRepo.findById(id);

        if (brainj.isEmpty())
            throw new BrainjMissingException("Brainj non presente");

        return converter.toBrainjRespDto(brainj.get());
    }

    @Override
    public UserTycoon updateUser(UserTycoon userTycoon) {
        UserTycoon userTycoonDb = uRepo.findById(userTycoon.getId())
                .orElseThrow(() -> new UserMissingException("Impossibile aggiornare utente non presente."));
        userTycoonDb.setScore(userTycoon.getScore());
        userTycoonDb.setDifficulty(userTycoon.getDifficulty());
        return uRepo.save(userTycoonDb);
    }
}
