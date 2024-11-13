// Autor: Antonio Miguel Morales Caldero
package com.example.demo.service.impl;

import com.example.demo.entity.Oferta;
import com.example.demo.model.OfertaModel;
import com.example.demo.repository.OfertaRepository;
import com.example.demo.service.OfertaService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OfertaServiceImpl implements OfertaService {

    @Autowired
    private OfertaRepository ofertaRepository;

    @Override
    public void guardarOferta(OfertaModel ofertaModel) {
        Oferta oferta = convertirAEntidad(ofertaModel);
        ofertaRepository.save(oferta);
    }

    @Override
    public Oferta convertirAEntidad(OfertaModel ofertaModel) {
        Oferta oferta = new Oferta();
        oferta.setId(ofertaModel.getId());
        oferta.setModelo(ofertaModel.getModelo());
        oferta.setColor(ofertaModel.getColor());
        oferta.setPrecio(ofertaModel.getPrecio());
        oferta.setPotencia(ofertaModel.getPotencia());
        oferta.setImagen(ofertaModel.getImagen());
        oferta.setEstado(ofertaModel.getEstado());
        return oferta;
    }

    @Override
    public OfertaModel convertirAModelo(Oferta oferta) {
        OfertaModel ofertaModel = new OfertaModel();
        ofertaModel.setId(oferta.getId());
        ofertaModel.setModelo(oferta.getModelo());
        ofertaModel.setColor(oferta.getColor());
        ofertaModel.setPrecio(oferta.getPrecio());
        ofertaModel.setPotencia(oferta.getPotencia());
        ofertaModel.setImagen(oferta.getImagen());
        ofertaModel.setEstado(oferta.getEstado());
        return ofertaModel;
    }
    
    @Override
    public List<Oferta> obtenerOfertasPendientes() {
        return ofertaRepository.findAll().stream()
                               .filter(oferta -> "pendiente".equalsIgnoreCase(oferta.getEstado()))
                               .toList();
    }

    @Override
    public Oferta obtenerOfertaPorId(int id) {
        return ofertaRepository.findById(id).orElse(null);
    }

    @Override
    public void actualizarOferta(Oferta oferta) {
        ofertaRepository.save(oferta);
    }
}
