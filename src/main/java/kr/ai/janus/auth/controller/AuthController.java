package kr.ai.janus.auth.controller;

import jakarta.validation.Valid;
import kr.ai.janus.auth.dto.AuthResponse;
import kr.ai.janus.auth.dto.LoginRequest;
import kr.ai.janus.auth.oauth.OAuthProvider;
import kr.ai.janus.auth.service.AuthService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login/{provider}")
    public AuthResponse login(@PathVariable OAuthProvider provider,
                              @Valid @RequestBody LoginRequest request) {
        return authService.login(provider, request.code());
    }
}
