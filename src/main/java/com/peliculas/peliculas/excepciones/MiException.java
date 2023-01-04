package com.peliculas.peliculas.excepciones;

import java.io.IOException;

public class MiException extends Exception {

    public MiException(String mensaje) {
        super(mensaje);
    }

    public MiException(String string, IOException e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
