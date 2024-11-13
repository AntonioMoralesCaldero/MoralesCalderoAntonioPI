// Autor: Antonio Miguel Morales Caldero
package com.example.demo.repository;

import com.example.demo.entity.Oferta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfertaRepository extends JpaRepository<Oferta, Integer> {
}
