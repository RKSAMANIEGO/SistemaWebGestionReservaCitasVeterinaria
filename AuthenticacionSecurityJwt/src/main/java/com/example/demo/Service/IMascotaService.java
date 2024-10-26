package com.example.demo.Service;

import java.util.List;

import com.example.demo.Dto.CreateMascotaDto;
import com.example.demo.Model.CCliente;
import com.example.demo.Model.CMascota;

public interface IMascotaService {
	
	List<CMascota> listarMascotasActivas();
	List<CMascota> listarMascotasInactivas();
	CMascota guardarMascota(CreateMascotaDto m, CCliente c);
	CMascota actualizarMascota(CMascota m , Long id_Cliente);
	CMascota desactivarMascota(Long id);
	List<CMascota> findByNombreRazaSexo(Integer estado,String valor);
	List<CMascota> findMascotaByNombreCliente(Integer estado, String nombre);
}
