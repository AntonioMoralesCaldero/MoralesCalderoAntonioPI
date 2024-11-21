package com.example.demo.repository;

import com.example.demo.entity.Cita;
import com.example.demo.entity.Vehiculo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer> {
	
    List<Cita> findByUsuarioId(int usuarioId);
    List<Cita> findByEstado(String estado);
    List<Cita> findByVehiculoOcasion(Vehiculo vehiculo);

}
