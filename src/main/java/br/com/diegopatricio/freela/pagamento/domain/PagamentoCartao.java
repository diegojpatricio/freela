package br.com.diegopatricio.freela.pagamento.domain;

import br.com.diegopatricio.freela.ordemservico.domain.OrdemServico;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class PagamentoCartao extends Pagamento{
    private static final long serialVersionUID = 1L;

    @Column(name = "NUMERO_PARCELAS")
    private Integer numeroParcelas;

    public PagamentoCartao(){}

    public PagamentoCartao(Integer idPagamento, StatusPagamento statusPagmento, OrdemServico ordemServico, Integer numeroParcelas) {
        super(idPagamento, statusPagmento, ordemServico);
        this.numeroParcelas = numeroParcelas;
    }

    public PagamentoCartao(Integer numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }
}
