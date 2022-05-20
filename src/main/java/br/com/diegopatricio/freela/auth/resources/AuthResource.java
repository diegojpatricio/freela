package br.com.diegopatricio.freela.auth.resources;


import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import br.com.diegopatricio.freela.auth.services.AuthService;
import br.com.diegopatricio.freela.cliente.domain.EmailDTO;
import br.com.diegopatricio.freela.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.diegopatricio.freela.security.JWTUtil;
import br.com.diegopatricio.freela.security.UserSS;
/*
@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthService service;

    @PostMapping(value = "/refresh_token")
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
        UserSS user = UserService.authenticated();
        String token = jwtUtil.generateToken(user.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        response.addHeader("access-control-expose-headers", "Authorization");
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/esquecisenha")
    public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO objDto) {
        service.sendNewPassword(objDto.getEmail());
        return ResponseEntity.noContent().build();
    }
}*/