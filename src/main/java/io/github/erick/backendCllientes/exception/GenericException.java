package io.github.erick.backendCllientes.exception;

public class GenericException extends RuntimeException  {
    public GenericException(String msg, String texto) {
        super(msg + texto);
    }
}
