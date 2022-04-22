package br.com.diegopatricio.freela.servico.repositories;

import br.com.diegopatricio.freela.servico.domain.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoRepository extends JpaRepository <Servico, Integer> {
}
