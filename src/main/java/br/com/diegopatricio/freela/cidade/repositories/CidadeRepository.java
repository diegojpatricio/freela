package br.com.diegopatricio.freela.cidade.repositories;


import br.com.diegopatricio.freela.cidade.domain.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
}
