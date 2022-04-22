package br.com.diegopatricio.freela.ordemservico.repositories;

import br.com.diegopatricio.freela.ordemservico.domain.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdemServicoRepository extends JpaRepository <OrdemServico, Integer> {
}
