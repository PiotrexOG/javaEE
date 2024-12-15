package com.example.skins.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * Base super class providing support for optimistic locking version and creation date.
 */
@MappedSuperclass
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class VersionAndCreationDateAuditable {

    @Version
    private Long version;

    @Column(name = "creation_date_time")
    private LocalDateTime creationDateTime;
    @Column(name = "edit_date_time")
    private LocalDateTime editDateTime;

    @PrePersist
    public void updateCreationDateTime() {
        creationDateTime = LocalDateTime.now();
    }

    @PreUpdate
    public void updateEditDateTime() {
        editDateTime = LocalDateTime.now();
    }
}