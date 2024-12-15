package com.example.skins.skin.service;

import com.example.skins.c4se.entity.Case;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.EJBAccessException;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import com.example.skins.c4se.repository.api.CaseRepository;
import jakarta.inject.Inject;
import com.example.skins.user.entity.UserRoles;
import jakarta.ws.rs.NotFoundException;
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
        userRepository.find(skin.getUser().getId())
               .ifPresent(user -> user.getSkins().add(skin));
    }

//     public void addSkinToCase(UUID caseId, Skin skin) {
//        skinRepository.addSkinToCase(caseId, skin);
//    }

    @RolesAllowed(UserRoles.USER)
    public void createForCallerPrincipal(Skin skin) {
        User user = userRepository.findByLogin(securityContext.getCallerPrincipal().getName())
                .orElseThrow(IllegalStateException::new);
        skin.setUser(user);
        skinRepository.create(skin);
    }
    /**
     * Updates existing skin.
     *
     * @param skin skin to be updated
     */
    public void update(Skin skin) {
        skinRepository.update(skin);
    }

        @RolesAllowed(UserRoles.USER)
    public void update(Skin skin, UUID initialCase) {
        Skin existingSkin = skinRepository.find(skin.getId())
                .orElseThrow(() -> new NotFoundException("Skin not found: " + skin.getId()));

        checkAdminRoleOrOwner(Optional.of(existingSkin));

        Case newCase = caseRepository.find(skin.getCaseItem().getId())
                .orElseThrow(() -> new NotFoundException("Case not found: " + skin.getCaseItem().getId()));

        if (!initialCase.equals(newCase.getId())) {
            Case oldCase = caseRepository.find(initialCase)
                    .orElseThrow(() -> new NotFoundException("Initial case not found: " + initialCase));

            oldCase.getSkins().removeIf(f -> f.getId().equals(existingSkin.getId()));
            caseRepository.update(oldCase);
        }

        existingSkin.setName(skin.getName());
        existingSkin.setFloatValue(skin.getFloatValue());
        existingSkin.setCaseItem(newCase);

        userRepository.update(existingSkin.getUser());
        caseRepository.update(newCase);

        skinRepository.update(existingSkin);
    }

    /**
     * Deletes existing skin.
     *
     * @param id existing skin's id to be deleted
     */
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

    private void checkAdminRoleOrOwner(Optional<Skin> skin) throws EJBAccessException {
        if (securityContext.isCallerInRole(UserRoles.ADMIN)) {
            return;
        }
        if (securityContext.isCallerInRole(UserRoles.USER)
                && skin.isPresent()
                && skin.get().getUser().getName().equals(securityContext.getCallerPrincipal().getName())) {
            return;
        }
        throw new EJBAccessException("Caller not authorized.");
    }
}
