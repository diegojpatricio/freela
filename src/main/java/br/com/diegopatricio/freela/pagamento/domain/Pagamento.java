package br.com.diegopatricio.freela.pagamento.domain;

import br.com.diegopatricio.freela.ordemservico.domain.OrdemServico;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name="PAGAMENTOS")
public abstract class Pagamento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer idPagamento;
    @Column(name = "STATUS_PAGAMENTO")
    private Integer statusPagamento;
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "ID_ORDEMSERVICO")
    @MapsId
    private OrdemServico ordemServico;

    public Pagamento(Integer idPagamento, StatusPagamento statusPagmento, OrdemServico ordemServico) {
        super();
        this.idPagamento = idPagamento;
        this.statusPagamento = statusPagmento.getCod();
        this.ordemServico = ordemServico;
    }

    public Pagamento() {

    }

    public Integer getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(Integer idPagamento) {
        this.idPagamento = idPagamento;
    }

    public StatusPagamento getStatusPagamento() {
        return StatusPagamento.toEnum(statusPagamento);
    }

    public void setStatusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = statusPagamento.getCod();
    }

    public OrdemServico getOrdemServico() {
        return ordemServico;
    }

    public void setOrdemServico(OrdemServico ordemServico) {
        this.ordemServico = ordemServico;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Pagamento pagamento = (Pagamento) o;
        return idPagamento != null && Objects.equals(idPagamento, pagamento.idPagamento);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
