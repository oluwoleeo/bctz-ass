package com.wole;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TokenTests {
    static InputValidator inputValidator;

    @BeforeAll
    static void setUp(){
        inputValidator = new InputValidator(
                "wolexy",
                "waze@abc.com",
                "Akashi@007",
                "11/09/2001");
    }

    @Test
    @DisplayName("Verify valid token")
    void testValidToken(){
        final String token = inputValidator.generateToken();

        String expected = "Verification passed";
        String actual = inputValidator.verifyToken(token);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Verify invalid token")
    void testInvalidToken(){
        final String token = "inputValidator.generateToken()";

        String expected = "Verification failed";
        String actual = inputValidator.verifyToken(token);

        Assertions.assertEquals(expected, actual);
    }
}
