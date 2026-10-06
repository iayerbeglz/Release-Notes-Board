package com.dbcargo.notesboard.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class ItemStatusValidator implements ConstraintValidator<ItemStatusConstraint, String> {

    List<String> validStatus = List.of("DRAFT", "PREVIEW", "PUBLISHED");

    @Override
    public boolean isValid(String status, ConstraintValidatorContext constraintValidatorContext) {
        return validStatus.contains(status);
    }
}
