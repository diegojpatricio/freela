package br.com.diegopatricio.freela.cliente.domain;

import br.com.diegopatricio.freela.cliente.services.validations.ClienteInsert;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
@ClienteInsert
public class ClienteResourceDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Preenchimento Obrigatório!")
    @Length(min = 5,max = 120, message = "O tambanho deve ser entre 5 e 120 caracteres")
    private String nome;
    @NotEmpty(message = "Preenchimento Obrigatório!")
    @Email(message = "Email Inválido!")
    private String email;
    @NotEmpty(message = "Preenchimento Obrigatório!")
    private String cpfCnpj;
    private Integer tipoCliente;

    @NotEmpty(message = "Preenchimento Obrigatório!")
    private String logradouro;
    @NotEmpty(message = "Preenchimento Obrigatório!")
    private String numero;
    private String complemento;
    @NotEmpty(message = "Preenchimento Obrigatório!")
    private String bairro;
    @NotEmpty(message = "Preenchimento Obrigatório!")
    private String cep;

    @NotEmpty(message = "Preenchimento Obrigatório!")
    private String telefone1;
    private String telefone2;

    private Integer cidadeId;

    public ClienteResourceDTO(){

    }
}
