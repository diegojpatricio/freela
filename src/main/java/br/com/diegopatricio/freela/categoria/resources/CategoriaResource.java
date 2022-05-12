package br.com.diegopatricio.freela.categoria.resources;

import br.com.diegopatricio.freela.categoria.domain.Categoria;
import br.com.diegopatricio.freela.categoria.domain.CategoriaDTO;
import br.com.diegopatricio.freela.categoria.services.CategoriaService;
import io.swagger.annotations.Api;
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
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService service;

    @GetMapping
    @ApiOperation(value = "Retorna uma lista de Categoria")
    public ResponseEntity<List<CategoriaDTO>> listarCategorias(){
        List<Categoria> listaCategorias = service.listarCategorias();
        List<CategoriaDTO> listaDTO = listaCategorias.stream().map(CategoriaDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listaDTO);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Retorna uma única Categoria")
    public ResponseEntity<Categoria> busarCategoria(@PathVariable Integer id){
        Categoria categoria = service.buscarCategoria(id);
        return ResponseEntity.ok().body(categoria);
    }

    @PostMapping
    @ApiOperation(value = "Cadastra uma Categoria")
    public ResponseEntity<Void> cadastrarCategoria(@Valid @RequestBody CategoriaDTO categoriaDTO){
        Categoria categoria = service.fromDTO(categoriaDTO);
        categoria = service.cadastrarCategoria(categoria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(categoria.getIdCategoria()).toUri();
        return  ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "Atualiza uma Categoria")
    public ResponseEntity<Void> atualizarCategoria(@Valid @RequestBody CategoriaDTO categoriaDTO, @PathVariable Integer id){
        Categoria categoria = service.fromDTO(categoriaDTO);
        categoria.setIdCategoria(id);
        categoria = service.atualizarCategoria(categoria);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Deleta uma Categoria")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Integer id){
        service.deletarCategoria(id);
        return ResponseEntity.noContent().build();
    }
/*
    @GetMapping(value = "/page")
    public ResponseEntity<Page<CategoriaDTO>> buscarPaginaCategoria(
            @RequestParam(value = "page", defaultValue = "0") Integer pagina,
            @RequestParam(value = "linhasPagina", defaultValue = "24") Integer linhasPagina,
            @RequestParam(value = "ordenacao", defaultValue = "nomeCategoria") String ordenacao,
            @RequestParam(value = "direcao", defaultValue = "ASC") String direcao){
        Page<Categoria> listaCategorias = service.buscarPagina(pagina,linhasPagina,ordenacao, direcao);
        Page<CategoriaDTO> listaDTO = listaCategorias.map(CategoriaDTO::new);
        return ResponseEntity.ok().body(listaDTO);
    }*/
}
