package com.example.skins.user.dto.function;

import com.example.skins.user.dto.PutUserRequest;
import com.example.skins.user.entity.User;
import com.example.skins.user.entity.UserRoles;

import java.util.List;
import java.util.UUID;
import java.util.function.BiFunction;

/**
 * Converts {@link PutUserRequest} to {@link User}. Caution, password should be hashed in business logic.
 */
public class RequestToUserFunction implements BiFunction<UUID, PutUserRequest, User> {

    @Override
    public User apply(UUID id, PutUserRequest request) {
        return User.builder()
                .id(id)
                .login(request.getLogin())
                .name(request.getName())
                .birthDate(request.getBirthDate())
                .surname(request.getSurname())
                .email(request.getEmail())
                .password(request.getPassword())
                .roles(List.of(UserRoles.USER))
                .build();
    }

}
