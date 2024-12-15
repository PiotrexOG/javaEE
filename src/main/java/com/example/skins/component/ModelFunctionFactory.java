package com.example.skins.component;

import com.example.skins.c4se.model.function.CaseToModelFunction;
import com.example.skins.c4se.model.function.CasesToModelFunction;
import com.example.skins.user.model.function.UserToModelFunction;
import com.example.skins.user.model.function.UsersToModelFunction;
import jakarta.enterprise.context.ApplicationScoped;
import com.example.skins.skin.model.SkinEditModel;
import com.example.skins.skin.model.SkinModel;
import com.example.skins.skin.model.SkinsModel;
import com.example.skins.skin.model.function.*;

import java.util.function.Function;

/**
 * Factor for creating {@link Function} implementation for converting between various objects used in different layers.
 * Instead of injecting multiple function objects single factory is injected.
 */
@ApplicationScoped
public class ModelFunctionFactory {

    /**
     * Returns a function to convert a single {@link Skin} to {@link SkinModel}.
     *
     * @return new instance
     */
    public SkinToModelFunction SkinToModel() {
        return new SkinToModelFunction();
    }

    /**
     * Returns a function to convert a list of {@link Skin} to {@link SkinsModel}.
     *
     * @return new instance
     */
    public SkinsToModelFunction SkinsToModel() {
        return new SkinsToModelFunction();
    }

    /**
     * Returns a function to convert a single {@link Skin} to {@link SkinEditModel}.
     *
     * @return new instance
     */
    public SkinToEditModelFunction SkinToEditModel() {
        return new SkinToEditModelFunction();
    }

    /**
     * Returns a function to convert a single {@link SkinModel} to {@link Skin}.
     *
     * @return new instance
     */
    public ModelToSkinFunction modelToSkin() {
        return new ModelToSkinFunction();
    }


    public CaseToModelFunction CaseToModel() {
        return new CaseToModelFunction();
    }



    public CasesToModelFunction CasesToModel() {
        return new CasesToModelFunction();
    }

    /**
     * Returns a function to update a {@link Skin}.
     *
     * @return UpdateSkinFunction instance
     */
    public UpdateSkinWithModelFunction updateSkin() {
        return new UpdateSkinWithModelFunction();
    }


    public UserToModelFunction userToModel() {
        return new UserToModelFunction();
    }
    public UsersToModelFunction usersToModel() {
        return new UsersToModelFunction();
    }
}
