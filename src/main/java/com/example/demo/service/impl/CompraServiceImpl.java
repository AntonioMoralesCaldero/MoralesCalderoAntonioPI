// Autor: Antonio Miguel Morales Caldero
package com.example.demo.service.impl;

import com.example.demo.entity.Compra;
import com.example.demo.entity.Usuario;
import com.example.demo.entity.Vehiculo;
import com.example.demo.repository.CompraRepository;
import com.example.demo.service.CompraService;
import com.example.demo.service.UsuarioService;
import com.example.demo.service.VehiculoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraServiceImpl implements CompraService {

    private final CompraRepository compraRepository;
    private final UsuarioService usuarioService;
    private final VehiculoService vehiculoService;

    @Autowired
    public CompraServiceImpl(CompraRepository compraRepository, UsuarioService usuarioService, VehiculoService vehiculoService) {
        this.compraRepository = compraRepository;
        this.usuarioService = usuarioService;
        this.vehiculoService = vehiculoService;
    }

    @Override
    public void registrarCompra(Compra compra) {
        compraRepository.save(compra);
    }

    @Override
    public List<Compra> obtenerComprasPorUsuario(int usuarioId) {
        return compraRepository.findByUsuarioId(usuarioId);
    }
    
    @Override
    public void crearCompra(int usuarioId, int vehiculoId) {
        Usuario usuario = usuarioService.findUsuarioById(usuarioId);
        Vehiculo vehiculo = vehiculoService.findVehiculoById(vehiculoId);

        Compra compra = new Compra();
        compra.setUsuario(usuario);
        compra.setVehiculo(vehiculo);

        compraRepository.save(compra);
    }
}
