package com.example.skins.skin.repository.persistence;

import jakarta.enterprise.context.Dependent;
import com.example.skins.skin.repository.api.SkinRepository;
import com.example.skins.skin.entity.Skin;
import com.example.skins.skin.repository.api.SkinRepository;
import com.example.skins.c4se.entity.Case;
import com.example.skins.user.entity.User;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Dependent
public class SkinPersistenceRepository implements SkinRepository {

    private EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<Skin> findByIdAndUser(UUID id, User user) {
        return Optional.ofNullable(em.find(Skin.class, id));
    }

    @Override
    public List<Skin> findAllByUser(User user) {
        return em.createQuery("select c from Skin c where c.user = :user", Skin.class).setParameter("user", user).getResultList();
    }


    @Override
    public List<Skin> findAllByCase(Case aCase) {
        return em.createQuery("select c from Skin c where c.caseItem = :case", Skin.class).setParameter("case", aCase).getResultList();
    }

    @Override
    public Optional<Skin> find(UUID id) {
        return Optional.ofNullable(em.find(Skin.class, id));
    }

    @Override
    public List<Skin> findAll() {
        return em.createQuery("select c from Skin c", Skin.class).getResultList();
    }

    @Override
    public void create(Skin entity) {
        em.persist(entity);
    }

    @Override
    public void delete(Skin entity) {
        em.remove(entity);
    }

    @Override
    public void update(Skin entity) {
        em.merge(entity);
    }
}
