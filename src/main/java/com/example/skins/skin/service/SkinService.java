package com.example.skins.skin.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import com.example.skins.skin.repository.api.SkinRepository;
import com.example.skins.skin.repository.api.CaseRepository;
import com.example.skins.skin.entity.Skin;
import com.example.skins.user.entity.User;
import com.example.skins.user.repository.api.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service layer for all business actions regarding skin entity.
 */
@ApplicationScoped
@NoArgsConstructor(force = true)
public class SkinService {

    /**
     * Repository for skin entity.
     */
    private final SkinRepository skinRepository;

    /**
     * Repository for case entity.
     */
    private final CaseRepository caseRepository;

    /**
     * Repository for user entity.
     */
    private final UserRepository userRepository;

    /**
     * @param skinRepository  repository for skin entity
     * @param caseRepository repository for case entity
     * @param userRepository repository for user entity
     */
    @Inject
    public SkinService(SkinRepository skinRepository, CaseRepository caseRepository, UserRepository userRepository) {
        this.skinRepository = skinRepository;
        this.caseRepository = caseRepository;
        this.userRepository = userRepository;
    }

    /**
     * Finds single skin.
     *
     * @param id skin's id
     * @return container with skin
     */
    public Optional<Skin> find(UUID id) {
        return skinRepository.find(id);
    }

    /**
     * @param id   skin's id
     * @param user existing user
     * @return selected skin for user
     */
    public Optional<Skin> find(User user, UUID id) {
        return skinRepository.findByIdAndUser(id, user);
    }

    /**
     * @return all available skins
     */
    public List<Skin> findAll() {
        return skinRepository.findAll();
    }

    /**
     * @param user existing user, skin's owner
     * @return all available skins of the selected user
     */
    public List<Skin> findAll(User user) {
        return skinRepository.findAllByUser(user);
    }

    /**
     * Creates new skin.
     *
     * @param skin new skin
     */
    public void create(Skin skin) {
        skinRepository.create(skin);
    }

    /**
     * Updates existing skin.
     *
     * @param skin skin to be updated
     */
    public void update(Skin skin) {
        skinRepository.update(skin);
    }

    /**
     * Deletes existing skin.
     *
     * @param id existing skin's id to be deleted
     */
    public void delete(UUID id) {
        skinRepository.delete(skinRepository.find(id).orElseThrow());
    }


    public Optional<List<Skin>> findAllByCase(UUID id) {
        return caseRepository.find(id)
                .map(skinRepository::findAllByCase);
    }

    public Optional<List<Skin>> findAllByUser(UUID id) {
        return userRepository.find(id)
                .map(skinRepository::findAllByUser);
    }
}
