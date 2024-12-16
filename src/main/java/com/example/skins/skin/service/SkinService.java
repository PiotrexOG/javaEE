package com.example.skins.skin.service;

import com.example.skins.Logger.Loggable;
import jakarta.ejb.EJBAccessException;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import com.example.skins.c4se.repository.api.CaseRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import com.example.skins.user.entity.UserRoles;
import lombok.NoArgsConstructor;
import com.example.skins.skin.repository.api.SkinRepository;
import com.example.skins.skin.entity.Skin;
import com.example.skins.user.entity.User;
import com.example.skins.user.repository.api.UserRepository;
import jakarta.security.enterprise.SecurityContext;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service layer for all business actions regarding skin entity.
 */
@LocalBean
@Stateless
@NoArgsConstructor(force = true)
public class SkinService {

    /**
     * Repository for skin entity.
     */
    private final SkinRepository skinRepository;

    /**
     * Repository for case entity.
     */
    private final CaseRepository caseRepository;

    /**
     * Repository for user entity.
     */
    private final UserRepository userRepository;

    private final SecurityContext securityContext;

    /**
     * @param skinRepository  repository for skin entity
     * @param caseRepository repository for case entity
     * @param userRepository repository for user entity
     */
    @Inject
    public SkinService(SkinRepository skinRepository, CaseRepository caseRepository, UserRepository userRepository, SecurityContext securityContext) {
        this.skinRepository = skinRepository;
        this.caseRepository = caseRepository;
        this.userRepository = userRepository;
        this.securityContext = securityContext;
    }

    /**
     * Finds single skin.
     *
     * @param id skin's id
     * @return container with skin
     */
    public Optional<Skin> find(UUID id) {
        return skinRepository.find(id);
    }

    /**
     * @param id   skin's id
     * @param user existing user
     * @return selected skin for user
     */
    public Optional<Skin> find(User user, UUID id) {
        return skinRepository.findByIdAndUser(id, user);
    }

    /**
     * @return all available skins
     */
    public List<Skin> findAll() {
        return skinRepository.findAll();
    }

    /**
     * @param user existing user, skin's owner
     * @return all available skins of the selected user
     */
    public List<Skin> findAll(User user) {
        return skinRepository.findAllByUser(user);
    }

    /**
     * Creates new skin.
     *
     */
    public List<Skin> findAllForCallerPrincipal() {
    if (securityContext.isCallerInRole(UserRoles.ADMIN)) {
        return findAll();
    }
    User user = userRepository.findByLogin(securityContext.getCallerPrincipal().getName())
            .orElseThrow(IllegalStateException::new);
    return findAll(user);
    }

    @Loggable
    public void create(Skin skin) {
        if (skinRepository.find(skin.getId()).isPresent()) {
            throw new IllegalArgumentException("Skin already exists.");
        }
        if (caseRepository.find(skin.getCaseItem().getId()).isEmpty()) {
            throw new IllegalArgumentException("Case does not exists.");
        }
        skinRepository.create(skin);
        /* Both sides of relationship must be handled (if accessed) because of cache. */
        caseRepository.find(skin.getCaseItem().getId())
                .ifPresent(aCase -> aCase.getSkins().add(skin));
//        userRepository.find(skin.getUser().getId())
//               .ifPresent(user -> user.getSkins().add(skin));
    }

//     public void addSkinToCase(UUID caseId, Skin skin) {
//        skinRepository.addSkinToCase(caseId, skin);
//    }
    /**
     * Updates existing skin.
     *
     * @param skin skin to be updated
     */
    @Loggable
    public void update(Skin skin) {
        skinRepository.update(skin);
    }

    /**
     * Deletes existing skin.
     *
     * @param id existing skin's id to be deleted
     */
    @Loggable
    public void delete(UUID id) {
    //    skinRepository.delete(skinRepository.find(id).orElseThrow());

        User user = userRepository.findByLogin(securityContext.getCallerPrincipal().getName()).get();

        skinRepository.find(id).ifPresent(skin -> {
            if(!securityContext.isCallerInRole(UserRoles.ADMIN) && !skin.getUser().getId().equals(user.getId()))
                throw new EJBAccessException("Caller does not have sufficient rights to delete this skin.");
            var aCase = skin.getCaseItem();
            var skins = skin.getCaseItem().getSkins();
            skins.removeIf(skin1 -> skin1.getId().equals(id));
            aCase.setSkins(skins);
        });
        skinRepository.delete(skinRepository.find(id).orElseThrow());
    }


    public Optional<List<Skin>> findAllByCase(UUID id) {
        return caseRepository.find(id)
                .map(skinRepository::findAllByCase);
    }

    public Optional<List<Skin>> findAllByUser(UUID id) {
        return userRepository.find(id)
                .map(skinRepository::findAllByUser);
    }
}
