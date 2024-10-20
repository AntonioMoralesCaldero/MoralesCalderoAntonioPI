//Autor: Antonio Miguel Morales Caldero
package com.example.demo.service;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Usuario;
import com.example.demo.entity.Administrador;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.repository.AdministradorRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private AdministradorRepository administradorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario != null) {
            return new CustomUserDetails(usuario.getUsername(), usuario.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_USUARIO")), usuario.getId());
        }

        Administrador administrador = administradorRepository.findByUsername(username);
        if (administrador != null) {
            return new CustomUserDetails(administrador.getUsername(), administrador.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")), administrador.getId());
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
