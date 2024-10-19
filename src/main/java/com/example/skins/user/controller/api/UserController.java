package com.example.skins.user.controller.api;

import com.example.skins.user.dto.GetUserResponse;
import com.example.skins.user.dto.GetUsersResponse;
import com.example.skins.user.dto.PatchUserRequest;
import com.example.skins.user.dto.PutUserRequest;

import java.io.InputStream;
import java.util.UUID;

/**
 * Controller for managing collections Cases' representations.
 */
public interface UserController {

    GetUserResponse getUser(UUID id);
    GetUsersResponse getUsers();

    void putUser(UUID id, PutUserRequest request);
    void patchUser(UUID id, PatchUserRequest request);
    void deleteUser(UUID id);
    byte[] getUserAvatar(UUID id, String path);

    void putUserAvatar(UUID id, InputStream avatar, String path);
    void patchUserAvatar(UUID id, InputStream avatar, String path);

    void deleteUserAvatar(UUID id, String path);

}
