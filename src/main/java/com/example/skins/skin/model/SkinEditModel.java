package com.example.skins.skin.model;

import jakarta.servlet.http.Part;
import lombok.*;

/**
 * JSF view model class in order to not use entity classes. Represents single Skin to be edited. Includes
 * only fields which can be edited after Skin creation.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class SkinEditModel {

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
     * Multipart part for uploaded portrait file.
     */
    private Part portrait;

}
