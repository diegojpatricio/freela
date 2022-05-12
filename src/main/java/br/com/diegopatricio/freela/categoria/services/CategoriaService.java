package br.com.diegopatricio.freela.categoria.services;

import br.com.diegopatricio.freela.categoria.domain.Categoria;
import br.com.diegopatricio.freela.categoria.domain.CategoriaDTO;
import br.com.diegopatricio.freela.categoria.repositories.CategoriaRepository;
import br.com.diegopatricio.freela.exceptions.ExceptionDataIntegrityViolation;
import br.com.diegopatricio.freela.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository repo;

    public List<Categoria> listarCategorias(){
        return repo.findAll();
    }

    public Categoria buscarCategoria(Integer id){
        Optional<Categoria> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrato! ID: " + id +
                ", Tipo: " + Categoria.class.getName()));
    }

    public Categoria cadastrarCategoria(Categoria categoria) {
        categoria.setIdCategoria(null);
        return repo.save(categoria);
    }

    public Categoria atualizarCategoria(Categoria categoria) {
        Categoria novaCategoria = buscarCategoria(categoria.getIdCategoria());
        updateData(novaCategoria, categoria);
        return repo.save(novaCategoria);
    }

    public void deletarCategoria(Integer id) {
        buscarCategoria(id);
        try {
            repo.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new ExceptionDataIntegrityViolation("Existe uma associação entre categorias e serviços." +
                    " Este procedimento não pode ser concluído!");
        }

    }

    public Page<Categoria> buscarPagina(Integer pagina, Integer linhasPagina, String ordenacao, String direcao){
        PageRequest pageRequest = PageRequest.of(pagina, linhasPagina, Sort.Direction.valueOf(direcao), ordenacao);
        return repo.findAll(pageRequest);

    }

    public Categoria fromDTO(CategoriaDTO categoriaDTO){
        return new Categoria(categoriaDTO.getIdCategoria(), categoriaDTO.getNomeCategoria());

    }

    private void updateData(Categoria novaCategoria, Categoria categoria){
        novaCategoria.setNomeCategoria(categoria.getNomeCategoria());
    }

    /*
    * public Cliente buscarCliente(Long id){
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrato! ID: " + id +
                ", Tipo: " + Cliente.class.getName()));

    }

    public List<Cliente> listarClientes(){
        return clienteRepository.findAll();
    }

    public Cliente gerarCliente(Cliente cliente){
        cliente.setId(null);
        return clienteRepository.save(cliente);

    }

    public Cliente atualizarCliente(Long id, ClienteDTO clienteDto) {
        Cliente upCliente = buscarCliente(id);
        upCliente.setNome(clienteDto.getNome());
        upCliente.setCnpj(clienteDto.getCnpj());
        upCliente.setCpf(clienteDto.getCpf());
        upCliente.setEmail(clienteDto.getEmail());
        upCliente.setEndereco(clienteDto.getEndereco());
        upCliente.setTelefone(clienteDto.getTelefone());
        return clienteRepository.save(upCliente);
    }

    public void deletarCliente(Long id) {

        //Cliente cliente = buscarCliente(id);
        //clienteRepository.delete(cliente);

        buscarCliente(id);
        try{
            clienteRepository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new ExceptionDataIntegrityViolation("Existe uma associação entre contas e clientes." +
                    " Este procedimento não pode ser concluído!");
        }
    }*/
}
