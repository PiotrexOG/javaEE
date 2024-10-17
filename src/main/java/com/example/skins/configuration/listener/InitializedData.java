package com.example.skins.configuration.listener;

import com.example.skins.skin.entity.Skin;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.SneakyThrows;
import com.example.skins.skin.entity.Case;
import com.example.skins.skin.entity.Skill;
import com.example.skins.skin.service.SkinService;
import com.example.skins.skin.service.CaseService;
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
     * Skin service.
     */
    private SkinService SkinService;

    /**
     * User service.
     */
    private UserService userService;

    /**
     * Case service.
     */
    private CaseService CaseService;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        //SkinService = (SkinService) event.getServletContext().getAttribute("SkinService");
        userService = (UserService) event.getServletContext().getAttribute("userService");
        //CaseService = (CaseService) event.getServletContext().getAttribute("CaseService");
        init();
    }

    /**
     * Initializes database with some example values. Should be called after creating this object. This object should be
     * created only once.
     */
    @SneakyThrows
    private void init() {
        User admin = User.builder()
                .id(UUID.fromString("c4804e0f-769e-4ab9-9ebe-0578fb4f00a6"))
                .login("admin")
                .name("System")
                .surname("Admin")
                .birthDate(LocalDate.of(1990, 10, 21))
                .email("admin@simplerpg.example.com")
                .password("adminadmin")
                .roles(List.of(UserRoles.ADMIN, UserRoles.USER))
                .build();

        User kevin = User.builder()
                .id(UUID.fromString("81e1c2a9-7f57-439b-b53d-6db88b071e4e"))
                .login("kevin")
                .name("Kevin")
                .surname("Pear")
                .birthDate(LocalDate.of(2001, 1, 16))
                .email("kevin@example.com")
                .password("useruser")
                .roles(List.of(UserRoles.USER))
                .build();

        User alice = User.builder()
                .id(UUID.fromString("ed6cfb2a-cad7-47dd-9b56-9d1e3c7a4197"))
                .login("alice")
                .name("Alice")
                .surname("Grape")
                .birthDate(LocalDate.of(2002, 3, 19))
                .email("alice@example.com")
                .password("useruser")
                .roles(List.of(UserRoles.USER))
                .build();

        userService.create(admin);
        userService.create(kevin);
        userService.create(alice);

//        Case bard = Case.builder()
//                .id(UUID.fromString("f5875513-bf7b-4ae1-b8a5-5b70a1b90e76"))
//                .name("Bard")
//                .releaseDate(LocalDate.of(2002, 3, 19))
//                .build();
//
//        Case cleric = Case.builder()
//                .id(UUID.fromString("5d1da2ae-6a14-4b6d-8b4f-d117867118d4"))
//                .name("Cleric")
//                .build();
//
//        Case warrior = Case.builder()
//                .id(UUID.fromString("2d9b1e8c-67c5-4188-a911-5f064a63d8cd"))
//                .name("Warrior")
//                .build();
//
//        Case rogue = Case.builder()
//                .id(UUID.randomUUID())
//                .name("Rogue")
//                .build();
//
//        CaseService.create(bard);
//        CaseService.create(cleric);
//        CaseService.create(warrior);
//        CaseService.create(rogue);
//
//        Skin calvian = Skin.builder()
//                .id(UUID.fromString("525d3e7b-bb1f-4c13-bf17-926d1a12e4c0"))
//                .name("Calvian")
//                .floatValue(18f)
//                .caseItem(bard)
//                .user(kevin)
//                .build();
//
//        Skin uhlbrecht = Skin.builder()
//                .id(UUID.fromString("cc0b0577-bb6f-45b7-81d6-3db88e6ac19f"))
//                .name("Uhlbrecht")
//                .floatValue(37f)
//                .caseItem(warrior)
//                .user(kevin)
//                .build();
//
//        Skin eloise = Skin.builder()
//                .id(UUID.fromString("f08ef7e3-7f2a-4378-b1fb-2922d730c70d"))
//                .name("Eloise")
//                .floatValue(32f)
//                .caseItem(cleric)
//                .user(alice)
//                .build();
//
//        Skin zereni = Skin.builder()
//                .id(UUID.fromString("ff327e8a-77c0-4f9b-90a2-89e16895d1e1"))
//                .name("Zereni")
//                .floatValue(20f)
//                .caseItem(rogue)
//                .user(alice)
//                .build();
//
//        SkinService.create(calvian);
//        SkinService.create(uhlbrecht);
//        SkinService.create(eloise);
//        SkinService.create(zereni);
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