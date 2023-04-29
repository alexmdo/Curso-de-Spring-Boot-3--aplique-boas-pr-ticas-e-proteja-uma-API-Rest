package med.voll.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import med.voll.api.domain.usuario.AuthenticationDataDTO;
import med.voll.api.domain.usuario.User;
import med.voll.api.infra.security.TokenService;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody @Valid AuthenticationDataDTO authenticationDataDTO) {
        var token = new UsernamePasswordAuthenticationToken(authenticationDataDTO.login(), authenticationDataDTO.password());
        var authentication = authenticationManager.authenticate(token);
        return ResponseEntity.ok(tokenService.generateToken((User) authentication.getPrincipal()));
    }
    
}
