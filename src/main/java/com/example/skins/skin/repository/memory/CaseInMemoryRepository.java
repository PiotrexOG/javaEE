package com.example.skins.skin.repository.memory;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import com.example.skins.skin.entity.Case;
import com.example.skins.skin.repository.api.CaseRepository;
import com.example.skins.datastore.component.DataStore;

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
        throw new UnsupportedOperationException("Operation not implemented.");
    }

    @Override
    public void update(Case entity) {
        throw new UnsupportedOperationException("Operation not implemented.");
    }

}
