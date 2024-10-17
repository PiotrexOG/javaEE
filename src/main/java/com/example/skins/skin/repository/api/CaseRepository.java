package com.example.skins.skin.repository.api;

import com.example.skins.skin.entity.Case;
import com.example.skins.repository.api.Repository;

import java.util.UUID;

/**
 * Repository for Case entity. Repositories should be used in business layer (e.g.: in services).
 */
public interface CaseRepository extends Repository<Case, UUID> {

}
