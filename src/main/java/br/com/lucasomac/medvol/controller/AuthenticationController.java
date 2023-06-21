package br.com.lucasomac.medvol.controller;

import br.com.lucasomac.medvol.domain.consumer.Consumer;
import br.com.lucasomac.medvol.domain.consumer.ConsumerDTO;
import br.com.lucasomac.medvol.infra.TokenService;
import br.com.lucasomac.medvol.infra.security.TokenDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.com.lucasomac.medvol.commons.Constants.API_LOGIN_PATH;

@RestController
@RequestMapping(API_LOGIN_PATH)
public class AuthenticationController {
    private final AuthenticationManager manager;
    private final TokenService tokenService;

    public AuthenticationController(AuthenticationManager manager, TokenService tokenService) {
        this.manager = manager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<Object> handleLogin(@RequestBody @Valid ConsumerDTO data) {
        var token = new UsernamePasswordAuthenticationToken(data.login(), data.pass());
        var authentication = manager.authenticate(token);
        var jwt = tokenService.generateToken((Consumer) authentication.getPrincipal());
        return ResponseEntity.ok(new TokenDTO(jwt));
    }
}
