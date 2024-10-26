package com.example.demo.Service;

import java.util.List;

import com.example.demo.Model.CRecetas;

public interface IRecetasService {
	
	List<CRecetas> listarRecetas();
	CRecetas guardarRecetas(CRecetas r);
	CRecetas actualizarRecetas(CRecetas r, Long id);

}
