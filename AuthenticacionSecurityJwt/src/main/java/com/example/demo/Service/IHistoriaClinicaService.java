package com.example.demo.Service;

import java.util.List;

import com.example.demo.Model.CHistoriasClinicas;

public interface IHistoriaClinicaService {

	List<CHistoriasClinicas> listarHC();
	CHistoriasClinicas guardarHC(CHistoriasClinicas hc);
	CHistoriasClinicas actualizarHC(CHistoriasClinicas hc,Long id);

}
