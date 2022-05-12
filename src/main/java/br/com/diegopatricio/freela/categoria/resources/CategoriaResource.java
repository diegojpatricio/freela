package br.com.diegopatricio.freela.categoria.resources;

import br.com.diegopatricio.freela.categoria.domain.Categoria;
import br.com.diegopatricio.freela.categoria.domain.CategoriaDTO;
import br.com.diegopatricio.freela.categoria.services.CategoriaService;
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
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService service;

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listarCategorias(){
        List<Categoria> listaCategorias = service.listarCategorias();
        List<CategoriaDTO> listaDTO = listaCategorias.stream().map(CategoriaDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listaDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Categoria> busarCategoria(@PathVariable Integer id){
        Categoria categoria = service.buscarCategoria(id);
        return ResponseEntity.ok().body(categoria);
    }

    @PostMapping
    public ResponseEntity<Void> cadastrarCategoria(@Valid @RequestBody CategoriaDTO categoriaDTO){
        Categoria categoria = service.fromDTO(categoriaDTO);
        categoria = service.cadastrarCategoria(categoria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(categoria.getIdCategoria()).toUri();
        return  ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> atualizarCategoria(@Valid @RequestBody CategoriaDTO categoriaDTO, @PathVariable Integer id){
        Categoria categoria = service.fromDTO(categoriaDTO);
        categoria.setIdCategoria(id);
        categoria = service.atualizarCategoria(categoria);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Integer id){
        service.deletarCategoria(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Page<CategoriaDTO>> buscarPaginaCategoria(
            @RequestParam(value = "page", defaultValue = "0") Integer pagina,
            @RequestParam(value = "linhasPagina", defaultValue = "24") Integer linhasPagina,
            @RequestParam(value = "ordenacao", defaultValue = "nomeCategoria") String ordenacao,
            @RequestParam(value = "direcao", defaultValue = "ASC") String direcao){
        Page<Categoria> listaCategorias = service.buscarPagina(pagina,linhasPagina,ordenacao, direcao);
        Page<CategoriaDTO> listaDTO = listaCategorias.map(CategoriaDTO::new);
        return ResponseEntity.ok().body(listaDTO);
    }

    /*
     * Por questões de boas práticas deve-se retornar para o usuário a URI de acesso ao novo objeto criado.
     *
     *  @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> buscarCliente(@PathVariable Long id){
        Cliente obj = clienteService.buscarCliente(id);
        return ResponseEntity.ok().body(obj);
    }


    @GetMapping
    public  ResponseEntity<List<ClienteDTO>> listarClientes(){
        List<Cliente> list = clienteService.listarClientes();
        List<ClienteDTO> listDTO = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
    @PostMapping
    public ResponseEntity<Cliente> gerarCliente(@RequestBody Cliente cliente){
        cliente = clienteService.gerarCliente(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(cliente);
    }
    *
    * @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> atualizarCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDto){
       Cliente upCliente = clienteService.atualizarCliente(id, clienteDto);
       return ResponseEntity.ok().body(new ClienteDTO(upCliente));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id){
        clienteService.deletarCliente(id);
        return ResponseEntity.noContent().build();

    }*/
}
