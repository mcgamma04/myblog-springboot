package com.stringcodeltd.myblogapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
}
