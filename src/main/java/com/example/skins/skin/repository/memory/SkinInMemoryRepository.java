package com.example.skins.skin.repository.memory;

import com.example.skins.c4se.entity.Case;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import com.example.skins.skin.entity.Skin;
import com.example.skins.skin.repository.api.SkinRepository;
import com.example.skins.datastore.component.DataStore;
import com.example.skins.user.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Repository for Skin entity. Repositories should be used in business layer (e.g.: in services).
 */
@RequestScoped
public class SkinInMemoryRepository implements SkinRepository {

    /**
     * Underlying data store. In future should be replaced with database connection.
     */
    private final DataStore store;

    /**
     * @param store data store
     */
    @Inject
    public SkinInMemoryRepository(DataStore store) {
        this.store = store;
    }

    @Override
    public Optional<Skin> find(UUID id) {
        return store.findAllSkins().stream()
                .filter(Skin -> Skin.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Skin> findAll() {
        return store.findAllSkins();
    }

    @Override
    public void create(Skin entity) {
        store.createSkin(entity);
    }

    @Override
    public void delete(Skin entity) {
        store.deleteSkin(entity.getId());
    }

    @Override
    public void update(Skin entity) {
        store.updateSkin(entity);
    }

    @Override
    public Optional<Skin> findByIdAndUser(UUID id, User user) {
        return store.findAllSkins().stream()
                .filter(Skin -> Skin.getUser().equals(user))
                .filter(Skin -> Skin.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Skin> findAllByUser(User user) {
        return store.findAllSkins().stream()
                .filter(Skin -> user.equals(Skin.getUser()))
                .collect(Collectors.toList());
    }

    @Override
        public void addSkinToCase(UUID caseId, Skin skin) {
        store.addSkinToCase(caseId, skin);
    }

    @Override
    public List<Skin> findAllByCase(Case Case) {
        return store.findAllSkins().stream()
                .filter(Skin -> Case.equals(Skin.getCaseItem()))
                .collect(Collectors.toList());
    }

}
