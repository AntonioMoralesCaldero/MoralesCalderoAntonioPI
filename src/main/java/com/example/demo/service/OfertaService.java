// Autor: Antonio Miguel Morales Caldero
package com.example.demo.service;

import com.example.demo.entity.Oferta;
import com.example.demo.model.OfertaModel;

import java.util.List;

public interface OfertaService {
    void guardarOferta(OfertaModel ofertaModel);    
    Oferta convertirAEntidad(OfertaModel ofertaModel);
    OfertaModel convertirAModelo(Oferta oferta);
    List<Oferta> obtenerOfertasPendientes();
    Oferta obtenerOfertaPorId(int id);
    void actualizarOferta(Oferta oferta);
}
