package br.com.diegopatricio.freela.servico.resources;

import br.com.diegopatricio.freela.categoria.domain.Categoria;
import br.com.diegopatricio.freela.categoria.domain.CategoriaDTO;
import br.com.diegopatricio.freela.servico.domain.Servico;
import br.com.diegopatricio.freela.servico.domain.ServicoDTO;
import br.com.diegopatricio.freela.servico.resources.utils.URL;
import br.com.diegopatricio.freela.servico.service.ServicoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value="/servicos")
public class ServicoResource {

    @Autowired
    private ServicoService service;

    @GetMapping
    @ApiOperation(value = "Retorna uma listade Serviço")
    public ResponseEntity<List<Servico>> listarServicos(){
        List<Servico> listarServicos = service.listarServicos();
        //List<ServicoDTO> listaDTO = listarServicos.stream().map(ServicoDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listarServicos);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Retorna um Serviço")
    public ResponseEntity<Servico> buscarServico(@PathVariable Integer id) {
        Servico servico = service.buscarServico(id);
        return ResponseEntity.ok().body(servico);
    }

   @PostMapping
   @PreAuthorize("hasAnyRole('ADMIN')")
   @ApiOperation(value = "Cadastra um Serviço")
    public ResponseEntity<Void> cadastrarServico(@RequestBody ServicoDTO servicoDTO){
        Servico servico = service.fromDTOService(servicoDTO);
        servico = service.cadastrarServico(servico);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(servico.getIdServico()).toUri();
        return  ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "Atualiza o nome e categoria do serviço")
    public ResponseEntity<Void> atualizarServico(@Valid @RequestBody ServicoDTO servicoDTO, @PathVariable Integer id){
        Servico servico = service.fromDTOService(servicoDTO);
        servico.setIdServico(id);
        servico = service.atualizarServico(servico);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "Deleta um serviço")
    public ResponseEntity<Void> deletarServico(@PathVariable Integer id){
        service.deletarServico(id);
        return ResponseEntity.noContent().build();
    }

    /*
    @GetMapping(value = "/page")
    public ResponseEntity<Page<ServicoDTO>> buscarPaginaServico(
            @RequestParam(value="nome", defaultValue="") String nome,
            @RequestParam(value="categorias", defaultValue="") String categorias,
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linhasPagina", defaultValue="24") Integer linhasPagina,
            @RequestParam(value="ordenacao", defaultValue="nome") String ordenacao,
            @RequestParam(value="direcao", defaultValue="ASC") String direcao) {
        String nomeDecoded = URL.decodeParam(nome);
        List<Integer> ids = URL.decodeIntList(categorias);
        Page<Servico> list = service.search(nomeDecoded, ids, page, linhasPagina, ordenacao, direcao);
        Page<ServicoDTO> listDto = list.map(ServicoDTO::new);
        return ResponseEntity.ok().body(listDto);
    }*/

}
