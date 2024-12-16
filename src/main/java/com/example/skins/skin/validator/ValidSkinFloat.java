package com.example.skins.skin.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SkinFloatValidator.class)
@Documented
public @interface ValidSkinFloat {
    String message() default "The value must be a float between 0.00 and 0.9";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
