package com.example.skins.skin.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

//@Dependent
public class SkinFloatValidator implements ConstraintValidator<ValidSkinFloat, Float> {
    @Override
    public boolean isValid(Float value, ConstraintValidatorContext context) {
        if (value > 0.9f) {
            return false;
        }

        return true;
    }
}
