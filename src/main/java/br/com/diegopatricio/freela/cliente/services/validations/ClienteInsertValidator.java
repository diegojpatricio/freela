package br.com.diegopatricio.freela.cliente.services.validations;


import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.diegopatricio.freela.cliente.domain.Cliente;
import br.com.diegopatricio.freela.cliente.domain.ClienteResourceDTO;
import br.com.diegopatricio.freela.cliente.domain.TipoCliente;
import br.com.diegopatricio.freela.cliente.repositories.ClienteRepository;
import br.com.diegopatricio.freela.exceptions.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;



public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteResourceDTO> {

    @Autowired
    private ClienteRepository repo;

    @Override
    public void initialize(ClienteInsert ann){}

    @Override
    public boolean isValid(ClienteResourceDTO clienteResourceDTO, ConstraintValidatorContext context){
        List<FieldMessage> list = new ArrayList<>();

        if (clienteResourceDTO.getTipoCliente().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(clienteResourceDTO.getCpfCnpj())){
        list.add(new FieldMessage("cpfCnpj", "CPF Inválido!"));
        }

        if (clienteResourceDTO.getTipoCliente().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(clienteResourceDTO.getCpfCnpj())){
            list.add(new FieldMessage("cpfCnpj", "CNPJ Inválido!"));
        }

        Cliente aux = repo.findByEmail(clienteResourceDTO.getEmail());
        if (aux != null){
            list.add(new FieldMessage("email","E-mail já cadastrado!"));
        }

        for (FieldMessage e : list){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessagem()).addPropertyNode(e.getCampo()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}