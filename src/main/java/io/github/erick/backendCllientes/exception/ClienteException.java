package io.github.erick.backendCllientes.exception;

public class ClienteException extends  RuntimeException {
    public ClienteException(String msg, String texto){
        super( msg  + texto);
    }
}
