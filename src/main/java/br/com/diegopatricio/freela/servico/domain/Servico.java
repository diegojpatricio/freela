package br.com.diegopatricio.freela.servico.domain;

import br.com.diegopatricio.freela.categoria.domain.Categoria;
import br.com.diegopatricio.freela.ordemservico.domain.OrdemServico;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name="SERVICOS")
public class Servico implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SERVICO")
    private Integer idServico;
    @Column(name = "NOME")
    private String nomeServico;
    @Column(name = "VALOR")
    private Double valorServico;
   @JsonIgnore
   @ManyToMany
    @JoinTable(name = "SERVICO_CATEGORIA",
        joinColumns = @JoinColumn(name = "ID_SERVICO"),
        inverseJoinColumns = @JoinColumn(name = "ID_CATEGORIA"))
    private List<Categoria> categorias = new ArrayList<>();
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "SERVICO_OS",
            joinColumns = @JoinColumn(name = "ID_SERVICO"),
            inverseJoinColumns = @JoinColumn(name = "ID_OS"))
    private List<OrdemServico> ordemServicos = new ArrayList<>();

    public Servico() {
    }

    public Servico(Integer idServico, String nomeServico, Double valorServico, List<Categoria> categorias) {
        this.idServico = idServico;
        this.nomeServico = nomeServico;
        this.valorServico = valorServico;
        this.categorias = categorias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Servico servico = (Servico) o;
        return idServico != null && Objects.equals(idServico, servico.idServico);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "idServico = " + idServico + ", " +
                "nomeServico = " + nomeServico + ", " +
                "valorServico = " + valorServico + ")";
    }
}
