package com.example.skins.skin.dto.function;


import com.example.skins.skin.dto.GetSkinResponse;
import com.example.skins.skin.entity.Skin;
import com.example.skins.user.dto.GetUsersResponse;

import java.util.function.Function;

public class SkinToResponseFunction implements Function<Skin, GetSkinResponse> {
    @Override
    public GetSkinResponse apply(Skin skin) {
        return GetSkinResponse.builder()
                .id(skin.getId())
                .name(skin.getName())
                .floatValue(skin.getFloatValue())
             //   .version(skin.getVersion())
                .user(GetUsersResponse.User.builder()
                        .login(skin.getUser().getLogin())
                        .id(skin.getUser().getId())
                        .build())
                .Case(GetSkinResponse.Case.builder()
                        .name(skin.getCaseItem().getName())
                        .uuid(skin.getCaseItem().getId())
                        .build())
                .build();
    }
}
