package com.example.skins.skin.dto;

import com.example.skins.skin.entity.Skin;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * GET Case response. Described details about selected Case. Can be used to present description while
 * Skin creation or on Skin's stat page. How Case is described is defined in
 * {@link pl.edu.pg.eti.kask.rpg.Skin.entity.Case}.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetCaseResponse {

    /**
     * Describes single skill. Returning Case description without skills list would not give all required
     * information. Forcing to return list of skills in separate request would be unnecessary transfer growth. How
     * skills are described is defined in {@link pl.edu.pg.eti.kask.rpg.Skin.entity.Skill}.
     */
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Skill {

        /**
         * Unique id identifying skill.
         */
        private UUID id;

        /**
         * Name of the skill.
         */
        private String name;

        /**
         * Description of the skill.
         */
        private String description;

    }

    /**
     * Unique id identifying Case.
     */
    private UUID id;

    /**
     * Name of the Case.
     */
    private String name;

    private LocalDate releaseDate;
    private Integer seriesId;
    /**
     * Set of skills available to this Case on different levels.
     */
    @Singular
    private List<Skin> skins;

}
