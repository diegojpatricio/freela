package br.com.diegopatricio.freela.ordemservico.resources;

import br.com.diegopatricio.freela.categoria.domain.Categoria;
import br.com.diegopatricio.freela.categoria.domain.CategoriaDTO;
import br.com.diegopatricio.freela.ordemservico.domain.OrdemServico;
import br.com.diegopatricio.freela.ordemservico.domain.OrdemServicoDTO;
import br.com.diegopatricio.freela.ordemservico.domain.OrdemServicoDTOPut;
import br.com.diegopatricio.freela.ordemservico.services.OrdemServicoService;
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
@RequestMapping(value = "/ordemservicos")
public class OrdemServicoResource {

    @Autowired
    private OrdemServicoService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "Retorna lista de Ordem de Serviço")
    public ResponseEntity<List<OrdemServicoDTO>> listarOrdemServico(){
        List<OrdemServico> listaCategorias = service.listarOS();
        List<OrdemServicoDTO> listaDTO = listaCategorias.stream().map(OrdemServicoDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listaDTO);
    }


    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Retorna uma única Ordem de Serviço")
    public ResponseEntity<OrdemServico> buscarOrdemServico(@PathVariable Integer id){
        OrdemServico ordemServico = service.buscarOrdemServico(id);
        return ResponseEntity.ok().body(ordemServico);

    }

    @PostMapping
    public ResponseEntity<Void> cadastrarOS(@Valid @RequestBody OrdemServico ordemServico) {
        ordemServico = service.cadastrarOS(ordemServico);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(ordemServico.getIdOrdemServico()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "Atualiza o status de pagamento da Ordem de Serviço")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<OrdemServico> atualizarOS(@Valid @RequestBody OrdemServicoDTOPut ordemServicoDTO, @PathVariable Integer id){
        OrdemServico os = service.fromDTOPutOrdemServico(ordemServicoDTO);
        os.setIdOrdemServico(id);
        os = service.atualizarOS(os);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(os.getIdOrdemServico()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Deleta uma Ordem de Serviço")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> deletarOS(@PathVariable Integer id){
        service.deletarOS(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/page")
    @ApiOperation(value = "Retorna uma Ordem de Serviço Listada por página")
    public ResponseEntity<Page<OrdemServico>> buscarPaginaCategoria(
            @RequestParam(value = "page", defaultValue = "0") Integer pagina,
            @RequestParam(value = "linhasPagina", defaultValue = "24") Integer linhasPagina,
            @RequestParam(value = "ordenacao", defaultValue = "dataSolicitacao") String ordenacao,
            @RequestParam(value = "direcao", defaultValue = "DESC") String direcao){
        Page<OrdemServico> listaOS = service.buscarPagina(pagina, linhasPagina, ordenacao, direcao);
        return ResponseEntity.ok().body(listaOS);
    }


}
