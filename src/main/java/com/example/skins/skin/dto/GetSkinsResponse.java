package com.example.skins.skin.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

/**
 * GET Skins response. Contains list of available Skins. Can be used to list particular user's Skins as
 * well as all Skins in the game.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetSkinsResponse {

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
