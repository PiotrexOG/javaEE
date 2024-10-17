package com.example.skins.skin.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import com.example.skins.user.entity.User;

import java.io.Serializable;
import java.util.UUID;

/**
 * Entity for game Skin owned by the user. Represents Skins basic stats (see {@link Creature}) as well as
 * Case and skills. Also contains link to user (see @link {@link User}) for the sake of database relationship.
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)

public class Skin implements Serializable {
    private UUID id;

    private String name;
    private Float floatValue;
    private Case caseItem; // Relacja do Case
    private User user; // Relacja do User (1:N)
//    /**
//     * Skin's background story.
//     */
//    private String background;
//
//    /**
//     * Skin's age.
//     */
//    private Integer age;
//
//    /**
//     * Skin's Case (class).
//     */
//    private Case Case;
//
//    /**
//     * Owner of this Skin.
//     */
//    private User user;
//
//    /**
//     * Skin's level.
//     */
//    private Integer level;
//
//    /**
//     * Skin's total experience.
//     */
//    private Integer experience;
//
//    /**
//     * Creature's portrait. Images in database are stored as blobs (binary large objects).
//     */


}
