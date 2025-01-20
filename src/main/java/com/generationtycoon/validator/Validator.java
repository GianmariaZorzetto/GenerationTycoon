package com.generationtycoon.validator;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;


/**
 * Validator è un enum che contiene due singleton per la validazione di due stringhe, uno per le passwords e uno per le email.
 */
public enum Validator {
    /**
     * Oggetto per validare le passwords.
     * La password deve contenere una <strong>maiuscola</strong>, una <strong>minuscola</strong>, un <strong>numero</strong> e un <strong>carattere speciale</strong>.
     * La password deve essere lunga <em>almeno</em> <strong>8</strong> caratteri.
     */
    PASSWORD("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[?!@$ %^&*-]).{8,}$"),
    /**
     * Oggetto per validare le email.
     * La email deve essere del tipo: {@code name@domain.com}
     */
    EMAIL("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");

    /**
     * Oggetto per il controllo delle stringhe in ingresso.
     */
    private final Pattern pattern;

    /**
     * Costruttore dei singleton.
     * Costruisce un {@link Pattern} a partire da una {@link String}.
     *
     * @param regex la regex in ingresso.
     * @throws PatternSyntaxException se {@code regex} in ingresso non è valida.
     */
    Validator(String regex) throws PatternSyntaxException {
        this.pattern = Pattern.compile(regex);
    }

    /**
     * Metodo per validare una {@link String} in input.
     *
     * @param value la {@link String} da validare.
     * @return {@code true} se {@code value} aderisce al pattern, {@code false} altrimenti.
     */
    public boolean validate(String value) {
        return pattern.matcher(value).matches();
    }
}
