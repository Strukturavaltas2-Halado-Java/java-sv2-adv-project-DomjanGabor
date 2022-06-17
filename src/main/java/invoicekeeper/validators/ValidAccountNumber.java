package invoicekeeper.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = BankAccountValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidAccountNumber {
    String message() default "Bank account number is in incorrect format.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String accountFormat() default "^[0-9]{8}-[0-9]{8}-[0-9]{8}$";
}
