package com.example.skins.configuration.observer;

import com.example.skins.user.entity.SkillGroup;
import com.example.skins.user.entity.User;
import com.example.skins.user.entity.UserRoles;
import com.example.skins.user.repository.api.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Singleton
@Startup
@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
@NoArgsConstructor(force = true)
public class InitializeAdminService {
    private final UserRepository  userRepository;

    private final Pbkdf2PasswordHash passwordHash;

    @Inject
    public InitializeAdminService(UserRepository userRepository,
                                  Pbkdf2PasswordHash passwordHash){
        this.userRepository = userRepository;
        this.passwordHash = passwordHash;
    }

    @PostConstruct
    private void init(){
        if(userRepository.findByLogin("admin-service").isEmpty()){
            User admin = User.builder()
                    .id(UUID.fromString("00000000-0000-0000-0000-000000001234"))
                    .login("admin-service")
                    .name("adminowiec")
                    .surname("adminowski")
                    .email("admin@wesoly.example.com")
                    .password(passwordHash.generate("adminadmin".toCharArray()))
                    .roles(List.of(UserRoles.ADMIN, UserRoles.USER))
                    .birthDate(LocalDate.of(1990, 2, 2))
                    .skins(new ArrayList<>())
                    .skillGroup(SkillGroup.GLOBAL)
                    .build();

            userRepository.create(admin);
        }
    }
}
