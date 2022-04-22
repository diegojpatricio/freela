package br.com.diegopatricio.freela.ordemservico.resources;

import br.com.diegopatricio.freela.categoria.domain.Categoria;
import br.com.diegopatricio.freela.ordemservico.domain.OrdemServico;
import br.com.diegopatricio.freela.ordemservico.services.OrdemServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ordemservicos")
public class OrdemServicoResource {

    @Autowired
    private OrdemServicoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrdemServico> buscarOrdemServico(@PathVariable Integer id){
        OrdemServico obj = service.buscarOrdemServico(id);
        return ResponseEntity.ok().body(obj);

    }


}
