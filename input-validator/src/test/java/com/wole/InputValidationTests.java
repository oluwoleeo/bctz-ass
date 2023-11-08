package com.wole;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class InputValidationTests {
    static InputValidator inputValidator;

    @BeforeAll
    static void setUp(){
        inputValidator = new InputValidator();
    }

    @Test
    @DisplayName("Valid username")
    void testValidUsername(){
        String username = "wole";
        int validationExceptionsBeforeSize = inputValidator.getValidationExceptions().size();

        boolean expected = true;
        boolean actual = inputValidator.isValidUsername(username);
        int validationExceptionsAfterSize = inputValidator.getValidationExceptions().size();

        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(validationExceptionsBeforeSize, validationExceptionsAfterSize);
    }

    @Test
    @DisplayName("Username length")
    void testUsernameLength(){
        String username = "dog";
        int validationExceptionsBeforeSize = inputValidator.getValidationExceptions().size();

        boolean expected = false;
        boolean actual = inputValidator.isValidUsername(username);

        int validationExceptionsAfterSize = inputValidator.getValidationExceptions().size();

        Assertions.assertEquals(expected, actual );
        Assertions.assertEquals(validationExceptionsBeforeSize + 1, validationExceptionsAfterSize );
        ValidationException ex = inputValidator.getValidationExceptions().get(validationExceptionsAfterSize - 1);
        Assertions.assertEquals("Username ", ex.getField());
        Assertions.assertEquals("below 4 digits", ex.getDescription());
    }

    @Test
    @DisplayName("Blank username")
    void testBlankUsername(){
        String username = "         ";
        int validationExceptionsBeforeSize = inputValidator.getValidationExceptions().size();

        boolean expected = false;
        boolean actual = inputValidator.isValidUsername(username);

        int validationExceptionsAfterSize = inputValidator.getValidationExceptions().size();

        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(validationExceptionsBeforeSize + 1, validationExceptionsAfterSize );
        ValidationException ex = inputValidator.getValidationExceptions().get(validationExceptionsAfterSize - 1);
        Assertions.assertEquals("Username ", ex.getField());
        Assertions.assertEquals("is blank", ex.getDescription());
    }

    @Test
    @DisplayName("Valid email")
    void testValidEmail(){
        String email = "oluwaremi.oluwole@gmail.com";
        int validationExceptionsBeforeSize = inputValidator.getValidationExceptions().size();

        boolean expected = true;
        boolean actual = inputValidator.isValidEmail(email);

        int validationExceptionsAfterSize = inputValidator.getValidationExceptions().size();

        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(validationExceptionsBeforeSize, validationExceptionsAfterSize);
    }

    @Test
    @DisplayName("Blank email")
    void testBlankEmail(){
        String email = "   ";
        int validationExceptionsBeforeSize = inputValidator.getValidationExceptions().size();

        boolean expected = false;
        boolean actual = inputValidator.isValidEmail(email);

        int validationExceptionsAfterSize = inputValidator.getValidationExceptions().size();

        Assertions.assertEquals(expected, actual );
        Assertions.assertEquals(validationExceptionsBeforeSize + 1, validationExceptionsAfterSize );
        ValidationException ex = inputValidator.getValidationExceptions().get(validationExceptionsAfterSize - 1);
        Assertions.assertEquals("Email ", ex.getField());
        Assertions.assertEquals("is blank", ex.getDescription());
    }

    @Test
    @DisplayName("Email without @")
    void testEmailWithoutAt(){
        String email = "oluwaremi.oluwolegmail.com";
        int validationExceptionsBeforeSize = inputValidator.getValidationExceptions().size();

        boolean expected = false;
        boolean actual = inputValidator.isValidEmail(email);

        int validationExceptionsAfterSize = inputValidator.getValidationExceptions().size();

        Assertions.assertEquals(expected, actual );
        Assertions.assertEquals(validationExceptionsBeforeSize + 1, validationExceptionsAfterSize );
        ValidationException ex = inputValidator.getValidationExceptions().get(validationExceptionsAfterSize - 1);
        Assertions.assertEquals("Email ", ex.getField());
        Assertions.assertEquals("not valid", ex.getDescription());
    }

    @Test
    @DisplayName("Email without .")
    void testEmailWithoutDot(){
        String email = "oluwaremi.oluwole@gmailcom";

        int validationExceptionsBeforeSize = inputValidator.getValidationExceptions().size();

        boolean expected = false;
        boolean actual = inputValidator.isValidEmail(email);

        int validationExceptionsAfterSize = inputValidator.getValidationExceptions().size();

        Assertions.assertEquals(expected, actual );
        Assertions.assertEquals(validationExceptionsBeforeSize + 1, validationExceptionsAfterSize );
        ValidationException ex = inputValidator.getValidationExceptions().get(validationExceptionsAfterSize - 1);
        Assertions.assertEquals("Email ", ex.getField());
        Assertions.assertEquals("not valid", ex.getDescription());
    }

    @Test
    @DisplayName("Valid password")
    void testValidPassword(){
        String password = "Password@123";

        int validationExceptionsBeforeSize = inputValidator.getValidationExceptions().size();

        boolean expected = true;
        boolean actual = inputValidator.isValidPassword(password);

        int validationExceptionsAfterSize = inputValidator.getValidationExceptions().size();

        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(validationExceptionsBeforeSize, validationExceptionsAfterSize);
    }

    @Test
    @DisplayName("Blank password")
    void testBlankPassword(){
        String password = "   ";
        int validationExceptionsBeforeSize = inputValidator.getValidationExceptions().size();

        boolean expected = false;
        boolean actual = inputValidator.isValidPassword(password);

        int validationExceptionsAfterSize = inputValidator.getValidationExceptions().size();

        Assertions.assertEquals(expected, actual );
        Assertions.assertEquals(validationExceptionsBeforeSize + 1, validationExceptionsAfterSize );
        ValidationException ex = inputValidator.getValidationExceptions().get(validationExceptionsAfterSize - 1);
        Assertions.assertEquals("Password ", ex.getField());
        Assertions.assertEquals("is blank", ex.getDescription());
    }

    @Test
    @DisplayName("Password without uppercase")
    void testPasswordWithoutUppercase(){
        String password = "password@123";

        int validationExceptionsBeforeSize = inputValidator.getValidationExceptions().size();

        boolean expected = false;
        boolean actual = inputValidator.isValidPassword(password);

        int validationExceptionsAfterSize = inputValidator.getValidationExceptions().size();

        Assertions.assertEquals(expected, actual );
        Assertions.assertEquals(validationExceptionsBeforeSize + 1, validationExceptionsAfterSize );
        ValidationException ex = inputValidator.getValidationExceptions().get(validationExceptionsAfterSize - 1);
        Assertions.assertEquals("Password ", ex.getField());
        Assertions.assertEquals("not strong enough", ex.getDescription());
    }

    @Test
    @DisplayName("Password without digit")
    void testPasswordWithoutDigit(){
        String password = "Password@";

        int validationExceptionsBeforeSize = inputValidator.getValidationExceptions().size();

        boolean expected = false;
        boolean actual = inputValidator.isValidPassword(password);

        int validationExceptionsAfterSize = inputValidator.getValidationExceptions().size();

        Assertions.assertEquals(expected, actual );
        Assertions.assertEquals(validationExceptionsBeforeSize + 1, validationExceptionsAfterSize );
        ValidationException ex = inputValidator.getValidationExceptions().get(validationExceptionsAfterSize - 1);
        Assertions.assertEquals("Password ", ex.getField());
        Assertions.assertEquals("not strong enough", ex.getDescription());
    }

    @Test
    @DisplayName("Password without special character")
    void testPasswordWithoutSpecialCharacter(){
        String password = "Password123";

        int validationExceptionsBeforeSize = inputValidator.getValidationExceptions().size();

        boolean expected = false;
        boolean actual = inputValidator.isValidPassword(password);

        int validationExceptionsAfterSize = inputValidator.getValidationExceptions().size();

        Assertions.assertEquals(expected, actual );
        Assertions.assertEquals(validationExceptionsBeforeSize + 1, validationExceptionsAfterSize );
        ValidationException ex = inputValidator.getValidationExceptions().get(validationExceptionsAfterSize - 1);
        Assertions.assertEquals("Password ", ex.getField());
        Assertions.assertEquals("not strong enough", ex.getDescription());
    }

    @Test
    @DisplayName("Valid age")
    void testValidAge(){
        String date = "03/05/1998";

        int validationExceptionsBeforeSize = inputValidator.getValidationExceptions().size();

        boolean expected = true;
        boolean actual = inputValidator.isValidDate(date);

        int validationExceptionsAfterSize = inputValidator.getValidationExceptions().size();

        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(validationExceptionsBeforeSize, validationExceptionsAfterSize);
    }

    @Test
    @DisplayName("Blank date")
    void testBlankDate(){
        String date = "   ";
        int validationExceptionsBeforeSize = inputValidator.getValidationExceptions().size();

        boolean expected = false;
        boolean actual = inputValidator.isValidDate(date);

        int validationExceptionsAfterSize = inputValidator.getValidationExceptions().size();

        Assertions.assertEquals(expected, actual );
        Assertions.assertEquals(validationExceptionsBeforeSize + 1, validationExceptionsAfterSize );
        ValidationException ex = inputValidator.getValidationExceptions().get(validationExceptionsAfterSize - 1);
        Assertions.assertEquals("Date of birth ", ex.getField());
        Assertions.assertEquals("is blank", ex.getDescription());
    }

    @Test
    @DisplayName("Age below 16 years")
    void testAgeBelow16Years(){
        String date = "11/11/2011";

        int validationExceptionsBeforeSize = inputValidator.getValidationExceptions().size();

        boolean expected = false;
        boolean actual = inputValidator.isValidDate(date);

        int validationExceptionsAfterSize = inputValidator.getValidationExceptions().size();

        Assertions.assertEquals(expected, actual );
        Assertions.assertEquals(validationExceptionsBeforeSize + 1, validationExceptionsAfterSize );
        ValidationException ex = inputValidator.getValidationExceptions().get(validationExceptionsAfterSize - 1);
        Assertions.assertEquals("Date of birth ", ex.getField());
        Assertions.assertEquals("less than 16 years", ex.getDescription());
    }

    @Test
    @DisplayName("Age is future date")
    void testDateIsFutureDate(){
        String date = "30/11/2023";

        int validationExceptionsBeforeSize = inputValidator.getValidationExceptions().size();

        boolean expected = false;
        boolean actual = inputValidator.isValidDate(date);

        int validationExceptionsAfterSize = inputValidator.getValidationExceptions().size();

        Assertions.assertEquals(expected, actual );
        Assertions.assertEquals(validationExceptionsBeforeSize + 1, validationExceptionsAfterSize );
        ValidationException ex = inputValidator.getValidationExceptions().get(validationExceptionsAfterSize - 1);
        Assertions.assertEquals("Date of birth ", ex.getField());
        Assertions.assertEquals("is a future date", ex.getDescription());
    }

    @Test
    @DisplayName("Unparseable date")
    void testUnparseableDate(){
        String date = "Wole";

        // Assertions.assertThrows(DateTimeParseException.class, () -> inputValidator.isValidDate(password) );

        int validationExceptionsBeforeSize = inputValidator.getValidationExceptions().size();

        boolean expected = false;
        boolean actual = inputValidator.isValidDate(date);

        int validationExceptionsAfterSize = inputValidator.getValidationExceptions().size();

        Assertions.assertEquals(expected, actual );
        Assertions.assertEquals(validationExceptionsBeforeSize + 1, validationExceptionsAfterSize );
        ValidationException ex = inputValidator.getValidationExceptions().get(validationExceptionsAfterSize - 1);
        Assertions.assertEquals("Date of birth ", ex.getField());
        Assertions.assertEquals("not valid", ex.getDescription());
    }

}
