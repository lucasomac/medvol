package br.com.lucasomac.medvol.controller;

import br.com.lucasomac.medvol.domain.consumer.ConsumerDTO;
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

    public AuthenticationController(AuthenticationManager manager) {
        this.manager = manager;
    }

    @PostMapping
    public ResponseEntity<Object> handleLogin(@RequestBody @Valid ConsumerDTO data) {
        var token = new UsernamePasswordAuthenticationToken(data.login(), data.pass());
        var authentication = manager.authenticate(token);
        return ResponseEntity.ok().build();
    }
}
