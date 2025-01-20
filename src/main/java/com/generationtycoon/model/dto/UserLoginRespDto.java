package com.generationtycoon.model.dto;

/**
 * Dto per la risposta a un login.
 *
 * @param token il token dell'user <em>loggato</em>.
 */
public record UserLoginRespDto(String token) {
}
