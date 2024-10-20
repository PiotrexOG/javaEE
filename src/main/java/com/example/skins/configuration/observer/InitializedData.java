package com.example.skins.configuration.observer;

import com.example.skins.skin.entity.Skin;
import com.example.skins.user.entity.SkillGroup;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.context.control.RequestContextController;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import lombok.SneakyThrows;
import com.example.skins.skin.entity.Case;
import com.example.skins.skin.entity.Skill;

import com.example.skins.user.entity.User;
import com.example.skins.user.entity.UserRoles;
import com.example.skins.user.service.UserService;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Listener started automatically on CDI application context initialized. Injects proxy to the services and fills
 * database with default content. When using persistence storage application instance should be initialized only during
 * first run in order to init database with starting data. Good place to create first default admin user.
 */
@ApplicationScoped
public class InitializedData{

    /**
     * User service.
     */
    private final UserService userService;

    /**
     * The CDI container provides a built-in instance of {@link RequestContextController} that is dependent scoped for
     * the purposes of activating and deactivating.
     */
    private final RequestContextController requestContextController;

    /**
     * @param userService              user service
     * @param requestContextController CDI request context controller
     */
    @Inject
    public InitializedData(
            UserService userService,
            RequestContextController requestContextController
    ) {
        this.userService = userService;
        this.requestContextController = requestContextController;
    }

    public void contextInitialized(@Observes @Initialized(ApplicationScoped.class) Object init) {
        init();
    }


    /**
     * Initializes database with some example values. Should be called after creating this object. This object should be
     * created only once.
     */
    @SneakyThrows
    private void init() {
        requestContextController.activate();// start request scope in order to inject request scoped repositories


        User piotrulo = User.builder()
                .id(UUID.fromString("c4804e0f-769e-4ab9-9ebe-0578fb4f00a6"))
                .login("piotrulo")
                .name("Piotr")
                .surname("Wesołowski")
                .birthDate(LocalDate.of(2003, 1, 28))
                .email("piotrulo@wesoly.example.com")
                .password("adminadmin")
                .skillGroup(SkillGroup.SILVER)
    //            .avatar(getResourceAsByteArray("/test/avatar/calvian.png"))
                .build();

        User ewik = User.builder()
                .id(UUID.fromString("81e1c2a9-7f57-439b-b53d-6db88b071e4e"))
                .login("ewik")
                .name("Jakub")
                .surname("Wojtalewicz")
                .birthDate(LocalDate.of(2002, 2, 6))
                .email("ewik@example.com")
                .password("useruser")
                .skillGroup(SkillGroup.GLOBAL)
                .build();

        User oskar = User.builder()
                .id(UUID.fromString("ed6cfb2a-cad7-47dd-9b56-9d1e3c7a4197"))
                .login("oskar")
                .name("Oskar")
                .surname("Wilda")
                .birthDate(LocalDate.of(2002, 5, 8))
                .email("oski@example.com")
                .password("useruser")
                .skillGroup(SkillGroup.GOLD)
                .build();

        userService.create(piotrulo);
        userService.create(ewik);
        userService.create(oskar);

        requestContextController.deactivate();

    }

    /**
     * @param name name of the desired resource
     * @return array of bytes read from the resource
     */
    @SneakyThrows
    private byte[] getResourceAsByteArray(String name) {
        try (InputStream is = this.getClass().getResourceAsStream(name)) {
            if (is != null) {
                return is.readAllBytes();
            } else {
                throw new IllegalStateException("Unable to get resource %s".formatted(name));
            }
        }
    }

}