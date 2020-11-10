package io.github.erick.backendCllientes.exception;

public class FornecedorException extends RuntimeException {
    public FornecedorException(String msg, String texto) {
        super(msg + texto);
    }

}