package com.example.skins.skin.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import com.example.skins.skin.repository.api.CaseRepository;
import com.example.skins.skin.entity.Case;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service layer for all business actions regarding character's case entity.
 */
@ApplicationScoped
@NoArgsConstructor(force = true)
public class CaseService {

    /**
     * Repository for case entity.
     */
    private final CaseRepository repository;

    /**
     * @param repository repository for case entity
     */
    @Inject
    public CaseService(CaseRepository repository) {
        this.repository = repository;
    }

    /**
     * @param id case's id
     * @return container with case entity
     */
    public Optional<Case> find(UUID id) {
        return repository.find(id);
    }

    /**
     * @return all available cases
     */
    public List<Case> findAll() {
        return repository.findAll();
    }

    /**
     * Stores new case in the data store.
     *
     * @param cas new case to be saved
     */
    public void create(Case cas) {
        repository.create(cas);
    }

}
