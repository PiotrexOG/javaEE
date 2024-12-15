package com.example.skins.configuration.observer;


//package pl.edu.pg.eti.kask.rpg.configuration.singleton;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import lombok.NoArgsConstructor;
import com.example.skins.c4se.entity.Case;
import com.example.skins.skin.entity.Skin;
import com.example.skins.c4se.service.CaseService;
import com.example.skins.skin.service.SkinService;
import com.example.skins.user.entity.SkillGroup;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.context.control.RequestContextController;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import lombok.SneakyThrows;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RunAs;
import jakarta.ejb.*;
import com.example.skins.user.entity.UserRoles;

import com.example.skins.user.entity.User;
import com.example.skins.user.service.UserService;
import jakarta.security.enterprise.SecurityContext;
import jakarta.servlet.ServletContextListener;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Listener started automatically on CDI application context initialized. Injects proxy to the services and fills
 * database with default content. When using persistence storage application instance should be initialized only during
 * first run in order to init database with starting data. Good place to create first default admin user.
 */
@Singleton
@Startup
@TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
@NoArgsConstructor
@DependsOn("InitializeAdminService")
@DeclareRoles({UserRoles.ADMIN, UserRoles.USER})
@RunAs(UserRoles.ADMIN)
@Log
public class InitializedData{

    /**
     * User service.
     */
    private  UserService userService;



    private  SkinService skinService;
    private  CaseService caseService;

        @Inject
    private SecurityContext securityContext;

    @EJB
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @EJB
    public void setSkinService(SkinService skinService) {
        this.skinService = skinService;
    }

    @EJB
    public void setCaseService(CaseService caseService) {
        this.caseService = caseService;
    }


    /**
     * Initializes database with some example values. Should be called after creating this object. This object should be
     * created only once.
     */
    @PostConstruct
    @SneakyThrows
    private void init() {

                if (userService.find("piotrulo").isEmpty())  {
            User piotrulo = User.builder()
                    .id(UUID.fromString("c4804e0f-769e-4ab9-9ebe-0578fb4f00a6"))
                    .login("piotrulo")
                    .name("Piotr")
                    .surname("Wesołowski")
                    .birthDate(LocalDate.of(2003, 1, 28))
                    .email("piotrulo@wesoly.example.com")
                    .password("adminadmin")
                    .roles(List.of(UserRoles.ADMIN, UserRoles.USER))
                    .skillGroup(SkillGroup.SILVER)
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
                     .roles(List.of(UserRoles.USER))
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
                    .roles(List.of(UserRoles.USER))
                    .build();

            userService.create(piotrulo);
            userService.create(ewik);
            userService.create(oskar);

            // Tworzenie Case'ów (kategorii skinów)
            Case defaultCase = Case.builder()
                    .id(UUID.fromString("ed6cfb2a-cad7-47dd-9b56-9d1e3c7a4123"))
                    .name("Default Case")
                    .releaseDate(LocalDate.now())
                    .seriesId(1)
                    .build();

            Case premiumCase = Case.builder()
                    .id(UUID.fromString("ed6cfb2a-cad7-47dd-9b56-9d1e3c7a4124"))
                    .name("Premium Case")
                    .releaseDate(LocalDate.now())
                    .seriesId(2)
                    .build();


            // Tworzenie Skinów i przypisanie ich do Case'ów oraz użytkownika
            Skin redSkin = Skin.builder()
                    .id(UUID.fromString("ed6cfb2a-cad7-47dd-9b56-9d1e3c7a4999"))
                    .name("Red Dragon")
                    .floatValue(0.05f)
                    .caseItem(defaultCase)  // przypisanie do defaultCase
                    .user(piotrulo)         // przypisanie do użytkownika
                    .build();

            Skin blueSkin = Skin.builder()
                    .id(UUID.fromString("ed6cfb2a-cad7-47dd-9b56-9d1e3c7a4998"))
                    .name("Blue Phoenix")
                    .floatValue(0.10f)
                    .caseItem(premiumCase)  // przypisanie do premiumCase
                    .user(piotrulo)         // przypisanie do użytkownika
                    .build();

            Skin ewikSkin = Skin.builder()
                    .id(UUID.fromString("ed6cfb2a-cad7-47dd-9b56-9d1e3c7a4997"))
                    .name("ewik Phoenix")
                    .floatValue(0.10f)
                    .caseItem(premiumCase)  // przypisanie do premiumCase
                    .user(ewik)         // przypisanie do użytkownika
                    .build();
            //defaultCase.setSkins(Arrays.asList(redSkin, blueSkin));

            caseService.create(defaultCase);
            caseService.create(premiumCase);

            skinService.create(redSkin);
            skinService.create(blueSkin);
            skinService.create(ewikSkin);

            System.out.println("halo");
            System.out.println(skinService.findAllByCase(UUID.fromString("ed6cfb2a-cad7-47dd-9b56-9d1e3c7a4123")));
            System.out.println("halo");

            //System.out.println(caseService.find(UUID.fromString("ed6cfb2a-cad7-47dd-9b56-9d1e3c7a4123")).get().getSkins());

//        List<Skin> all_skins = skinService.findAll();
//        for (Skin skin : all_skins) {
//            System.out.println(skin);
//        }
//
//        Optional<List<Skin>> skin_of_case = skinService.findAllByCase(UUID.fromString("ed6cfb2a-cad7-47dd-9b56-9d1e3c7a4123"));
//
//
//        if (skin_of_case.isPresent()) {
//            for (Skin skin : skin_of_case.get()) {
//                System.out.println(skin);
//            }
//        } else {
//            System.out.println("No skins found for the given case.");
//        }
//
//        skinService.addSkinToCase(UUID.fromString("ed6cfb2a-cad7-47dd-9b56-9d1e3c7a4123"), blueSkin);
//        System.out.println("po zmianie\n");
//
//        Optional<List<Skin>> skin_of_case_updated = skinService.findAllByCase(UUID.fromString("ed6cfb2a-cad7-47dd-9b56-9d1e3c7a4123"));
//
//        if (skin_of_case_updated.isPresent()) {
//            for (Skin skin : skin_of_case_updated.get()) {
//                System.out.println(skin);
//            }
//        } else {
//            System.out.println("No skins found for the given case.");
//        }
//                        System.out.println("po zmianie 2\n");

//        skinService.delete(UUID.fromString("ed6cfb2a-cad7-47dd-9b56-9d1e3c7a4999"));
//
//        Optional<List<Skin>> skin_of_case_updated2 = skinService.findAllByCase(UUID.fromString("ed6cfb2a-cad7-47dd-9b56-9d1e3c7a4123"));
//
//        if (skin_of_case.isPresent()) {
//            for (Skin skin : skin_of_case_updated2.get()) {
//                System.out.println(skin);
//            }
//        } else {
//            System.out.println("No skins found for the given case.");
//        }

//                        System.out.println("po zmianie 3\n");
//        caseService.deleteCaseAndUnassignSkins(UUID.fromString("ed6cfb2a-cad7-47dd-9b56-9d1e3c7a4123"));
//
//        Optional<List<Skin>> skin_of_case_updated3 = skinService.findAllByCase(UUID.fromString("ed6cfb2a-cad7-47dd-9b56-9d1e3c7a4123"));
//
//        if (skin_of_case_updated3.isPresent()) {
//            for (Skin skin : skin_of_case_updated3.get()) {
//                System.out.println(skin);
//            }
//        } else {
//            System.out.println("No skins found for the given case.");
//        }
//
//                List<Skin> all_skins2 = skinService.findAll();
//        for (Skin skin : all_skins2) {
//            System.out.println(skin);
//        }
        }

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
