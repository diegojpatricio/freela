package br.com.diegopatricio.freela.exceptions;

public class ConstraintViolationException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ConstraintViolationException(String message) {
        super(message);
    }

    public ConstraintViolationException(String message, Throwable cause) {
        super(message, cause);
    }
}
