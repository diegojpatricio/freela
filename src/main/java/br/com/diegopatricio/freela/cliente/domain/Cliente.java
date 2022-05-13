package br.com.diegopatricio.freela.cliente.domain;

import br.com.diegopatricio.freela.endereco.domain.Endereco;
import br.com.diegopatricio.freela.ordemservico.domain.OrdemServico;
import br.com.diegopatricio.freela.perfil.Perfil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name="CLIENTES")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CLIENTE")
    private Integer idCliente;
    @Column(name = "NOME")
    private String nome;
    @Column(name = "EMAIL", unique = true)
    private String email;
    @Column(name = "CPF_CNPJ")
    private String cpfCnpj;
    @Column(name = "TIPO_CLIENTE")
    private Integer tipoCliente;
    @ElementCollection
    @CollectionTable(name = "TELEFONE")
    private Set<String> telefones = new HashSet<>();
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Endereco> enderecos = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<OrdemServico> ordemServicos =new ArrayList<>();
    @JsonIgnore
    private String senha;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PERFIS")
    private Set<Integer> perfis = new HashSet<>();

    public Cliente() {
        addPerfil(Perfil.CLIENTE);
    }

    public Cliente(Integer idCliente, String nome, String email, String cpfCnpj, TipoCliente tipoCliente, String senha) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.email = email;
        this.cpfCnpj = cpfCnpj;
        this.tipoCliente = (tipoCliente == null) ? null : tipoCliente.getCod();
        this.senha = senha;
        addPerfil(Perfil.CLIENTE);
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public TipoCliente getTipoCliente() {
        return TipoCliente.toEnum(tipoCliente);
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente.getCod();
    }

    public Set<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<String> telefones) {
        this.telefones = telefones;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public List<OrdemServico> getOrdemServicos() {
        return ordemServicos;
    }

    public void setOrdemServicos(List<OrdemServico> ordemServicos) {
        this.ordemServicos = ordemServicos;
    }

    public Set<Perfil> getPerfis(){
        return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfil(Perfil perfil){
        perfis.add(perfil.getCod());
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Cliente cliente = (Cliente) o;
        return idCliente != null && Objects.equals(idCliente, cliente.idCliente);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
