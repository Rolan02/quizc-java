package org.fundacionjala.app.quizz.model.validator;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class ValidatorIT {

    @Test
    public void testDateValidator() {
        List<String> errors = new ArrayList<>();
        Validator dateValidator = ValidatorType.DATE.getValidator();

        dateValidator.validate("24/12/2021", null, errors);

        Assert.assertEquals(DateValidator.class, dateValidator.getClass());
        Assert.assertTrue(errors.isEmpty());
    }

    @Test
    public void testMaxLengthValidator() {
        List<String> errors = new ArrayList<>();
        Validator maxLengthValidator = ValidatorType.MAX_LENGTH.getValidator();

        maxLengthValidator.validate("HOLA MUNDO", "10", errors);

        Assert.assertEquals(MaxLengthValidator.class, maxLengthValidator.getClass());
        Assert.assertTrue(errors.isEmpty());
    }

    @Test
    public void testMaxLengthValidatorWhitError() {
        List<String> errors = new ArrayList<>();
        Validator maxLengthValidator = ValidatorType.MAX_LENGTH.getValidator();

        maxLengthValidator.validate("HOLA MUNDO", "1", errors);
        Assert.assertTrue(!errors.isEmpty());
    }

    @Test
    public void testOnlyUpperCaseValidator() {
        List<String> errors = new ArrayList<>();
        Validator onlyUpperCaseValidator = ValidatorType.ONLYUPPERCASE.getValidator();

        onlyUpperCaseValidator.validate("HOLA", null, errors);

        Assert.assertEquals(OnlyUpperCaseTextValidator.class, onlyUpperCaseValidator.getClass());
        Assert.assertTrue(errors.isEmpty());
    }

    @Test
    public void testOnlyUpperCaseValidatorWhitError() {
        List<String> errors = new ArrayList<>();
        Validator onlyUpperCaseValidator = ValidatorType.ONLYUPPERCASE.getValidator();

        onlyUpperCaseValidator.validate("hola", null, errors);

        Assert.assertEquals(OnlyUpperCaseTextValidator.class, onlyUpperCaseValidator.getClass());
        Assert.assertTrue(!errors.isEmpty());
    }
}
