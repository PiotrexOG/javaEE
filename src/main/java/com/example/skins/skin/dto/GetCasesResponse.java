package com.example.skins.skin.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

/**
 * GET Cases response. Returns list of all available Cases names.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetCasesResponse {

    /**
     * Represents single Case in list.
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
     * List of all Cases.
     */
    private List<Case> Cases;

}
