package br.com.diegopatricio.freela.servico.service;

import br.com.diegopatricio.freela.categoria.domain.Categoria;
import br.com.diegopatricio.freela.categoria.repositories.CategoriaRepository;
import br.com.diegopatricio.freela.categoria.services.CategoriaService;
import br.com.diegopatricio.freela.exceptions.ExceptionDataIntegrityViolation;
import br.com.diegopatricio.freela.exceptions.ObjectNotFoundException;
import br.com.diegopatricio.freela.servico.domain.Servico;
import br.com.diegopatricio.freela.servico.domain.ServicoDTO;
import br.com.diegopatricio.freela.servico.repositories.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository repo;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CategoriaService categoriaService;

    public List<Servico> listarServicos(){
        return repo.findAll();
    }

    public Servico buscarServico(Integer id){
        Optional<Servico> obj = repo.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrato! ID: " + id +
                ", Tipo: " + Servico.class.getName()));
    }

   public Servico cadastrarServico(Servico servico) {
       try {
           servico.setIdServico(null);
           servico =  repo.save(servico);
           servico.setCategorias(categoriaRepository.findByServicos_IdServico(servico.getIdServico()));
           return servico;
       }
       catch (DataIntegrityViolationException e){
           throw new DataIntegrityViolationException("Categoria informada inexistete!");
       }


    }

    public Page<Servico> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        List<Categoria> categorias = categoriaRepository.findAllById(ids);
        return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
    }

    public Servico atualizarServico(Servico servico) {
        Servico novoServico = buscarServico(servico.getIdServico());
        updateData(novoServico, servico);
        return repo.save(novoServico);
    }

    public void deletarServico(Integer id) {
        buscarServico(id);
        try {
            repo.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new ExceptionDataIntegrityViolation("Existe uma associação entre categorias e serviços." +
                    " Este procedimento não pode ser concluído!");
        }

    }

    public Servico fromDTOService(ServicoDTO servicoDTO){
        return new Servico(servicoDTO.getIdServico(), servicoDTO.getNome(), servicoDTO.getValor(), servicoDTO.getCategorias());

    }

    private void updateData(Servico novoServico, Servico servico){
        novoServico.setNome(servico.getNome());
        novoServico.setValor(servico.getValor());
        novoServico.setCategorias(servico.getCategorias());
    }

}
