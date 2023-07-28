package com.stringcodeltd.myblogapp.service.impl;

import com.stringcodeltd.myblogapp.dto.*;
import com.stringcodeltd.myblogapp.exception.BlogApiException;
import com.stringcodeltd.myblogapp.model.Role;
import com.stringcodeltd.myblogapp.model.User;
import com.stringcodeltd.myblogapp.repository.RoleRepository;
import com.stringcodeltd.myblogapp.repository.UserRepository;
import com.stringcodeltd.myblogapp.security.JwtTokenProvider;
import com.stringcodeltd.myblogapp.service.AuthService;
import com.stringcodeltd.myblogapp.util.PasswordGeneration;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    private JwtTokenProvider jwtTokenProvider;
    private ModelMapper mapper;
//    private PasswordGeneration passwordGeneration;

    public AuthServiceImpl(AuthenticationManager authenticationManager, ModelMapper mapper,RoleRepository roleRepository,
                           UserRepository userRepository,JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.jwtTokenProvider=jwtTokenProvider;
        this.mapper = mapper;

    }

    @Override
    public String login(LoginDTO loginDTO) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtTokenProvider.generateToken(authentication);
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

    @Override
    public String updateUserName(UpdateProfileDTO updateProfileDTO) {
        //gewt loggin user

        User loggedUser = getLoggedUser();
        loggedUser.setName(updateProfileDTO.getName());
        userRepository.save(loggedUser);

        return "User details updated successully";
    }

    @Override
    public String passwordSetting(PasswordSettingDTO passwordSettingDTO) {
        User loggedUser = getLoggedUser();

        boolean matchPasswordWithOldPassword = passwordEncoder().matches(passwordSettingDTO.getOldpassword(), loggedUser.getPassword());

        if(!matchPasswordWithOldPassword){
            throw new RuntimeException("The password is incorrect. Please try again.");
        }
        if(passwordSettingDTO.getNewpassword().length() < 4){
            throw new RuntimeException("Password length must be greater than 3 characters");
        }

        if(!passwordSettingDTO.getNewpassword().equals(passwordSettingDTO.getConfirmpassord())){
            throw new RuntimeException("Password and confirm password are not the same");
        }


        loggedUser.setPassword(passwordEncoder().encode(passwordSettingDTO.getNewpassword()));
        userRepository.save(loggedUser);

        return "Password changed successfully";
    }

    @Override
    public UserDetailsDTO profile() {
        User loggedUser = getLoggedUser();
        loggedUser.getName();
        loggedUser.getEmail();


        return mapper.map(loggedUser, UserDetailsDTO.class);
    }

    private User getLoggedUser(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsernameOrEmail(name, name)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));


    }

    private static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
