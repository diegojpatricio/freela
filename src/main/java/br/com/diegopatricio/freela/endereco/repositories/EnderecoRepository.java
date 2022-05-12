package br.com.diegopatricio.freela.endereco.repositories;

import br.com.diegopatricio.freela.endereco.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}
