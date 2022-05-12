package br.com.diegopatricio.freela.ordemservico.services;

import br.com.diegopatricio.freela.categoria.domain.Categoria;
import br.com.diegopatricio.freela.categoria.domain.CategoriaDTO;
import br.com.diegopatricio.freela.cliente.domain.Cliente;
import br.com.diegopatricio.freela.cliente.services.ClienteService;
import br.com.diegopatricio.freela.exceptions.ConstraintViolationException;
import br.com.diegopatricio.freela.exceptions.ExceptionDataIntegrityViolation;
import br.com.diegopatricio.freela.exceptions.ObjectNotFoundException;
import br.com.diegopatricio.freela.ordemservico.domain.OrdemServico;
import br.com.diegopatricio.freela.ordemservico.domain.OrdemServicoDTO;
import br.com.diegopatricio.freela.ordemservico.repositories.OrdemServicoRepository;
import br.com.diegopatricio.freela.pagamento.domain.PagamentoBoleto;
import br.com.diegopatricio.freela.pagamento.domain.StatusPagamento;
import br.com.diegopatricio.freela.pagamento.repositories.PagamentoRepository;
import br.com.diegopatricio.freela.pagamento.services.BoletoService;
import br.com.diegopatricio.freela.servico.domain.Servico;
import br.com.diegopatricio.freela.servico.repositories.ServicoRepository;
import br.com.diegopatricio.freela.servico.service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrdemServicoService {

    @Autowired
    private OrdemServicoRepository repo;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    private Servico servico;
    

    public OrdemServico buscarOrdemServico(Integer id){
        Optional<OrdemServico> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrato! ID: " + id +
                ", Tipo: " + OrdemServico.class.getName()));
    }


    public List<OrdemServico> listarOS(){
        return repo.findAll();
    }


    public OrdemServico cadastrarOS(OrdemServico ordemServico){

        try {
            ordemServico.setValorOrdemServico(0d);
            ordemServico.setIdOrdemServico(null);
            ordemServico.setDataSolicitacao(new Date());
            ordemServico.setCliente(clienteService.buscarCliente(ordemServico.getCliente().getIdCliente()));
            ordemServico.getPagamento().setStatusPagamento(StatusPagamento.PENDENTE);
            ordemServico.getPagamento().setOrdemServico(ordemServico);
            if (ordemServico.getPagamento() instanceof PagamentoBoleto) {
                PagamentoBoleto pagto = (PagamentoBoleto) ordemServico.getPagamento();
                boletoService.preencherPagamentoComBoleto(pagto, ordemServico.getDataSolicitacao());
            }
            Optional<Servico> serv;
            for (Servico se : ordemServico.getServicos()) {
                serv = servicoRepository.findById(se.getIdServico());
                ordemServico.setValorOrdemServico(ordemServico.getValorOrdemServico() + (serv.isPresent() ? serv.get().getValor() : 0d));
            }

            ordemServico = repo.save(ordemServico);
            pagamentoRepository.save(ordemServico.getPagamento());
//        servicoRepository.saveAll(ordemServico.getServicos());
            return ordemServico;
        }
        catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("Dados Inválidos! Verifica e informe os dados corretamente! ");
        }

    }

    public OrdemServico atualizarOS(OrdemServico ordemServico) {
        OrdemServico novaOrdemServico = buscarOrdemServico(ordemServico.getIdOrdemServico());
       updateData(novaOrdemServico, ordemServico);
        return repo.save(novaOrdemServico);
    }

    public void deletarOS(Integer id) {
        buscarOrdemServico(id);
        try {
            repo.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new ExceptionDataIntegrityViolation("Existe uma associação entre categorias e serviços." +
                    " Este procedimento não pode ser concluído!");
        }

    }

    public OrdemServico fromDTOOrdemServico(OrdemServicoDTO ordemServicoDTO){
        return new OrdemServico(ordemServicoDTO.getIdOrdemServico(), ordemServicoDTO.getDataSolicitacao(), ordemServicoDTO.getValorOrdemServico(), ordemServicoDTO.getPagamento(), ordemServicoDTO.getCliente());
    }

    private void updateData(OrdemServico novaOS, OrdemServico ordemServico){
        novaOS.setPagamento(ordemServico.getPagamento());
    }

}
