// Autor: Antonio Miguel Morales Caldero
package com.example.demo.repository;

import com.example.demo.entity.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompraRepository extends JpaRepository<Compra, Integer> {
    List<Compra> findByUsuarioId(int usuarioId);
}
