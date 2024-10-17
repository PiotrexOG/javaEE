package com.example.skins.component;

import com.example.skins.skin.dto.*;
import com.example.skins.skin.dto.function.*;
import com.example.skins.skin.entity.Case;
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
public class DtoFunctionFactory {

    /**
     * Returns a function to convert a single {@link Skin} to {@link GetSkinResponse}.
     *
     * @return SkinToResponseFunction instance
     */
    public SkinToResponseFunction SkinToResponse() {
        return new SkinToResponseFunction();
    }

    /**
     * Returns a function to convert a list of {@link Skin} to {@link GetSkinsResponse}.
     *
     * @return SkinsToResponseFunction instance
     */
    public SkinsToResponseFunction SkinsToResponse() {
        return new SkinsToResponseFunction();
    }

    /**
     * Returns a function to convert a single {@link Case} to {@link GetCaseResponse}.
     *
     * @return CaseToResponseFunction instance
     */
    public CaseToResponseFunction CaseToResponse() {
        return new CaseToResponseFunction();
    }

    /**
     * Returns a function to convert a list of {@link Case} to {@link GetCasesResponse}.
     *
     * @return CasesToResponseFunction instance
     */
    public CasesToResponseFunction CasesToResponse() {
        return new CasesToResponseFunction();
    }

    /**
     * Returns a function to convert a {@link PutSkinRequest} to a {@link Skin}.
     *
     * @return RequestToSkinFunction instance
     */
    public RequestToSkinFunction requestToSkin() {
        return new RequestToSkinFunction();
    }

    /**
     * Returns a function to update a {@link Skin}.
     *
     * @return UpdateSkinFunction instance
     */
    public UpdateSkinWithRequestFunction updateSkin() {
        return new UpdateSkinWithRequestFunction();
    }

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

}
