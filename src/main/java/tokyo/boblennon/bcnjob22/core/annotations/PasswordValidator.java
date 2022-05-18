package tokyo.boblennon.bcnjob22.core.annotations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordConstraint, String> {

    // REGEX pattern for 'password' field
    // Must have at least 1 number, 1 uppercase character and be 8 characters long
    private static final String PASS_REGEXP = "^(?=.*[0-9])(?=.*[A-Z]).{8,}$";

    private static final Pattern pattern = Pattern.compile(PASS_REGEXP);

    @Override
    public void initialize(PasswordConstraint password) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

}
