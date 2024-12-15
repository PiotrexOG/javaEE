package com.example.skins.user.model.function;

import com.example.skins.user.entity.User;
import com.example.skins.user.model.UserModel;

import java.io.Serializable;
import java.util.function.Function;

public class UserToModelFunction implements Function<User, UserModel>, Serializable {
    @Override
    public UserModel apply(User user) {
        return UserModel.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }
}