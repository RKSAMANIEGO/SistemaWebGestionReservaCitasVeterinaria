package com.example.demo.Service;

import java.util.List;

import com.example.demo.Model.CTipoServicio;

public interface ITipoServicio {
	
	List<CTipoServicio> listarTipoServicio();
	CTipoServicio guardarTipoServicio(CTipoServicio tps);
	CTipoServicio actualizarTipoServicio(CTipoServicio ts,Long id);
	CTipoServicio desactivarTipoServicio(Long id);

}
