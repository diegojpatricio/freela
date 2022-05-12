package br.com.diegopatricio.freela.exceptions;

public class ExceptionDataIntegrityViolation extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ExceptionDataIntegrityViolation(String message) {
        super(message);
    }

    public ExceptionDataIntegrityViolation(String message, Throwable cause) {
        super(message, cause);
    }
}
