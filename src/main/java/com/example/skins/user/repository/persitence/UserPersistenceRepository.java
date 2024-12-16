package com.example.skins.user.repository.persitence;

import com.example.skins.user.entity.User;
import com.example.skins.user.repository.api.UserRepository;
import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Dependent
public class UserPersistenceRepository implements UserRepository {

    private EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<User> find(UUID id) {
        return Optional.ofNullable(em.find(User.class, id));
    }

    @Override
    public List<User> findAll() {
      //  return em.createQuery("select u from User u", User.class).getResultList();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> userRoot = cq.from(User.class);
        cq.select(userRoot);
        return em.createQuery(cq).getResultList();
    }

    @Override
    public void create(User entity) {
        em.persist(entity);
    }

    @Override
    public void delete(User entity) {
        em.remove(entity);
    }

    @Override
    public void update(User entity) {
        em.merge(entity);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        try {
          //  return Optional.ofNullable(em.createQuery("select u from User u where u.login = :login", User.class).setParameter("login", login).getSingleResult());

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> userRoot = cq.from(User.class);
            cq.where(cb.equal(userRoot.get("login"), login));
            User user = em.createQuery(cq).getSingleResult();
            return Optional.ofNullable(user);

        } catch (Exception ex) {
            return Optional.empty();
        }
    }
}
