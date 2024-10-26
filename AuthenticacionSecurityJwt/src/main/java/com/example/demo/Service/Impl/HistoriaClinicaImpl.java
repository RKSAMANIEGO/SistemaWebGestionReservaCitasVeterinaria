package com.example.demo.Service.Impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.CHistoriasClinicas;
import com.example.demo.Model.CMascota;
import com.example.demo.Repository.IHistoriaClinicaRepository;
import com.example.demo.Repository.IMascotasRepository;
import com.example.demo.Service.IHistoriaClinicaService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class HistoriaClinicaImpl implements IHistoriaClinicaService {

	@Autowired
	private IHistoriaClinicaRepository hCRepository;
	
	@Autowired
	private IMascotasRepository mascotaR;
	
	@Override
	public List<CHistoriasClinicas> listarHC() {
		return hCRepository.findAll();
	}

	@Override
	public CHistoriasClinicas guardarHC(CHistoriasClinicas hc) {
	
		CHistoriasClinicas historiCN= CHistoriasClinicas.builder()
				.fecha(LocalDate.now())
				.observaciones(hc.getObservaciones())
				.build();
		
		CMascota mascotaE=mascotaR.findById(hc.getMascota().getId())
				.orElseThrow(()-> new EntityNotFoundException("Mascota no Existe¡..."));
		historiCN.setMascota(mascotaE);

		return hCRepository.save(historiCN);
	}

	@Override
	public CHistoriasClinicas actualizarHC(CHistoriasClinicas hc, Long id) {
		
		CHistoriasClinicas hcEncontrada= hCRepository.findById(id)
				.orElseThrow( () -> new EntityNotFoundException("Historia Clinica No Existe"));
		
		hcEncontrada.setObservaciones(hc.getObservaciones()!=null ? hc.getObservaciones() : hcEncontrada.getObservaciones());

		if(hc.getMascota()!=null) {
			CMascota mascotaE=mascotaR.findById(hc.getMascota().getId())
					.orElseThrow(() -> new EntityNotFoundException("Mascota no Encontrada¡..."));
		hcEncontrada.setMascota(mascotaE);	
		}
		return hCRepository.save(hcEncontrada);
	}

	
	
	
}
