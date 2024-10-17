package com.example.skins.skin.dto;

import lombok.*;

/**
 * PATCH Skin request. Contains all fields that can be updated by the user. How Skin is described is defined
 * in {@link pl.edu.pg.eti.kask.rpg.Skin.dto.GetSkinsResponse.Skin} and
 * {@link pl.edu.pg.eti.kask.rpg.creature.entity.Creature} classes.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PatchSkinRequest {

    /**
     * Skin's name.
     */
    private String name;

    private Float floatValue;

}
