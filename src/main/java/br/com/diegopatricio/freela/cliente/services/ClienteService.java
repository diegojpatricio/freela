package br.com.diegopatricio.freela.cliente.services;

import br.com.diegopatricio.freela.cidade.domain.Cidade;
import br.com.diegopatricio.freela.cliente.domain.ClienteDTO;
import br.com.diegopatricio.freela.cliente.domain.ClienteResourceDTO;
import br.com.diegopatricio.freela.cliente.domain.TipoCliente;
import br.com.diegopatricio.freela.cliente.repositories.ClienteRepository;
import br.com.diegopatricio.freela.cliente.domain.Cliente;
import br.com.diegopatricio.freela.endereco.domain.Endereco;
import br.com.diegopatricio.freela.endereco.repositories.EnderecoRepository;
import br.com.diegopatricio.freela.exceptions.AuthorizationException;
import br.com.diegopatricio.freela.exceptions.ExceptionDataIntegrityViolation;
import br.com.diegopatricio.freela.exceptions.ObjectNotFoundException;
import br.com.diegopatricio.freela.perfil.Perfil;
import br.com.diegopatricio.freela.security.UserSS;
import br.com.diegopatricio.freela.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    @Autowired
    private EnderecoRepository enderecoRepository;


    private Perfil perfil;

    @Autowired
    private BCryptPasswordEncoder pe;

    public List<Cliente> listarClientes(){
        return repo.findAll();
    }

    public Cliente buscarCliente(Integer id){

        UserSS user = UserService.authenticated();
        if (user==null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
            throw new AuthorizationException("Acesso negado");
        }

        Optional<Cliente> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrato! ID: " + id +
                ", Tipo: " + Cliente.class.getName()));
    }

    @Transactional
    public Cliente cadastrarCliente(Cliente cliente) {
        cliente.setIdCliente(null);
        cliente = repo.save(cliente);
        enderecoRepository.saveAll(cliente.getEnderecos());
        return cliente;
    }

    public Cliente atualizarCliente(Cliente cliente) {
        UserSS user = UserService.authenticated();
        if (user==null || !user.hasRole(Perfil.ADMIN) && !cliente.getIdCliente().equals(user.getId())) {
            throw new AuthorizationException("Acesso negado");
        }

        Cliente novoCliente = buscarCliente(cliente.getIdCliente());
        updateData(novoCliente, cliente);
        return repo.save(novoCliente);
    }

    public Cliente tornarAdmin(Cliente cliente) {
        UserSS user = UserService.authenticated();
        if (user==null || !user.hasRole(Perfil.ADMIN) && !cliente.getIdCliente().equals(user.getId())) {
            throw new AuthorizationException("Acesso negado");
        }

        Cliente novoCliente = buscarCliente(cliente.getIdCliente());
        tornarAdmin(novoCliente, cliente);
        return repo.save(novoCliente);
    }

    public void deletarCliente(Integer id) {
        buscarCliente(id);
        try {
            repo.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new ExceptionDataIntegrityViolation("Não é possivel excluir , porque há entidades relacionadas.");
        }
    }

    public Page<Cliente> buscarPagina(Integer pagina, Integer linhasPagina, String ordenacao, String direcao){
        PageRequest pageRequest = PageRequest.of(pagina, linhasPagina, Sort.Direction.valueOf(direcao), ordenacao);
        return repo.findAll(pageRequest);

    }

    public Cliente fromDTO(ClienteDTO clienteDTO){
        return new Cliente(clienteDTO.getIdCliente(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null,null);
    }
    public Cliente fromNewDTO(ClienteResourceDTO clienteResourceDTO){
        Cliente cliente = new Cliente(null, clienteResourceDTO.getNome(), clienteResourceDTO.getEmail(), clienteResourceDTO.getCpfCnpj(), TipoCliente.toEnum(clienteResourceDTO.getTipoCliente()), pe.encode(clienteResourceDTO.getSenha()));
        Cidade cidade = new Cidade(clienteResourceDTO.getCidadeId(), null, null);
        Endereco endereco = new Endereco(null, clienteResourceDTO.getLogradouro(), clienteResourceDTO.getNumero(), clienteResourceDTO.getComplemento(), clienteResourceDTO.getBairro(), clienteResourceDTO.getCep(), cliente, cidade);
        cliente.getEnderecos().add(endereco);
        cliente.getTelefones().add(clienteResourceDTO.getTelefone1());
        if (clienteResourceDTO.getTelefone2()!=null) {
            cliente.getTelefones().add(clienteResourceDTO.getTelefone2());
        }
        return cliente;
    }

    private void updateData(Cliente novoCliente, Cliente cliente){
        novoCliente.setNome(cliente.getNome());
        novoCliente.setEmail(cliente.getEmail());
    }

    private void tornarAdmin(Cliente novoCliente, Cliente cliente){
        novoCliente.addPerfil(Perfil.ADMIN);
    }
}
