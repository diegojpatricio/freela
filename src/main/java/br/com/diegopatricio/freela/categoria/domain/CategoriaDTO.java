package br.com.diegopatricio.freela.categoria.domain;

import br.com.diegopatricio.freela.categoria.domain.Categoria;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;


@Getter
@Setter
public class CategoriaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer idCategoria;
    @NotEmpty(message = "Preenchimento Obrigatório")
    @Length(min=5, max=80, message="O Tamando deve ser entre 5  e 80 caracteres.")
    private String nomeCategoria;

    public CategoriaDTO(){}

    public CategoriaDTO(Categoria categoria) {
        idCategoria = categoria.getIdCategoria();
        nomeCategoria = categoria.getNomeCategoria();
    }
}
