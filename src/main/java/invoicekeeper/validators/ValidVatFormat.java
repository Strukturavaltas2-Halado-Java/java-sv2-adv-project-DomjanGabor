package invoicekeeper.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = VatValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidVatFormat {
    String message() default "VAT is in incorrect format.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String vatFormat() default "^[0-9]{8}-[0-9]{1}-[0-9]{2}$";
}
