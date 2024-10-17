package com.example.skins.datastore.component;

import com.example.skins.skin.entity.Skin;
import lombok.extern.java.Log;
import com.example.skins.skin.entity.Case;
import com.example.skins.serialization.component.CloningUtility;
import com.example.skins.user.entity.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * For the sake of simplification instead of using real database this example is using a data source object which should
 * be put in servlet context in a single instance. In order to avoid {@link java.util.ConcurrentModificationException}
 * all methods are synchronized. Normally synchronization would be carried on by the database server. Caution, this is
 * very inefficient implementation but can be used to present other mechanisms without obscuration example with ORM
 * usage.
 */
@Log
public class DataStore {

    /**
     * Set of all available Cases.
     */
    private final Set<Case> Cases = new HashSet<>();

    /**
     * Set of all Skins.
     */
    private final Set<Skin> Skins = new HashSet<>();

    /**
     * Set of all users.
     */
    private final Set<User> users = new HashSet<>();

    /**
     * Component used for creating deep copies.
     */
    private final CloningUtility cloningUtility;

    /**
     * @param cloningUtility component used for creating deep copies
     */
    public DataStore(CloningUtility cloningUtility) {
        this.cloningUtility = cloningUtility;
    }

    /**
     * Seeks for all Cases.
     *
     * @return list (can be empty) of all Cases
     */
    public synchronized List<Case> findAllCases() {
        return Cases.stream()
                .map(cloningUtility::clone)
                .collect(Collectors.toList());
    }

    /**
     * Stores new Case.
     *
     * @param value new Case to be stored
     * @throws IllegalArgumentException if Case with provided id already exists
     */
    public synchronized void createCase(Case value) throws IllegalArgumentException {
        if (Cases.stream().anyMatch(Case -> Case.getId().equals(value.getId()))) {
            throw new IllegalArgumentException("The Case id \"%s\" is not unique".formatted(value.getId()));
        }
        Cases.add(cloningUtility.clone(value));
    }

    /**
     * Seeks for all Skins.
     *
     * @return list (can be empty) of all Skins
     */
    public synchronized List<Skin> findAllSkins() {
        return Skins.stream()
                .map(cloningUtility::clone)
                .collect(Collectors.toList());
    }

    /**
     * Stores new Skin.
     *
     * @param value new Skin to be stored
     * @throws IllegalArgumentException if Skin with provided id already exists or when {@link User} or
     *                                  {@link Case} with provided uuid does not exist
     */
    public synchronized void createSkin(Skin value) throws IllegalArgumentException {
        if (Skins.stream().anyMatch(Skin -> Skin.getId().equals(value.getId()))) {
            throw new IllegalArgumentException("The Skin id \"%s\" is not unique".formatted(value.getId()));
        }
        Skin entity = cloneWithRelationships(value);
        Skins.add(entity);
    }

    /**
     * Updates existing Skin.
     *
     * @param value Skin to be updated
     * @throws IllegalArgumentException if Skin with the same id does not exist or when {@link User} or
     *                                  {@link Case} with provided uuid does not exist
     */
    public synchronized void updateSkin(Skin value) throws IllegalArgumentException {
        Skin entity = cloneWithRelationships(value);
        if (Skins.removeIf(Skin -> Skin.getId().equals(value.getId()))) {
            Skins.add(entity);
        } else {
            throw new IllegalArgumentException("The Skin with id \"%s\" does not exist".formatted(value.getId()));
        }
    }

    /**
     * Deletes existing Skin.
     *
     * @param id id of Skin to be deleted
     * @throws IllegalArgumentException if Skin with provided id does not exist
     */
    public synchronized void deleteSkin(UUID id) throws IllegalArgumentException {
        if (!Skins.removeIf(Skin -> Skin.getId().equals(id))) {
            throw new IllegalArgumentException("The Skin with id \"%s\" does not exist".formatted(id));
        }
    }

    /**
     * Seeks for all users.
     *
     * @return list (can be empty) of all users
     */
    public synchronized List<User> findAllUsers() {
        return users.stream()
                .map(cloningUtility::clone)
                .collect(Collectors.toList());
    }

    /**
     * Stores new user.
     *
     * @param value new user to be stored
     * @throws IllegalArgumentException if user with provided id already exists
     */
    public synchronized void createUser(User value) throws IllegalArgumentException {
        if (users.stream().anyMatch(Skin -> Skin.getId().equals(value.getId()))) {
            throw new IllegalArgumentException("The user id \"%s\" is not unique".formatted(value.getId()));
        }
        users.add(cloningUtility.clone(value));
    }

    /**
     * Updates existing user.
     *
     * @param value user to be updated
     * @throws IllegalArgumentException if user with the same id does not exist
     */
    public synchronized void updateUser(User value) throws IllegalArgumentException {
        if (users.removeIf(Skin -> Skin.getId().equals(value.getId()))) {
            users.add(cloningUtility.clone(value));
        } else {
            throw new IllegalArgumentException("The user with id \"%s\" does not exist".formatted(value.getId()));
        }
    }

    /**
     * Clones existing Skin and updates relationships for values in storage
     *
     * @param value Skin
     * @return cloned value with updated relationships
     * @throws IllegalArgumentException when {@link User} or {@link Case} with provided uuid does not exist
     */
    private Skin cloneWithRelationships(Skin value) {
        Skin entity = cloningUtility.clone(value);

        if (entity.getUser() != null) {
            entity.setUser(users.stream()
                    .filter(user -> user.getId().equals(value.getUser().getId()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("No user with id \"%s\".".formatted(value.getUser().getId()))));
        }

        if (entity.getCaseItem() != null) {
            entity.setCaseItem(Cases.stream()
                    .filter(Case -> Case.getId().equals(value.getCaseItem().getId()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("No Case with id \"%s\".".formatted(value.getCaseItem().getId()))));
        }

        return entity;
    }

}
