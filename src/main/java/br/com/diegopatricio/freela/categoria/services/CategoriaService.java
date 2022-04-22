package br.com.diegopatricio.freela.categoria.services;

import br.com.diegopatricio.freela.categoria.domain.Categoria;
import br.com.diegopatricio.freela.categoria.repositories.CategoriaRepository;
import br.com.diegopatricio.freela.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository repo;
    public Categoria buscarCategoria(Integer id){
        Optional<Categoria> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrato! ID: " + id +
                ", Tipo: " + Categoria.class.getName()));
    }
}
