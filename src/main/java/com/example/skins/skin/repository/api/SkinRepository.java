package com.example.skins.skin.repository.api;

import com.example.skins.skin.entity.Case;
import com.example.skins.repository.api.Repository;
import com.example.skins.skin.entity.Skin;
import com.example.skins.user.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository for Skin entity. Repositories should be used in business layer (e.g.: in services).
 */
public interface SkinRepository extends Repository<Skin, UUID> {

    /**
     * Seeks for single user's Skin.
     *
     * @param id   Skin's id
     * @param user Skin's owner
     * @return container (can be empty) with Skin
     */
    Optional<Skin> findByIdAndUser(UUID id, User user);

    /**
     * Seeks for all user's Skins.
     *
     * @param user Skins' owner
     * @return list (can be empty) of user's Skins
     */
    List<Skin> findAllByUser(User user);

    /**
     * Seeks for all Case's Skins.
     *
     * @param Case Skin's Case
     * @return list (can be empty) of user's Skins
     */
    List<Skin> findAllByCase(Case Case);

}
