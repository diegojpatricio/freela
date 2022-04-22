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
public class PagamentoPix extends Pagamento{
    private static final long serialVersionUID = 1L;

    @Column(name = "CHAVE_PIX")
    private String chavePix;

    public PagamentoPix(){}

    public PagamentoPix(Integer idPagamento, StatusPagamento statusPagmento, OrdemServico ordemServico, String chavePix) {
        super(idPagamento, statusPagmento, ordemServico);
        this.chavePix = chavePix;
    }

    public PagamentoPix(String chavePix) {
        this.chavePix = chavePix;
    }
}
