package com.example.skins.skin.dto;

import lombok.*;

import java.util.UUID;

/**
 * PUT Skin request. Contains only fields that can be set up byt the user while creating a new Skin.How
 * Skin is described is defined in {@link pl.edu.pg.eti.kask.rpg.Skin.dto.GetSkinsResponse.Skin} and
 * {@link pl.edu.pg.eti.kask.rpg.creature.entity.Creature} classes.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutSkinRequest {

    /**
     * Name of the Skin.
     */
    private String name;

    private Float floatValue;

    private UUID user;
    /**
     * Identifier of the Skin's Case.
     */
    private UUID Case;

}
