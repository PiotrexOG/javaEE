package com.example.skins.skin.dto;

import com.example.skins.user.entity.User;
import lombok.*;

import java.util.UUID;

/**
 * GET Skin response. It contains all field that can be presented (but not necessarily changed) to the used. How
 * Skin is described is defined in {@link pl.edu.pg.eti.kask.rpg.Skin.dto.GetSkinsResponse.Skin}
 * and {@link pl.edu.pg.eti.kask.rpg.creature.entity.Creature} classes.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetSkinResponse {

    /**
     * Represents single Case.
     */
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Case {

        /**
         * Unique id identifying Case.
         */
        private UUID id;

        /**
         * Name of the Case.
         */
        private String name;

    }

    /**
     * Unique id identifying Skin.
     */
    private UUID id;

    /**
     * Name of the Skin.
     */
    private String name;

    private Float floatValue;

    /**
     * Skin's Case.
     */
    private Case Case;

    private User user;
}
