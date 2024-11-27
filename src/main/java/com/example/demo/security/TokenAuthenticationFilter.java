// Autor: Antonio Miguel Morales Caldero
package com.example.demo.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.stereotype.Component;

import com.example.demo.controller.AuthApiController;

import java.io.IOException;

@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final AuthApiController authApiController;

    public TokenAuthenticationFilter(AuthApiController authApiController) {
        this.authApiController = authApiController;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            Integer userId = authApiController.validateToken(token);

            if (userId != null) {
                TokenAuthentication authentication = new TokenAuthentication(userId);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}
