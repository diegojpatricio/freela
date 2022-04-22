package br.com.diegopatricio.freela.ordemservico.services;

import br.com.diegopatricio.freela.categoria.domain.Categoria;
import br.com.diegopatricio.freela.categoria.repositories.CategoriaRepository;
import br.com.diegopatricio.freela.exceptions.ObjectNotFoundException;
import br.com.diegopatricio.freela.ordemservico.domain.OrdemServico;
import br.com.diegopatricio.freela.ordemservico.repositories.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrdemServicoService {

    @Autowired
    private OrdemServicoRepository repo;

    public OrdemServico buscarOrdemServico(Integer id){
        Optional<OrdemServico> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrato! ID: " + id +
                ", Tipo: " + OrdemServico.class.getName()));
    }
}
