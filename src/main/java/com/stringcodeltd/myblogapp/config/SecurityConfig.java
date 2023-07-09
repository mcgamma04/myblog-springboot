package com.stringcodeltd.myblogapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .authorizeHttpRequests((authorise)
                            ->authorise.anyRequest()
                            .authenticated()
                    ).httpBasic(Customizer.withDefaults());
    return http.build();
    }

//    @Bean
//    public UserDetailsService userDetails(){
//        UserDetails bloguser     = User.builder()
//                .username("mcgamma04")
//                .password(passwordEncoder().encode("mike"))
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(bloguser,admin);
//    }

    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
