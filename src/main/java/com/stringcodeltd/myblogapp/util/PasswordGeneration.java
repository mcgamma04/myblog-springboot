package com.stringcodeltd.myblogapp.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

public class PasswordGeneration {
    public static void main(String[] args) {
//        System.out.println(passwordEncoder().encode("admin"));
//        System.out.println(passwordEncoder().encode("best123"));
        System.out.println(getTokenFromRequest("Bearer hjshjjdhkjdkjkjdfkjdkfjdfjkdjkfjkfkj"));

    }

    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private static String getTokenFromRequest(String request) {
//        String bearerToken =  request.getHeader("Authorization");
        if (request.startsWith("Bearer ")) {
            return request.substring("Bearer ".length());

        }

        return null;

    }
}