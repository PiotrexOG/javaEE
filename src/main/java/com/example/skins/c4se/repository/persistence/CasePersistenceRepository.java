package com.example.skins.c4se.repository.persistence;

import jakarta.enterprise.context.Dependent;
import com.example.skins.c4se.entity.Case;
import com.example.skins.c4se.repository.api.CaseRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Dependent
public class CasePersistenceRepository implements CaseRepository {
    private EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<Case> find(UUID id) {
        return Optional.ofNullable(em.find(Case.class, id));
    }

    @Override
    public List<Case> findAll() {
        return em.createQuery("select g from Case g", Case.class).getResultList();
    }

    @Override
    public void create(Case entity) {
        em.persist(entity);
    }

    @Override
    public void delete(Case entity) {
        em.remove(entity);
    }

    @Override
    public void update(Case entity) {
        em.merge(entity);
    }
}