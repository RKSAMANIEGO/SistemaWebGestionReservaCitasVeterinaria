package com.example.demo.Service;

import java.util.List;

import com.example.demo.Model.CDetalleHistoriaClinica;

public interface IDetalleHcService {
	
	List<CDetalleHistoriaClinica> listarDetalleHC();
	CDetalleHistoriaClinica guardarDetalleHc(CDetalleHistoriaClinica detalleHc);
	CDetalleHistoriaClinica actualizarDetalleHc(CDetalleHistoriaClinica detalleHc, Long id);

}
