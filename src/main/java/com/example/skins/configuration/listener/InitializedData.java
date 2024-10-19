package com.example.skins.configuration.listener;

import com.example.skins.skin.entity.Skin;
import com.example.skins.user.entity.SkillGroup;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
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
 * Listener started automatically on servlet context initialized. Fetches instance of the datasource from the servlet
 * context and fills it with default content. Normally this class would fetch database datasource and init data only in
 * cases of empty database. When using persistence storage application instance should be initialized only during first
 * run in order to init database with starting data. Good place to create first default admin user.
 */
@WebListener//using annotation does not allow configuring order
public class InitializedData implements ServletContextListener {

    /**
     * User service.
     */
    private UserService userService;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        userService = (UserService) event.getServletContext().getAttribute("userService");
        init();
    }

    /**
     * Initializes database with some example values. Should be called after creating this object. This object should be
     * created only once.
     */
    @SneakyThrows
    private void init() {
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
