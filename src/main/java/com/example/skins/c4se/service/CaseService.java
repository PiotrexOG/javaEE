package com.example.skins.c4se.service;

import com.example.skins.c4se.entity.Case;
import com.example.skins.c4se.repository.api.CaseRepository;
import com.example.skins.skin.repository.api.SkinRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

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

    /**
     * @param case_repository repository for case entity
     */
    @Inject
    public CaseService(CaseRepository case_repository) {
        this.case_repository = case_repository;
    }

//    public void delete(UUID caseId) {
//        // Znajdź skrzynkę po jej ID
//        Case existingCase = case_repository.find(caseId)
//                .orElseThrow(() -> new NoSuchElementException("Case not found with id: " + caseId));
//
//        // Usuń skrzynkę z repozytorium
//        case_repository.delete(existingCase);
//    }

    public void delete(Case caseItem) {
        // Usuń skrzynkę z repozytorium
        case_repository.delete(caseItem);
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

    public void update(Case acase) {
        case_repository.update(acase);
    }

}
