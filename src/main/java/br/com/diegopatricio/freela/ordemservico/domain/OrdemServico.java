package br.com.diegopatricio.freela.ordemservico.domain;

import br.com.diegopatricio.freela.cliente.domain.Cliente;
import br.com.diegopatricio.freela.pagamento.domain.Pagamento;
import br.com.diegopatricio.freela.servico.domain.Servico;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="ORDEMSERVICOS")
@NoArgsConstructor
public class OrdemServico implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOrdemServico;
    @Column(name = "DATA_SOLICITACAO")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dataSolicitacao;
    @Column(name = "VALOR_ORDEMSERVICO")
    private Double valorOrdemServico;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "ordemServico")
    private Pagamento pagamento;
    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE")
    private Cliente cliente;
    @ManyToMany(mappedBy = "ordemServicos")
    private Set<Servico> servicos =  new HashSet<>();

    public OrdemServico(Integer idOrdemServico, Date dataSolicitacao, Cliente cliente, Double valorOrdemServico) {
        super();
        this.idOrdemServico = idOrdemServico;
        this.dataSolicitacao = dataSolicitacao;
        this.cliente = cliente;
        this.valorOrdemServico = valorOrdemServico;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OrdemServico that = (OrdemServico) o;
        return idOrdemServico != null && Objects.equals(idOrdemServico, that.idOrdemServico);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
