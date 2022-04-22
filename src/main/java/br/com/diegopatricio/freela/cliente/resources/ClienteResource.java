package br.com.diegopatricio.freela.cliente.resources;

import br.com.diegopatricio.freela.categoria.domain.Categoria;
import br.com.diegopatricio.freela.categoria.services.CategoriaService;
import br.com.diegopatricio.freela.cliente.domain.Cliente;
import br.com.diegopatricio.freela.cliente.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> buscarCliente(@PathVariable Integer id){
        Cliente obj = clienteService.buscarCliente(id);
        return ResponseEntity.ok().body(obj);

    }
}
