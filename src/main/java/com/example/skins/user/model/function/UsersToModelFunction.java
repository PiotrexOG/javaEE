package com.example.skins.user.model.function;

import com.example.skins.user.entity.User;
import com.example.skins.user.model.UsersModel;

import java.util.List;
import java.util.function.Function;

public class UsersToModelFunction implements Function<List<User>, UsersModel> {
    @Override
    public UsersModel apply(List<User> entity) {
        return UsersModel.builder()
                .users(entity.stream()
                        .map(User -> UsersModel.User.builder()
                                .id(User.getId())
                                .name(User.getName())
                                .build())
                        .toList())
                .build();
    }
}