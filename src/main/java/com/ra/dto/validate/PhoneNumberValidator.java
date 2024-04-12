package com.ra.dto.validate;

import com.ra.model.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.util.List;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumberConstraint,String> {
    @Autowired
    private UserDAO userDAO;
    @Override
    public void initialize(PhoneNumberConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.matches("(84|0[3|5|7|8|9])+([0-9]{8})\\b");
    }


}
