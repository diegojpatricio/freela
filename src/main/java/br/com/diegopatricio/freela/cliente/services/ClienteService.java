package br.com.diegopatricio.freela.cliente.services;

import br.com.diegopatricio.freela.cliente.domain.Cliente;
import br.com.diegopatricio.freela.cliente.repositories.ClienteRepository;
import br.com.diegopatricio.freela.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente buscarCliente(Integer id){
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrato! ID: " + id +
                ", Tipo: " + Cliente.class.getName()));
    }
}
