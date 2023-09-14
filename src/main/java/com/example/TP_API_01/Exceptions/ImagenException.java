package com.example.TP_API_01.Exceptions;

public class ImagenException extends Exception {

    private static final long serialVersionUID = 6332415080946078382L;

    public ImagenException(String mensaje) {
        super(mensaje);
    }
}