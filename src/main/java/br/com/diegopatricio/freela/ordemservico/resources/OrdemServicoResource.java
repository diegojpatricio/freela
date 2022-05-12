package br.com.diegopatricio.freela.ordemservico.resources;

import br.com.diegopatricio.freela.categoria.domain.Categoria;
import br.com.diegopatricio.freela.categoria.domain.CategoriaDTO;
import br.com.diegopatricio.freela.ordemservico.domain.OrdemServico;
import br.com.diegopatricio.freela.ordemservico.domain.OrdemServicoDTO;
import br.com.diegopatricio.freela.ordemservico.services.OrdemServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/ordemservicos")
public class OrdemServicoResource {

    @Autowired
    private OrdemServicoService service;

    @GetMapping
    public ResponseEntity<List<OrdemServicoDTO>> listarOrdemServico(){
        List<OrdemServico> listaCategorias = service.listarOS();
        List<OrdemServicoDTO> listaDTO = listaCategorias.stream().map(OrdemServicoDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listaDTO);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<OrdemServico> buscarOrdemServico(@PathVariable Integer id){
        OrdemServico obj = service.buscarOrdemServico(id);
        return ResponseEntity.ok().body(obj);

    }

    @PostMapping
    public ResponseEntity<Void> cadastrarOS(@Valid @RequestBody OrdemServico ordemServico) {
        ordemServico = service.cadastrarOS(ordemServico);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(ordemServico.getIdOrdemServico()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<OrdemServico> atualizarOS(@Valid @RequestBody OrdemServicoDTO ordemServicoDTO, @PathVariable Integer id){
        OrdemServico os = service.fromDTOOrdemServico(ordemServicoDTO);
        os.setIdOrdemServico(id);
        os = service.atualizarOS(os);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(os.getIdOrdemServico()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarOS(@PathVariable Integer id){
        service.deletarOS(id);
        return ResponseEntity.noContent().build();
    }


}
