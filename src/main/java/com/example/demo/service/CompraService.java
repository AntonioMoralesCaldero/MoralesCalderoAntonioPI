// Autor: Antonio Miguel Morales Caldero
package com.example.demo.service;

import com.example.demo.entity.Compra;
import java.util.List;

public interface CompraService {
    void registrarCompra(Compra compra);
    List<Compra> obtenerComprasPorUsuario(int usuarioId);
    void crearCompra(int usuarioId, int vehiculoId);

}
