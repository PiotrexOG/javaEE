package com.example.skins.skin.entity;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

/**
 * Entity class describing single skill. Skill has its flavour text for name and description and formal list of effects.
 * Effects can not be defined by use (admin) and be stored in database. Effects are predefined in application source
 * code.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class Skill implements Serializable {

    /**
     * Unique id (primary key).
     */
    private UUID id;

    /**
     * Name of the skill.
     */
    private String name;

    /**
     * Flavour text description for users.
     */
    private String description;

}
