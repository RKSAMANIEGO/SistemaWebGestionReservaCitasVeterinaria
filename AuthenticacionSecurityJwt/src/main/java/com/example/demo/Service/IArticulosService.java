package com.example.demo.Service;

import java.util.List;

import com.example.demo.Model.CArticulo;

public interface IArticulosService {
	
	List<CArticulo> listarArticulo();
	CArticulo  guardarArticulo(CArticulo a);
	CArticulo actualizarArticulo(CArticulo a, Long id);
	String desactivar(Long id);

}
