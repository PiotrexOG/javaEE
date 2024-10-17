package com.example.skins.skin.entity;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Entity class for game Skins' Cases (classes). Describes name of the Case and skills available on
 * different levels.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class Case implements Serializable {

    /**
     * Unique id (primary key).
     */
    private UUID id;

    /**
     * Name of the Case.
     */
    private String name;

    /**
     * Release date of the Case.
     */
    private LocalDate releaseDate;

    private Integer seriesId;

    @Singular
    private List<Skin> skins;

//    /**
//     * Set of skills available on different levels. While leveling up, Skin gains access to new skills. One skill
//     * every limit level. There is no rule which levels are limit ones.
//     */
//    @Singular
//    private Map<Integer, Skill> skills;

}
