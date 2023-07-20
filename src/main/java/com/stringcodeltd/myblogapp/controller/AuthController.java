package com.stringcodeltd.myblogapp.controller;

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
    public ResponseEntity<String> loginUser(@RequestBody LoginDTO loginDTO){
        String login = authService.login(loginDTO);
        return ResponseEntity.ok(login);
    }

    @PostMapping(value = {"/register","/signup"})
    public ResponseEntity<String> registerUser(@RequestBody RegisterDTO registerDTO){
        String register = authService.register(registerDTO);

        return new ResponseEntity<>(register,HttpStatus.CREATED );

    }
}
