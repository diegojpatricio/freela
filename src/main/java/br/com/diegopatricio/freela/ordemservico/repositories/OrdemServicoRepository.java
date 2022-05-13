package br.com.diegopatricio.freela.ordemservico.repositories;

import br.com.diegopatricio.freela.cliente.domain.Cliente;
import br.com.diegopatricio.freela.ordemservico.domain.OrdemServico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OrdemServicoRepository extends JpaRepository <OrdemServico, Integer> {

    @Transactional(readOnly = true)
    Page<OrdemServico> findByCliente(Cliente cliente, Pageable pageRequest);
}
