package br.com.diegopatricio.freela.cliente.services.validations;


import br.com.diegopatricio.freela.cliente.repositories.ClienteRepository;
import br.com.diegopatricio.freela.cliente.domain.Cliente;
import br.com.diegopatricio.freela.cliente.domain.ClienteDTO;
import br.com.diegopatricio.freela.exceptions.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ClienteRepository repo;

    @Override
    public void initialize(ClienteUpdate ann){}

    @Override
    public boolean isValid(ClienteDTO clienteDTO, ConstraintValidatorContext context){

        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        Cliente aux = repo.findByEmail(clienteDTO.getEmail());
        if (aux != null && !aux.getIdCliente().equals(uriId)){
            list.add(new FieldMessage("email","E-mail já cadastrado!"));
        }

        for (FieldMessage e : list){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessagem()).addPropertyNode(e.getCampo()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}