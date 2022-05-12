package br.com.diegopatricio.freela.servico.repositories;

import br.com.diegopatricio.freela.categoria.domain.Categoria;
import br.com.diegopatricio.freela.servico.domain.Servico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ServicoRepository extends JpaRepository <Servico, Integer> {

    @Transactional(readOnly=true)
   //@Query("SELECT DISTINCT obj FROM Servico obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
    Page<Servico> findDistinctByNomeContainingAndCategoriasIn(@Param("nome") String nome, @Param("categorias") List<Categoria> categorias, Pageable pageRequest);
  // @Override
  // @Query("SELECT service FROM Servico service LEFT JOIN FETCH service.categorias")
   // List<Servico> findAll();
}
