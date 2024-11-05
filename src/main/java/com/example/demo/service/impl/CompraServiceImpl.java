package com.example.demo.service.impl;

import com.example.demo.entity.Compra;
import com.example.demo.repository.CompraRepository;
import com.example.demo.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraServiceImpl implements CompraService {

    private final CompraRepository compraRepository;

    @Autowired
    public CompraServiceImpl(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }

    @Override
    public void registrarCompra(Compra compra) {
        compraRepository.save(compra);
    }

    @Override
    public List<Compra> obtenerComprasPorUsuario(int usuarioId) {
        return compraRepository.findByUsuarioId(usuarioId);
    }
}
