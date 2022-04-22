package br.com.diegopatricio.freela.categoria.resources;

import br.com.diegopatricio.freela.categoria.domain.Categoria;
import br.com.diegopatricio.freela.categoria.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Categoria> busarCategoria(@PathVariable Integer id){
        Categoria obj = service.buscarCategoria(id);
        return ResponseEntity.ok().body(obj);

    }
}
