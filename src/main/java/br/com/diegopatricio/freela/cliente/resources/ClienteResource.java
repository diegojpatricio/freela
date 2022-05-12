package br.com.diegopatricio.freela.cliente.resources;

import br.com.diegopatricio.freela.cliente.domain.Cliente;
import br.com.diegopatricio.freela.cliente.domain.ClienteDTO;
import br.com.diegopatricio.freela.cliente.domain.ClienteResourceDTO;
import br.com.diegopatricio.freela.cliente.services.ClienteService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService service;

    @GetMapping
    @ApiOperation(value = "Retorna uma lista de Clientes")
    public ResponseEntity<List<ClienteDTO>> listarClientes(){
        List<Cliente> listaClientes = service.listarClientes();
        List<ClienteDTO> listaDTO = listaClientes.stream().map(ClienteDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listaDTO);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Retorna um único Cliente")
    public ResponseEntity<Cliente> buscarCliente(@PathVariable Integer id){
        Cliente obj = service.buscarCliente(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    @ApiOperation(value = "Cadastra um Cliente")
    public ResponseEntity<Void> cadastrarCliente(@Valid @RequestBody ClienteResourceDTO clienteResourceDTO){
        Cliente cliente = service.fromNewDTO(clienteResourceDTO);
        cliente = service.cadastrarCliente(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(cliente.getIdCliente()).toUri();
        return  ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "Atualiza informações de um Cliente")
    public ResponseEntity<Void> atualizarCliente(@Valid @RequestBody ClienteDTO clienteDTO, @PathVariable Integer id){
        Cliente cliente = service.fromDTO(clienteDTO);
        cliente.setIdCliente(id);
        cliente = service.atualizarCliente(cliente);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Deleta um Cliente")
    public ResponseEntity<Void> deletarCliente(@PathVariable Integer id){
        service.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }
/*
    @GetMapping(value = "/page")
    public ResponseEntity<Page<ClienteDTO>> buscarPagina(
            @RequestParam(value = "page", defaultValue = "0") Integer pagina,
            @RequestParam(value = "linhasPagina", defaultValue = "24") Integer linhasPagina,
            @RequestParam(value = "ordenacao", defaultValue = "nome") String ordenacao,
            @RequestParam(value = "direcao", defaultValue = "ASC") String direcao){
        Page<Cliente> listaClientes = service.buscarPagina(pagina,linhasPagina,ordenacao, direcao);
        Page<ClienteDTO> listaDTO = listaClientes.map(ClienteDTO::new);
        return ResponseEntity.ok().body(listaDTO);
    }*/


}
