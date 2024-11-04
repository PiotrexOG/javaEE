package com.example.skins.c4se.repository.api;

import com.example.skins.c4se.entity.Case;
import com.example.skins.repository.api.Repository;

import java.util.UUID;

/**
 * Repository for Case entity. Repositories should be used in business layer (e.g.: in services).
 */
public interface CaseRepository extends Repository<Case, UUID> {

}
