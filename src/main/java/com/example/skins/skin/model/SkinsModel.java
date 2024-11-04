package com.example.skins.skin.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * JSF view model class in order to not use entity classes. Represents list of Skins to be displayed.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class SkinsModel implements Serializable {

    /**
     * Represents single Skin in list.
     */
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Skin {

        /**
         * Unique id identifying Skin.
         */
        private UUID id;

        /**
         * Name of the Skin.
         */
        private String name;

    }

    /**
     * Name of the selected Skins.
     */
    @Singular
    private List<Skin> Skins;

}
