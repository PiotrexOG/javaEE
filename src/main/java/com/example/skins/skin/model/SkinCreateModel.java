package com.example.skins.skin.model;

import com.example.skins.c4se.model.CaseModel;
import jakarta.servlet.http.Part;
import lombok.*;

import java.util.UUID;

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
    private String name;

    /**
     * Skin's background story.
     */
    private Float floatValue;


    private CaseModel acase;

}
