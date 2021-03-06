package br.com.diegopatricio.freela.endereco.domain;

import br.com.diegopatricio.freela.cidade.domain.Cidade;
import br.com.diegopatricio.freela.cliente.domain.Cliente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name="ENDERECOS")
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ENDERECO")
    private Integer idEndereco;
    @Column(name = "LOGRADOURO")
    private String logradouro;
    @Column(name = "NUMERO")
    private String numero;
    @Column(name = "COMPLEMENTO")
    private String complemento;
    @Column(name = "BAIRRO")
    private String bairro;
    @Column(name = "CEP")
    private String cep;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "ID_CIDADE")
    private Cidade cidade;


    public Endereco() {
    }

    public Endereco(Integer idEndereco, String logradouro, String numero, String complemento, String bairro, String cep,
                    Cliente cliente, Cidade cidade) {
        super();
        this.idEndereco = idEndereco;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
        this.cliente = cliente;
        this.setCidade(cidade);
    }
}
