package com.stringcodeltd.myblogapp.service.impl;

import com.stringcodeltd.myblogapp.dto.LoginDTO;
import com.stringcodeltd.myblogapp.dto.RegisterDTO;
import com.stringcodeltd.myblogapp.exception.BlogApiException;
import com.stringcodeltd.myblogapp.model.Role;
import com.stringcodeltd.myblogapp.model.User;
import com.stringcodeltd.myblogapp.repository.RoleRepository;
import com.stringcodeltd.myblogapp.repository.UserRepository;
import com.stringcodeltd.myblogapp.service.AuthService;
import com.stringcodeltd.myblogapp.util.PasswordGeneration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class AuthServiceImpl implements AuthService {
    private AuthenticationManager authenticationManager;
    private RoleRepository roleRepository;
    private UserRepository userRepository;
//    private PasswordGeneration passwordGeneration;

    public AuthServiceImpl(AuthenticationManager authenticationManager, RoleRepository roleRepository,
                           UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;

    }

    @Override
    public String login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "You have login successfully";
    }

    @Override
    public String register(RegisterDTO registerDTO) {
        //check if email is taken
       if(userRepository.existsByUsername(registerDTO.getUsername())) {
           throw new BlogApiException(HttpStatus.BAD_REQUEST, "Username already exists, Login instead");
       }
       if(userRepository.existsByEmail(registerDTO.getEmail())){
           throw new BlogApiException(HttpStatus.BAD_REQUEST,"Email already exists, Login instead");
       }
       //check password leng
        if(registerDTO.getPassword().length() < 4){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Password length must not less than 4 character ");
        }
        //CREATE  user object
        User user = new User();
        user.setName(registerDTO.getName());
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder().encode( registerDTO.getPassword()));

        //Set Roles
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName("ROLE_USER").get());
        user.setRoles(roles);
        userRepository.save(user);

        return "You have been registered successfully,kindly login to continue";
    }
    private static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
