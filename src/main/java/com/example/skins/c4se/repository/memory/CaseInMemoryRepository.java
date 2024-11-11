package com.example.skins.c4se.repository.memory;

import com.example.skins.c4se.entity.Case;
import com.example.skins.c4se.repository.api.CaseRepository;
import com.example.skins.datastore.component.DataStore;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository for Case entity. Repositories should be used in business layer (e.g.: in services).
 */
@RequestScoped
public class CaseInMemoryRepository implements CaseRepository {

    /**
     * Underlying data store. In future should be replaced with database connection.
     */
    private final DataStore store;

    /**
     * @param store data store
     */
    @Inject
    public CaseInMemoryRepository(DataStore store) {
        this.store = store;
    }


    @Override
    public Optional<Case> find(UUID id) {
        return store.findAllCases().stream()
                .filter(Case -> Case.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Case> findAll() {
        return store.findAllCases();
    }

    @Override
    public void create(Case entity) {
        store.createCase(entity);
    }

    @Override
    public void delete(Case entity) {
        store.deleteCase(entity.getId());
    }

    @Override
    public void update(Case entity) {
       store.updateCase(entity);
    }


}
