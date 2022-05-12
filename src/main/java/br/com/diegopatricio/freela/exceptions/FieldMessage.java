package br.com.diegopatricio.freela.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class FieldMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private String campo;
    private String messagem;

    public FieldMessage(){}

    public FieldMessage(String campo, String messagem) {
        this.campo = campo;
        this.messagem = messagem;
    }
}
