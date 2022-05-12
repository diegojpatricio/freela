package br.com.diegopatricio.freela.cidade.domain;

import br.com.diegopatricio.freela.estado.domain.Estado;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name="CIDADES")
public class Cidade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CIDADE")
    private Integer idCidade;
    @Column(name = "NOME")
    private String nome;
    @ManyToOne
    @JoinColumn(name = "ID_ESTADO")
    private Estado estado;

    public Cidade() {
    }

    public Cidade(Integer idCidade, String nome, Estado estado) {
        super();
        this.idCidade = idCidade;
        this.nome = nome;
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Cidade cidade = (Cidade) o;
        return idCidade != null && Objects.equals(idCidade, cidade.idCidade);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
