package com.example.skins.skin.model;

import com.example.skins.c4se.model.CaseModel;
import com.example.skins.skin.validator.ValidSkinFloat;
import com.example.skins.user.entity.User;
import jakarta.servlet.http.Part;
import lombok.*;

import java.util.UUID;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * JSF view model class in order to not use entity classes. Represents new Skin to be created. Includes oll
 * fields which can be used in Skin creation.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class SkinCreateModel {

    /**
     * Skin's id.
     */
    private UUID id;

    /**
     * Name of the Skin.
     */
        @NotBlank
    private String name;

    /**
     * Skin's background story.
     */
//        @NotNull
//    private User user;

    @Min(0)
    @ValidSkinFloat
    private Float floatValue;

    @NotNull
    private CaseModel acase;

}
