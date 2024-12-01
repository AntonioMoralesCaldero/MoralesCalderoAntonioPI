//Autor: Antonio Miguel Morales Caldero
package com.example.demo.repository;

import com.example.demo.entity.Vehiculo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface VehiculoRepository extends JpaRepository<Vehiculo, Integer> {
    List<Vehiculo> findByVendidoFalse();
}
