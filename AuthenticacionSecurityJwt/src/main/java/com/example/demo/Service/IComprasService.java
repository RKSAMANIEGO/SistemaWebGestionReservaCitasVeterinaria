package com.example.demo.Service;

import java.util.List;

import com.example.demo.Model.CCompras;

public interface IComprasService {

	List<CCompras> listarCompras();
	CCompras guardarCompras(CCompras compras);
	CCompras actualizarCompras(CCompras c, Long id);
	String eliminarCompras(Long id);
	
}
