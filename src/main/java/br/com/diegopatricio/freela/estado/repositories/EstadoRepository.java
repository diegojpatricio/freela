package br.com.diegopatricio.freela.estado.repositories;

import br.com.diegopatricio.freela.estado.domain.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {
}
