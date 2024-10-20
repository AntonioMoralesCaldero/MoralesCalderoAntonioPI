//Autor: Antonio Miguel Morales Caldero
package com.example.demo.service;

import com.example.demo.entity.Usuario;
import com.example.demo.model.UsuarioModel;

import java.util.List;

public interface UsuarioService {
    UsuarioModel registrar(UsuarioModel usuarioModel);
    boolean authenticate(String username, String password);
    List<UsuarioModel> findAll();
//    void togglePacienteActive(int id);
    UsuarioModel findById(int id);
//    void updatePaciente(int id, PacienteModel pacienteModel);
//    void deleteById(int id);
    Usuario findByUsername(String username);
    UsuarioModel login(String username, String password);
//    List<PacienteModel> findAllOrderByGastoDesc();
//    List<PacienteModel> findAllOrderByCitasEspecialidad(int especialidadId);
}
