package com.example.skins.user.repository.api;

import com.example.skins.repository.api.Repository;
import com.example.skins.user.entity.User;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository for User entity. Repositories should be used in business layer (e.g.: in services).
 */
public interface UserRepository extends Repository<User, UUID> {

    /**
     * Seeks for single user using login.
     *
     * @param login user's login
     * @return container (can be empty) with user
     */
    Optional<User> findByLogin(String login);

}
