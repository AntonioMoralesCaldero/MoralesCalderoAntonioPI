// Autor: Antonio Miguel Morales Caldero
package com.example.demo.service.impl;

import com.example.demo.entity.Usuario;
import com.example.demo.model.UsuarioModel;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.UsuarioService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UsuarioModel registrar(UsuarioModel usuarioModel) {
        Usuario usuario = convertirModeloAEntidad(usuarioModel);
        usuario.setPassword(passwordEncoder.encode(usuarioModel.getPassword()));
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return convertirEntidadAModelo(savedUsuario);
    }

    @Override
    public boolean authenticate(String username, String password) {
        Usuario usuario = usuarioRepository.findByUsername(username);
        return usuario != null && passwordEncoder.matches(password, usuario.getPassword());
    }

    @Override
    public List<UsuarioModel> findAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioModel> usuarioModels = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            usuarioModels.add(convertirEntidadAModelo(usuario));
        }
        return usuarioModels;
    }

    @Override
    public UsuarioModel findById(int id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        return (usuario != null) ? convertirEntidadAModelo(usuario) : null;
    }

    @Override
    public Usuario findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }
    
    @Override
    public Usuario findUsuarioById(int id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public UsuarioModel login(String username, String password) {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario != null && passwordEncoder.matches(password, usuario.getPassword())) {
            return convertirEntidadAModelo(usuario);
        }
        return null;
    }

    private UsuarioModel convertirEntidadAModelo(Usuario usuario) {
        UsuarioModel model = new UsuarioModel();
        model.setId(usuario.getId());
        model.setNombre(usuario.getNombre());
        model.setApellidos(usuario.getApellidos());
        model.setfecha_nacimiento(usuario.fecha_nacimiento());
        model.setDireccion(usuario.getDireccion());
        model.setUsername(usuario.getUsername());
        model.setPassword(usuario.getPassword());
        model.setActive(usuario.isActive());
        return model;
    }

    private Usuario convertirModeloAEntidad(UsuarioModel usuarioModel) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioModel.getId());
        usuario.setNombre(usuarioModel.getNombre());
        usuario.setApellidos(usuarioModel.getApellidos());
        usuario.setfecha_nacimiento(usuarioModel.fecha_nacimiento());
        usuario.setDireccion(usuarioModel.getDireccion());
        usuario.setUsername(usuarioModel.getUsername());
        usuario.setPassword(usuarioModel.getPassword());
        usuario.setActive(usuarioModel.isActive());
        return usuario;
    }
}
