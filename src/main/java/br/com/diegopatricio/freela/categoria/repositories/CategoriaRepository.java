package br.com.diegopatricio.freela.categoria.repositories;

import br.com.diegopatricio.freela.categoria.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {


    List<Categoria> findByServicos_IdServico(Integer idServico);
}
