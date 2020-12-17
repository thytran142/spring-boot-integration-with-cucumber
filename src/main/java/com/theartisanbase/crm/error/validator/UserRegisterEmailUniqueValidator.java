/*
 * Created By Vanessa Tran at 2020 12 15
 */

package com.theartisanbase.crm.error.validator;

import com.theartisanbase.crm.domain.User;
import com.theartisanbase.crm.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserRegisterEmailUniqueValidator implements ConstraintValidator<UserRegisterEmailUniqueConstraint, String> {
    @Autowired
    private UserRepository userRepository;
    public UserRegisterEmailUniqueValidator() {}
    public UserRegisterEmailUniqueValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        User user = this.userRepository.findByEmail(value).orElse(null);
        return user == null;
    }
}
