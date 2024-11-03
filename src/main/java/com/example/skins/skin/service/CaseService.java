package com.example.skins.skin.service;

import com.example.skins.skin.entity.Skin;
import com.example.skins.skin.repository.api.SkinRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import com.example.skins.skin.repository.api.CaseRepository;
import com.example.skins.skin.entity.Case;
import java.util.List;
import java.util.NoSuchElementException;
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
    private final CaseRepository case_repository;
    private final SkinRepository skin_repository;

    /**
     * @param case_repository repository for case entity
     * @param skin_repository repository for case entity
     */
    @Inject
    public CaseService(CaseRepository case_repository, SkinRepository skin_repository) {
        this.case_repository = case_repository;
        this.skin_repository = skin_repository;
    }

    public void deleteCaseAndUnassignSkins(UUID caseId) {
        // Znajdź skrzynkę po jej ID
        Case existingCase = case_repository.find(caseId)
                .orElseThrow(() -> new NoSuchElementException("Case not found with id: " + caseId));

        // Usuń skrzynkę z repozytorium
        case_repository.delete(existingCase);
    }

    /**
     * @param id case's id
     * @return container with case entity
     */
    public Optional<Case> find(UUID id) {
        return case_repository.find(id);
    }

    /**
     * @return all available cases
     */
    public List<Case> findAll() {
        return case_repository.findAll();
    }

    /**
     * Stores new case in the data store.
     *
     * @param cas new case to be saved
     */
    public void create(Case cas) {
        case_repository.create(cas);
    }

}
