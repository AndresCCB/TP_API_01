package com.example.TP_API_01.Exceptions;

public class PersonaException extends Exception {

    private static final long serialVersionUID = -2835873129858130160L;

    public PersonaException(String mensaje) {
        super(mensaje);
    }
}
