package com.stringcodeltd.myblogapp.controller;

import com.stringcodeltd.myblogapp.dto.JwtResponseDTO;
import com.stringcodeltd.myblogapp.dto.LoginDTO;
import com.stringcodeltd.myblogapp.dto.RegisterDTO;
import com.stringcodeltd.myblogapp.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = {"/login","/signin"})
    public ResponseEntity<JwtResponseDTO> loginUser(@RequestBody LoginDTO loginDTO){
        String token = authService.login(loginDTO);
        JwtResponseDTO jwtResponseDTO = new JwtResponseDTO();
        jwtResponseDTO.setAccessToken(token);


        return ResponseEntity.ok(jwtResponseDTO);
    }

    @PostMapping(value = {"/register","/signup"})
    public ResponseEntity<String> registerUser(@RequestBody RegisterDTO registerDTO){
        String register = authService.register(registerDTO);

        return new ResponseEntity<>(register,HttpStatus.CREATED );

    }
}
