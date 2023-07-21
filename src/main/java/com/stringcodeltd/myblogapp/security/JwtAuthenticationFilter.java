package com.stringcodeltd.myblogapp.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtTokenProvider jwtTokenProvider;
    private UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, UserDetailsService userDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        //get JWT token from HTTP request
String token = getTokenFromRequest(request);

//validate Token
        if(StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)){
           //get username from token
            String username = jwtTokenProvider.getUsername(token);
            //Load the user associated with token

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            //create an instqnce of username and password
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);

    }


    private  String getTokenFromRequest(HttpServletRequest request){
        String bearerToken =  request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken)&& bearerToken.startsWith("Bearer ")){
         return bearerToken.substring("Bearer ".length());
        }
 return null;
    }

}
