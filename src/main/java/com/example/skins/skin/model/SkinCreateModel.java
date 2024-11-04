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
    private String background;

    /**
     * Skin's age.
     */
    private Integer age;

    /**
     * Skin's strength.
     */
    private Integer strength;

    /**
     * Skin's constitution.
     */
    private Integer constitution;

    /**
     * Skin's charisma.
     */
    private Integer charisma;

    /**
     * Skin's health.
     */
    private Integer health;

    /**
     * Skin's level.
     */
    private Integer level;

    /**
     * Skin's total experience.
     */
    private Integer experience;

    /**
     * Multipart part for uploaded portrait file.
     */
    private Part portrait;

    /**
     * Skin's Case.
     */
    private CaseModel Case;

}
