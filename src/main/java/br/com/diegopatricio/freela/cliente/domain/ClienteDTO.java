package br.com.diegopatricio.freela.cliente.domain;

import br.com.diegopatricio.freela.cliente.services.validations.ClienteUpdate;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
@ClienteUpdate
public class ClienteDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer idCliente;
    @NotEmpty(message = "Preenchimento Obrigatório!")
    @Length(min = 5,max = 120, message = "O tambanho deve ser entre 5 e 120 caracteres")
    private String nome;
    @NotEmpty(message = "Preenchimento Obrigatório!")
    @Email(message = "Email Inválido!")
    private String email;

    public ClienteDTO(){}

    public ClienteDTO(Cliente cliente){
        idCliente = cliente.getIdCliente();
        nome = cliente.getNome();
        email = cliente.getEmail();
    }
}
