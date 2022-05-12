package br.com.diegopatricio.freela.servico.domain;

import br.com.diegopatricio.freela.categoria.domain.Categoria;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.w3c.dom.stylesheets.LinkStyle;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ServicoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer idServico;
    private String nome;
    private Double valor;
    private List<Categoria> categorias;

    public ServicoDTO() {
    }

    public ServicoDTO(Servico servico) {
        idServico = servico.getIdServico();
        nome = servico.getNome();
        valor = servico.getValor();
        categorias = servico.getCategorias();
    }
}
