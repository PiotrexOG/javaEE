package com.example.skins.c4se.entity;

import com.example.skins.skin.entity.Skin;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
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

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Skin> skins;


}
