package br.com.diegopatricio.freela.cliente.services;


import br.com.diegopatricio.freela.cliente.domain.Cliente;
import br.com.diegopatricio.freela.ordemservico.domain.OrdemServico;
import org.springframework.mail.SimpleMailMessage;



public interface EmailService {

    void sendOrderConfirmationEmail(OrdemServico obj);

    void sendEmail(SimpleMailMessage msg);

    void sendNewPasswordEmail(Cliente cliente, String newPass);
}