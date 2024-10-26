package com.example.demo.Service;

import java.util.List;

import com.example.demo.Dto.VentaGuardarResponse;
import com.example.demo.Model.CVentas;

public interface IVentasService {
	
	List<CVentas> listarVentas();
	VentaGuardarResponse guardarVentas(CVentas v);
	CVentas actualizarVentas(CVentas v, Long id);
	String cancelarVentas(Long id);

}
