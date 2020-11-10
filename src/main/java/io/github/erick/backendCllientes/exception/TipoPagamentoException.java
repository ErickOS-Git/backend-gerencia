package io.github.erick.backendCllientes.exception;

public class TipoPagamentoException extends RuntimeException {
    public TipoPagamentoException(String msg, String error){
        super(msg + error);
    }
}
