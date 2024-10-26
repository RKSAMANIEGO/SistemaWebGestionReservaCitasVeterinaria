package com.example.demo.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.CServicios;
import com.example.demo.Model.CTipoServicio;
import com.example.demo.Repository.IServiciosRepository;
import com.example.demo.Repository.ITipoServicioRepository;
import com.example.demo.Service.ITipoServicio;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TipoServicioImpl implements ITipoServicio {

	@Autowired
	private ITipoServicioRepository iTipoServicioRepository;
	
	@Autowired
	private IServiciosRepository iServiciosRepository;
	
	@Override
	public List<CTipoServicio> listarTipoServicio() {
		return iTipoServicioRepository.findAll();
	}

	@Override
	public CTipoServicio guardarTipoServicio(CTipoServicio tps) {
				
		if(!iTipoServicioRepository.findByNombre(tps.getNombre()).isPresent()) {
			CTipoServicio tsN=CTipoServicio.builder()
					.nombre(tps.getNombre())
					.build();
			return iTipoServicioRepository.save(tsN);
		}
		return null;
	}

	@Override
	public CTipoServicio actualizarTipoServicio(CTipoServicio ts, Long id) {
		CTipoServicio tsE=iTipoServicioRepository.findById(id)
				.orElseThrow(() -> new  EntityNotFoundException("Tipo servicio no Existe"));
		
		tsE.setNombre(ts.getNombre()!=null ? ts.getNombre():tsE.getNombre());
		return iTipoServicioRepository.save(tsE);
	}

	@Override
	public CTipoServicio desactivarTipoServicio(Long id) {
		CTipoServicio tsE=iTipoServicioRepository.findById(id)
				.orElseThrow(() -> new  EntityNotFoundException("Tipo servicio no Existe"));
		tsE.setEstado(false);
	
		for(CServicios s: iServiciosRepository.findAll()) {
			if(s.getTipoServicio().getId().equals(id)) {
				s.setEstado(false);
				iServiciosRepository.save(s);
			}
		}
		
		return iTipoServicioRepository.save(tsE);
	}

}
