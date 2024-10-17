package com.example.skins.skin.service;

import com.example.skins.skin.repository.api.SkinRepository;
import com.example.skins.skin.repository.api.CaseRepository;
import com.example.skins.user.entity.User;
import com.example.skins.skin.entity.Skin;
import com.example.skins.user.repository.api.UserRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service layer for all business actions regarding Skin entity.
 */
public class SkinService {

    /**
     * Repository for Skin entity.
     */
    private final SkinRepository SkinRepository;

    /**
     * Repository for Case entity.
     */
    private final CaseRepository CaseRepository;

    /**
     * Repository for user entity.
     */
    private final UserRepository userRepository;

    /**
     * @param SkinRepository  repository for Skin entity
     * @param CaseRepository repository for Case entity
     * @param userRepository repository for user entity
     */
    public SkinService(SkinRepository SkinRepository, CaseRepository CaseRepository, UserRepository userRepository) {
        this.SkinRepository = SkinRepository;
        this.CaseRepository = CaseRepository;
        this.userRepository = userRepository;
    }

    /**
     * Finds single Skin.
     *
     * @param id Skin's id
     * @return container with Skin
     */
    public Optional<Skin> find(UUID id) {
        return SkinRepository.find(id);
    }

    /**
     * @param id   Skin's id
     * @param user existing user
     * @return selected Skin for user
     */
    public Optional<Skin> find(User user, UUID id) {
        return SkinRepository.findByIdAndUser(id, user);
    }

    /**
     * @return all available Skins
     */
    public List<Skin> findAll() {
        return SkinRepository.findAll();
    }

    /**
     * @param user existing user, Skin's owner
     * @return all available Skins of the selected user
     */
    public List<Skin> findAll(User user) {
        return SkinRepository.findAllByUser(user);
    }

    /**
     * Creates new Skin.
     *
     * @param Skin new Skin
     */
    public void create(Skin Skin) {
        SkinRepository.create(Skin);
    }

    /**
     * Updates existing Skin.
     *
     * @param Skin Skin to be updated
     */
    public void update(Skin Skin) {
        SkinRepository.update(Skin);
    }

    /**
     * Deletes existing Skin.
     *
     * @param id existing Skin's id to be deleted
     */
    public void delete(UUID id) {
        SkinRepository.delete(SkinRepository.find(id).orElseThrow());
    }

    /**
     * Updates portrait of the Skin.
     *
     * @param id Skin's id
     * @param is input stream containing new portrait
     */
    public void updatePortrait(UUID id, InputStream is) {
        throw new IllegalStateException();
//        SkinRepository.find(id).ifPresent(Skin -> {
//            try {
//                Skin.setPortrait(is.readAllBytes());
//                SkinRepository.update(Skin);
//            } catch (IOException ex) {
//                throw new IllegalStateException(ex);
//            }
//        });
    }

    public Optional<List<Skin>> findAllByCase(UUID id) {
        return CaseRepository.find(id)
                .map(SkinRepository::findAllByCase);
    }

    public Optional<List<Skin>> findAllByUser(UUID id) {
        return userRepository.find(id)
                .map(SkinRepository::findAllByUser);
    }
}
