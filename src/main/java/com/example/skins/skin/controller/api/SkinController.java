package com.example.skins.skin.controller.api;

import com.example.skins.skin.dto.GetSkinResponse;
import com.example.skins.skin.dto.GetSkinsResponse;
import com.example.skins.skin.dto.PatchSkinRequest;
import com.example.skins.skin.dto.PutSkinRequest;

import java.io.InputStream;
import java.util.UUID;

/**
 * Controller for managing collections Skins' representations.
 */
public interface SkinController {

    /**
     * @return all Skins representation
     */
    GetSkinsResponse getSkins();

    /**
     * @param id Case's id
     * @return Skins representation
     */
    GetSkinsResponse getCaseSkins(UUID id);

    /**
     * @param id user's id
     * @return Skins representation
     */
    GetSkinsResponse getUserSkins(UUID id);

    /**
     * @param uuid Skin's id
     * @return Skin representation
     */
    GetSkinResponse getSkin(UUID uuid);

    /**
     * @param id      Skin's id
     * @param request new Skin representation
     */
    void putSkin(UUID id, PutSkinRequest request);

    /**
     * @param id      Skin's id
     * @param request Skin update representation
     */
    void patchSkin(UUID id, PatchSkinRequest request);

    /**
     * @param id Skin's id
     */
    void deleteSkin(UUID id);

    /**
     * @param id Skin's id
     * @return Skin's portrait
     */
    byte[] getSkinPortrait(UUID id);

    /**
     * @param id       Skin's id
     * @param portrait Skin's new avatar
     */
    void putSkinPortrait(UUID id, InputStream portrait);

}
