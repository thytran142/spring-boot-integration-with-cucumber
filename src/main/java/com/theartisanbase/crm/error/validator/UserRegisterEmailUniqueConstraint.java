/*
 * Created By Vanessa Tran at 2020 12 15
 */

package com.theartisanbase.crm.error.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserRegisterEmailUniqueValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UserRegisterEmailUniqueConstraint {
    String message() default "This email is registered. Please login instead.";
    Class<?>[] groups() default  {};
    Class<? extends Payload>[] payload() default {};
}
