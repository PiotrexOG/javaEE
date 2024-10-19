package com.example.skins.user.controller.simple;

import com.example.skins.component.DtoFunctionFactory;
import com.example.skins.controller.servlet.exception.NotFoundException;
import com.example.skins.controller.servlet.exception.BadRequestException;
import com.example.skins.controller.servlet.exception.HttpRequestException;
import com.example.skins.user.controller.api.UserController;
import com.example.skins.user.dto.GetUserResponse;
import com.example.skins.user.dto.GetUsersResponse;
import com.example.skins.user.dto.PatchUserRequest;
import com.example.skins.user.dto.PutUserRequest;
import com.example.skins.user.entity.User;
import com.example.skins.user.service.UserService;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Simple framework agnostic implementation of controller.
 */
public class UserSimpleController implements UserController {

    /**
     * Case service.
     */
    private final UserService service;

    /**
     * Factory producing functions for conversion between DTO and entities.
     */
    private final DtoFunctionFactory factory;


    /**
     * @param service Case service
     * @param factory factory producing functions for conversion between DTO and entities
     */
    public UserSimpleController(UserService service, DtoFunctionFactory factory) {
        this.service = service;
        this.factory = factory;
    }


    @Override
    public GetUserResponse getUser(UUID id) {
        return service.find(id).map(factory.userToResponse()).orElseThrow(NotFoundException::new);
    }

    @Override
    public GetUsersResponse getUsers() {
        List<User> users = service.findAll();
        if (users.isEmpty()) {
            throw new NotFoundException();
        }
        return factory.usersToResponse().apply(users);
    }

    @Override
    public void putUser(UUID id, PutUserRequest request) {
        Optional<User> user = service.find(id);
        if(user.isPresent()){
            throw new BadRequestException("User with id " + id + " already exists");
        }
        try {
            service.create(factory.requestToUser().apply(id, request));
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException();
        }
    }

    @Override
    public void patchUser(UUID id, PatchUserRequest request) {
        service.find(id).ifPresentOrElse(user -> service.update(factory.updateUser().apply(user, request)), () -> {
            throw new NotFoundException();
        });
    }

    @Override
    public void deleteUser(UUID id) {
        service.find(id).ifPresentOrElse(
                service::delete,
                ()->{
                    throw new NotFoundException();
                }
        );
    }

    @Override
    public byte[] getUserAvatar(UUID id, String path) {
        Path pathToAvatar = Paths.get(
                path,
                service.find(id)
                        .map(user -> user.getId().toString())
                        .orElseThrow(() -> new NotFoundException("User does not exist"))
                        + ".png"
        );
        try {
            if (!Files.exists(pathToAvatar)) {
                throw new NotFoundException("User avatar does not exist");
            }
            return Files.readAllBytes(pathToAvatar);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void putUserAvatar(UUID id, InputStream avatar, String path) {
        service.find(id).ifPresentOrElse(
                user -> {
                    service.createAvatar(id, avatar, path);
                },
                () -> {
                    throw new NotFoundException();
                }
        );
    }

    @Override
    public void patchUserAvatar(UUID id, InputStream avatar, String path) {
        service.find(id).ifPresentOrElse(
                user -> service.updateAvatar(id, avatar, path),
                () -> {
                    throw new NotFoundException("User does not exist");
                }
        );
    }

    @Override
    public void deleteUserAvatar(UUID id, String path) {
        service.find(id).ifPresentOrElse(
                user -> {
                    try {
                        Path avatarPath = Paths.get(path, user.getId().toString() + ".png");
                        if (!Files.exists(avatarPath)) {
                            throw new NotFoundException("User avatar does not exist");
                        }
                        Files.delete(avatarPath);
                    } catch (IOException e) {
                        throw new NotFoundException(e);
                    }
                },
                () -> {
                    throw new NotFoundException("User does not exist");
                }
        );
    }
}
