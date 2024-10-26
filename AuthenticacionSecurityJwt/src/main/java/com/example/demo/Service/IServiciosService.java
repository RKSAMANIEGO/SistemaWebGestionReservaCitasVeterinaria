package com.example.demo.Service;

import java.util.List;

import com.example.demo.Dto.ActualizarServRequest;
import com.example.demo.Model.CServicios;

public interface IServiciosService {
	
	List<CServicios> listarServicios();
	CServicios crearServicios(CServicios s);
	ActualizarServRequest actualizarServicios(CServicios s, Long id);
	CServicios desactivarServicios(Long id);
	List<CServicios> buscarByIdTipo(Long id);

}
