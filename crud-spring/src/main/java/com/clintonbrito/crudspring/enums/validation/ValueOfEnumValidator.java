package com.clintonbrito.crudspring.enums.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnum, CharSequence> {
    private List<String> acceptedValues;

    @Override
    public void initialize(ValueOfEnum annotation) {
        acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
                .map(Enum::toString)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
//        if (value == null || "".equals(value.toString().trim())) {  
        if (value == null || value.toString().trim().isEmpty()) {
            return true;
        }

        if (!acceptedValues.contains(value.toString())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                            "must be any of enum " + acceptedValues)
                    .addConstraintViolation();

            return false;

        }


        return acceptedValues.contains(value.toString());
    }
}