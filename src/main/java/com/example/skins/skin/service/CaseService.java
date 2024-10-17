package com.example.skins.skin.service;

import com.example.skins.skin.entity.Case;
import com.example.skins.skin.repository.api.CaseRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service layer for all business actions regarding Skin's Case entity.
 */
public class CaseService {

    /**
     * Repository for Case entity.
     */
    private final CaseRepository repository;

    /**
     * @param repository repository for Case entity
     */
    public CaseService(CaseRepository repository) {
        this.repository = repository;
    }

    /**
     * @param id Case's id
     * @return container with Case entity
     */
    public Optional<Case> find(UUID id) {
        return repository.find(id);
    }

    /**
     * @return all available Cases
     */
    public List<Case> findAll() {
        return repository.findAll();
    }

    /**
     * Stores new Case in the data store.
     *
     * @param Case new Case to be saved
     */
    public void create(Case Case) {
        repository.create(Case);
    }

}
