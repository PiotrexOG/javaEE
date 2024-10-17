package com.example.skins.user.dto.function;

import com.example.skins.user.dto.PutPasswordRequest;
import com.example.skins.user.entity.User;

import java.util.function.BiFunction;

/**
 * Updates password in {@link User} based on {@link PutPasswordRequest}. Caution, password should be hashed in business
 * logic.
 */
public class UpdateUserPasswordWithRequestFunction implements BiFunction<User, PutPasswordRequest, User> {

    @Override
    public User apply(User entity, PutPasswordRequest request) {
        return User.builder()
                .id(entity.getId())
                .login(entity.getLogin())
                .name(entity.getName())
                .birthDate(entity.getBirthDate())
                .surname(entity.getSurname())
                .email(entity.getEmail())
                .password(request.getPassword())
                .build();
    }

}
