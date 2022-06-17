package invoicekeeper.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BankAccountValidator implements ConstraintValidator<ValidAccountNumber, String> {
    private String accountFormat;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.matches(accountFormat);
    }

    @Override
    public void initialize(ValidAccountNumber constraintAnnotation) {
        accountFormat = constraintAnnotation.accountFormat();
    }
}
