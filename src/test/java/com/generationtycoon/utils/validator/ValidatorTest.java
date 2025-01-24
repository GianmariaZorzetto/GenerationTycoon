package com.generationtycoon.utils.validator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidatorTest {
    @ParameterizedTest
    @ValueSource(strings = {"john.doe@example.com",
            "jane_smith123@gmail.com",
            "user.name@domain.co",
            "first.last@sub.domain.com",
            "contact@company.org",
            "info@website.net",
            "example123@domain.com",
            "test.email+filter@example.com",
            "admin@my-site.com",
            "user@domain.c",
            "support@service.io"})
    void acceptEmail(String email) {
        assertTrue(Validator.EMAIL.validate(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {"john.doe@.com",
            "jane_smith@.gmail.com",
            "user.name@domain..com",
            "first.last@domain",
            "contact@company,org",
            "info@website@net",
            "example123@domain..com",
            "test.email@.example.com",
            "admin@my-site..com",
            "support@service..io",
            "@missingusername.com",
            "username@.com",
            "username@domain..com",
            "user@domain..co.uk"})
    void invalidEmail(String email) {
        assertFalse(Validator.EMAIL.validate(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {"9$gT!3kZ@7mQ^1x",
            "R4#jP8&vL2!nW6z%",
            "H1^tY9!bF5@kQ3*e",
            "7!xN2^mP@8zR#4jL",
            "W3$kF1!nT7^qJ9&v",
            "5@hL8#zR2!mQ^6tY",
            "J9^kT4!bN1@wX7#p",
            "2!vF6^jH3@kQ9&zL",
            "8$gR1!mT^5nP@2#j",
            "K7^tY3!bF9@jL4&x"})
    void acceptPassword(String password) {
        assertTrue(Validator.PASSWORD.validate(password));
    }

    @ParameterizedTest
    @ValueSource(strings = {"password123",
            "12345678",
            "qwerty",
            "letmein",
            "abcdef",
            "111111",
            "welcome",
            "12345",
            "monkey",
            "sunshine",
            "123456789",
            "iloveyou",
            "admin",
            "1234567",
            "password1"})
    void refusePasswords(String password) {
        assertFalse(Validator.PASSWORD.validate(password));
    }
}