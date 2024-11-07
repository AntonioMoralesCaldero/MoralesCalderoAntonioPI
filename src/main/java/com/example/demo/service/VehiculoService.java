//Autor: Antonio Miguel Morales Caldero
package com.example.demo.service;

import com.example.demo.entity.Vehiculo;
import com.example.demo.model.VehiculoModel;

import java.util.List;

public interface VehiculoService {
    List<VehiculoModel> getAllVehiculos();
	VehiculoModel getVehiculoById(int id);
    VehiculoModel saveVehiculo(VehiculoModel vehiculoModel);
    List<VehiculoModel> getCochesByUsuarioId(int usuarioId); 

}
