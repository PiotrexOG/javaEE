package com.example.skins.skin.controller.api;

import com.example.skins.skin.dto.GetCasesResponse;

/**
 * Controller for managing collections Cases' representations.
 */
public interface CaseController {

    /**
     * @return all Cases representation
     */
    GetCasesResponse getCases();

}
