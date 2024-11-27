// Autor: Antonio Miguel Morales Caldero
package com.example.demo.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.Collections;

public class TokenAuthentication extends AbstractAuthenticationToken {

    private final Object principal;

    public TokenAuthentication(Object principal) {
        super(Collections.emptyList());
        this.principal = principal;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}
