package com.example.demo.Service;

import java.util.List;

import com.example.demo.Model.CVeterinario;

public interface IVeterinarioService {

	List<CVeterinario> listarVeterinario();
	CVeterinario guardarVeterinario(CVeterinario v);
	CVeterinario actualizarVeterinario(CVeterinario v,Long idV);
	CVeterinario desactivarVeterinario(Long id);

}
