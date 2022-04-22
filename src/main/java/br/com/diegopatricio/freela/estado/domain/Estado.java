package br.com.diegopatricio.freela.estado.domain;


import br.com.diegopatricio.freela.cidade.domain.Cidade;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name="ESTADOS")
public class Estado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ESTADO")
    private Integer idEstado;
    @Column(name = "NOME")
    private String nome;
    @JsonIgnore
    @OneToMany(mappedBy = "estado")
    private List<Cidade> cidades = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Estado estado = (Estado) o;
        return idEstado != null && Objects.equals(idEstado, estado.idEstado);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
