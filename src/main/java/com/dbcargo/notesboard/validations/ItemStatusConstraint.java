package com.dbcargo.notesboard.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ItemStatusValidator.class)
public @interface ItemStatusConstraint {
    String message() default "{Invalid item status}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}