package com.example.skins.user.controller.rest;

import com.example.skins.component.DtoFunctionFactory;
import com.example.skins.user.controller.api.UserController;
import com.example.skins.user.dto.GetUserResponse;
import com.example.skins.user.dto.GetUsersResponse;
import com.example.skins.user.dto.PatchUserRequest;
import com.example.skins.user.dto.PutUserRequest;
import com.example.skins.user.entity.User;
import com.example.skins.user.service.UserService;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.TransactionalException;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import jakarta.ejb.EJB;
import com.example.skins.user.entity.UserRoles;
import com.example.skins.user.service.UserService;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;

@Path("")
@Log
@RolesAllowed(UserRoles.USER)
public class UserRestController implements UserController {
    private UserService service;
    private final DtoFunctionFactory factory;

    private final UriInfo uriInfo;
    private HttpServletResponse response;

    @EJB
    public void setService(UserService service){
        this.service = service;
    }

    @Context
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    @Inject
    public UserRestController(DtoFunctionFactory dtoFunctionFactory, UriInfo uriInfo) {
        factory = dtoFunctionFactory;
        this.uriInfo = uriInfo;
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
    @SneakyThrows
        @PermitAll
    public void putUser(UUID id, PutUserRequest request) {
        try {
            service.create(factory.requestToUser().apply(id, request));
            response.setHeader("Location", uriInfo.getBaseUriBuilder()
                    .path(UserController.class, "getUser")
                    .build(id)
                    .toString());
            throw new WebApplicationException(Response.Status.CREATED);
        } catch (TransactionalException ex) {
            if (ex.getCause() instanceof IllegalArgumentException) {
                log.log(Level.WARNING, ex.getMessage(), ex);
                throw new BadRequestException(ex);
            }
            throw ex;
        }
    }

    @Override
    public void patchUser(UUID id, PatchUserRequest request) {
        service.find(id).ifPresentOrElse(user -> service.update(factory.updateUserWithRequest().apply(user, request)), () -> {
            throw new NotFoundException();
        });
    }

    @Override
    public void deleteUser(UUID id) {
        service.find(id).ifPresentOrElse(
                entity -> service.delete(id),
                () -> {
                    throw new NotFoundException();
                }
        );
    }

//    @Override
//    public byte[] getUserAvatar(UUID id) {
//        return service.find(id)
//                .map(User::getAvatar)
//                .orElseThrow(NotFoundException::new);
//    }
//
////    @Override
////    public void putUserAvatar(UUID id, InputStream avatar) {
////        service.find(id).ifPresentOrElse(
////                user -> {
////                    service.createAvatar(id, avatar);
////                },
////                () -> {
////                    throw new NotFoundException();
////                }
////        );
////    }
//
//    @Override
//    public void patchUserAvatar(UUID id, InputStream avatar) {
//        service.find(id).ifPresentOrElse(
//                user -> service.updateAvatar(id, avatar),
//                () -> {
//                    throw new NotFoundException("User does not exist");
//                }
//        );
//    }
//
//    @Override
//    public void deleteUserAvatar(UUID id) {
//        service.find(id).ifPresentOrElse(
//                user -> {
//                    user.setAvatar(null);
//                    service.update(user);
//                },
//                () -> {
//                    throw new NotFoundException("User does not exist");
//                }
//        );
//    }
}
