package com.example.skins.component;

import com.example.skins.skin.dto.function.SkinToResponseFunction;
import com.example.skins.skin.dto.function.SkinsToResponseFunction;
import com.example.skins.skin.dto.function.RequestToSkinFunction;
import com.example.skins.skin.dto.function.UpdateSkinWithRequestFunction;

import com.example.skins.c4se.dto.function.CasesToResponseFunction;
import com.example.skins.c4se.dto.function.CaseToResponseFunction;
import com.example.skins.c4se.dto.function.RequestToCase;
import com.example.skins.c4se.dto.function.UpdateCaseWithRequestFunction;

import jakarta.enterprise.context.ApplicationScoped;
import com.example.skins.user.dto.GetUserResponse;
import com.example.skins.user.dto.GetUsersResponse;
import com.example.skins.user.dto.PutUserRequest;

import com.example.skins.user.dto.function.*;
import com.example.skins.user.entity.User;

import java.util.function.Function;

/**
 * Factor for creating {@link Function} implementation for converting between various objects used in different layers.
 * Instead of injecting multiple function objects single factory is injected.
 */
@ApplicationScoped
public class DtoFunctionFactory {

    /**
     * Returns a function to convert a {@link PutUserRequest} to a {@link User}.
     *
     * @return RequestToUserFunction instance
     */
    public RequestToUserFunction requestToUser() {
        return new RequestToUserFunction();
    }

    /**
     * Returns a function to update a {@link User}.
     *
     * @return UpdateUserFunction instance
     */
    public UpdateUserWithRequestFunction updateUser() {
        return new UpdateUserWithRequestFunction();
    }

    /**
     * Returns a function to update a {@link User}'s password.
     *
     * @return UpdateUserPasswordFunction instance
     */
    public UpdateUserPasswordWithRequestFunction updateUserPassword() {
        return new UpdateUserPasswordWithRequestFunction();
    }

    /**
     * Returns a function to convert a list of {@link User} to {@link GetUsersResponse}.
     *
     * @return UsersToResponseFunction instance
     */
    public UsersToResponseFunction usersToResponse() {
        return new UsersToResponseFunction();
    }

    /**
     * Returns a function to convert a single {@link User} to {@link GetUserResponse}.
     *
     * @return UserToResponseFunction instance
     */
    public UserToResponseFunction userToResponse() {
        return new UserToResponseFunction();
    }

    public SkinToResponseFunction skinToResponse() {
        return new SkinToResponseFunction();
    }

    public SkinsToResponseFunction skinsToResponse() {
        return new SkinsToResponseFunction();
    }

    public RequestToSkinFunction requestToSkin() {
        return new RequestToSkinFunction();
    }

    public UpdateSkinWithRequestFunction updateSkinWithRequestFunction() {
        return new UpdateSkinWithRequestFunction();
    }

    public CaseToResponseFunction caseToResponse() {
        return new CaseToResponseFunction();
    }

    public CasesToResponseFunction casesToResponse() {
        return new CasesToResponseFunction();
    }

    public RequestToCase requestToCase() {
        return new RequestToCase();
    }

    public UpdateCaseWithRequestFunction updateCaseWithRequest() {
        return new UpdateCaseWithRequestFunction();
    }

}
