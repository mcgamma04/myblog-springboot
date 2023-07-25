package com.stringcodeltd.myblogapp.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordGeneration {
    public static void main(String[] args) {
        System.out.println(passwordEncoder().encode("admin"));
        System.out.println(passwordEncoder().encode("best123"));

    }

    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
