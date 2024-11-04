package com.example.skins.skin.model;

import lombok.*;

/**
 * JSF view model class in order to not use entity classes. Represents single Skin to be displayed.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class SkinModel {

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
    private int age;

    /**
     * Skin's strength.
     */
    private int strength;

    /**
     * Skin's constitution.
     */
    private int constitution;

    /**
     * Skin's charisma.
     */
    private int charisma;

    /**
     * Skin's health.
     */
    private int health;

    /**
     * Skin's level.
     */
    private int level;

    /**
     * Skin's total experience.
     */
    private int experience;

    /**
     * Name of the Skin's Case.
     */
    private String Case;

}
