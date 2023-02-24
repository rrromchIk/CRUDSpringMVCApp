package com.rom4ik.validator;

import com.rom4ik.dao.TestDAO;
import com.rom4ik.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author rom4ik
 */
@Component
public class TestValidator implements Validator {
    private final TestDAO testDAO;

    @Autowired
    public TestValidator(TestDAO testDAO) {
        this.testDAO = testDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Test.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Test test = (Test) o;

        if(testDAO.getByName(test.getName()) != null)
            errors.rejectValue("name", "", "Name is already taken");

    }
}
