package br.com.diegopatricio.freela.ordemservico.domain;

import br.com.diegopatricio.freela.pagamento.domain.Pagamento;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class OrdemServicoDTOPut implements Serializable {
    private static final long serialVersionUID = 1L;

    private Pagamento pagamento;


    public OrdemServicoDTOPut() {
    }

    public OrdemServicoDTOPut(Pagamento pagamento, Pagamento tipoPagamento) {
        this.pagamento = pagamento;
    }
}
