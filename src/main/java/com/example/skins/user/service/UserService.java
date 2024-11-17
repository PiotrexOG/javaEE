package com.example.skins.user.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

import com.example.skins.crypto.component.Pbkdf2PasswordHash;
import com.example.skins.user.entity.User;
import com.example.skins.user.repository.api.UserRepository;
import jakarta.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service layer for all business actions regarding user entity.
 */
@ApplicationScoped
@NoArgsConstructor(force = true)
public class UserService {

    /**
     * Repository for user entity.
     */
    private final UserRepository repository;

    /**
     * Hash mechanism used for storing users' passwords.
     */
    private final Pbkdf2PasswordHash passwordHash;

    public List<User> findAll() {
        return repository.findAll();
    }
    /**
     * @param repository   repository for Skin entity
     * @param passwordHash hash mechanism used for storing users' passwords
     */
    @Inject
    public UserService(UserRepository repository, Pbkdf2PasswordHash passwordHash) {
        this.repository = repository;
        this.passwordHash = passwordHash;
    }

    /**
     * @param id user's id
     * @return container (can be empty) with user
     */
    public Optional<User> find(UUID id) {
        return repository.find(id);
    }

    /**
     * Seeks for single user using login and password. Can be used in authentication module.
     *
     * @param login user's login
     * @return container (can be empty) with user
     */
    public Optional<User> find(String login) {
        return repository.findByLogin(login);
    }

    /**
     * Saves new user. Password is hashed using configured hash algorithm.
     *
     * @param user new user to be saved
     */
    @Transactional
    public void create(User user) {
        user.setPassword(passwordHash.generate(user.getPassword().toCharArray()));
        repository.create(user);
    }
    @Transactional
    public void update(User user) {
        repository.update(user);
    }
    @Transactional
    public void delete(UUID id) {
        repository.delete(repository.find(id).orElseThrow());
    }

    /**
     * @param login    user's login
     * @param password user's password
     * @return true if provided login and password are correct
     */
    public boolean verify(String login, String password) {
        return find(login)
                .map(user -> passwordHash.verify(password.toCharArray(), user.getPassword()))
                .orElse(false);
    }
    @Transactional
    public void updateAvatar(UUID id, InputStream is) {
        repository.find(id).ifPresent(user -> {
            try {
                user.setAvatar(is.readAllBytes());
                repository.update(user);
//                Path existingPath = Path.of(path, id.toString() + ".jpg");
//                if (Files.exists(existingPath)) {
//                    Files.copy(is, existingPath, StandardCopyOption.REPLACE_EXISTING);
//                } else {
//                    throw new NotFoundException("User avatar not found, use PUT instead.");
//                }
            } catch (IOException ex) {
                throw new IllegalStateException(ex);
            }
        });
    }

    public void createAvatar(UUID id, InputStream is, String path) {
        repository.find(id).ifPresent(user -> {
            try {
                Path destinationPath = Path.of(path, id.toString() + ".png");
//                if (Files.exists(destinationPath)) {
//                    throw new AlreadyExistsException("Avatar already exists, use PATCH instead.");
//                }
                Files.copy(is, destinationPath);
            } catch (IOException ex) {
                throw new IllegalStateException(ex);
            }
        });
    }

    public void deleteAvatar(UUID id){
        repository.find(id).ifPresent(user -> {
            user.setAvatar(null);
            repository.update(user);
        });
    }



}
