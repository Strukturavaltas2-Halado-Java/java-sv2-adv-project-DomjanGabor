package invoicekeeper.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VatValidator implements ConstraintValidator<ValidVatFormat, String> {
    private String vatFormat;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.matches(vatFormat);
    }

    @Override
    public void initialize(ValidVatFormat constraintAnnotation) {
        vatFormat = constraintAnnotation.vatFormat();
    }
}
