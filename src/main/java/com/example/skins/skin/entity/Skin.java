package com.example.skins.skin.entity;


import com.example.skins.c4se.entity.Case;
import com.example.skins.entity.VersionAndCreationDateAuditable;
import jakarta.persistence.*;
import jakarta.persistence.Table;
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
@ToString()
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "skins")

public class Skin extends VersionAndCreationDateAuditable implements Serializable {
    @Id
    private UUID id;

    private String name;
    private Float floatValue;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
  //  @JoinColumn(name = "case")
    private Case caseItem; // Relacja do Case

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
  //  @JoinColumn(name = "user_name")
    private User user; // Relacja do User (1:N)


    @Override
    public void updateCreationDateTime()
    {
        super.updateCreationDateTime();
    }
    @Override
    public void updateEditDateTime()
    {
        super.updateEditDateTime();
    }

}
