package io.github.erick.backendCllientes.exception;

public class ProdutoException extends RuntimeException {
    public ProdutoException(String msg, String texto) {
        super(msg + texto);
    }
}
