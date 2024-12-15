package com.example.skins.skin.model;

import lombok.*;

import java.time.LocalDateTime;

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
    private Float floatValue;


    private String acase;

    private Long version;

    private LocalDateTime creationDateTime;

}
