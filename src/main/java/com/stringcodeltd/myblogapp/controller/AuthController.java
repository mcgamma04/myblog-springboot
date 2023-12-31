package com.stringcodeltd.myblogapp.controller;

import com.stringcodeltd.myblogapp.dto.*;
import com.stringcodeltd.myblogapp.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody UpdateProfileDTO updateProfileDTO){
        String response = authService.updateUserName(updateProfileDTO);
        return new ResponseEntity<>(response,HttpStatus.OK);

    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/security")
    public ResponseEntity<String> passwordSetting(@RequestBody PasswordSettingDTO passwordSettingDTO){
        String response = authService.passwordSetting(passwordSettingDTO);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/profile")
    public  ResponseEntity<UserDetailsDTO> profile(){
        UserDetailsDTO profileR = authService.profile();
        return  new ResponseEntity<>(profileR, HttpStatus.OK);

    }
}
