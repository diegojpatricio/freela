package br.com.diegopatricio.freela.ordemservico.domain;

import br.com.diegopatricio.freela.cliente.domain.Cliente;
import br.com.diegopatricio.freela.pagamento.domain.Pagamento;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class OrdemServicoDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    private Integer idOrdemServico;
    private Date dataSolicitacao;
    private Double valorOrdemServico;
    private Pagamento pagamento;
    private Cliente cliente;


    public OrdemServicoDTO() {
    }

    public OrdemServicoDTO(OrdemServico ordemServico) {
        idOrdemServico = ordemServico.getIdOrdemServico();
        dataSolicitacao = ordemServico.getDataSolicitacao();
        valorOrdemServico = ordemServico.getValorOrdemServico();
        pagamento = ordemServico.getPagamento();
        cliente = ordemServico.getCliente();
    }
}
