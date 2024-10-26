package com.example.demo.Service.Impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.CDetalleHistoriaClinica;
import com.example.demo.Model.CHistoriasClinicas;
import com.example.demo.Repository.IDetalleHcRepository;
import com.example.demo.Repository.IHistoriaClinicaRepository;
import com.example.demo.Service.IDetalleHcService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DetalleHcImpl implements IDetalleHcService {

	@Autowired
	private IDetalleHcRepository detalleHcRepository;
	
	@Autowired
	private IHistoriaClinicaRepository historiaClinicaRepository;
	
	@Override
	public List<CDetalleHistoriaClinica> listarDetalleHC() {
		return detalleHcRepository.findAll();
	}

	@Override
	public CDetalleHistoriaClinica guardarDetalleHc(CDetalleHistoriaClinica detalleHc) {
			
		CDetalleHistoriaClinica detalleHcN= CDetalleHistoriaClinica.builder()
				.fecha(LocalDate.now())
				.motivo(detalleHc.getMotivo())
				.diagnostico(detalleHc.getDiagnostico())
				.tratamiento(detalleHc.getTratamiento())
				.observaciones(detalleHc.getObservaciones())
				.build();
		
		CHistoriasClinicas historiaClinicaE=historiaClinicaRepository.findById(detalleHc.getHistoriaClinica().getId())
				.orElseThrow( ()-> new EntityNotFoundException("Historia Clinica no Existe¡..."));
		
		detalleHcN.setHistoriaClinica(historiaClinicaE);
		
		return detalleHcRepository.save(detalleHcN);
	}

	@Override
	public CDetalleHistoriaClinica actualizarDetalleHc(CDetalleHistoriaClinica detalleHc, Long id) {
	
		
		CDetalleHistoriaClinica detalleHistoriaClinicaE=detalleHcRepository.findById(id)
				.orElseThrow( ()-> new EntityNotFoundException("Detalle Historia Clinica no Exite¡..."));
		
		detalleHistoriaClinicaE.setMotivo(detalleHc.getMotivo()!=null ? detalleHc.getMotivo() :detalleHistoriaClinicaE.getMotivo());
		detalleHistoriaClinicaE.setDiagnostico(detalleHc.getDiagnostico()!=null ? detalleHc.getDiagnostico():detalleHistoriaClinicaE.getDiagnostico());
		detalleHistoriaClinicaE.setTratamiento(detalleHc.getTratamiento()!=null ? detalleHc.getTratamiento():detalleHistoriaClinicaE.getTratamiento());
		detalleHistoriaClinicaE.setObservaciones(detalleHc.getObservaciones()!=null? detalleHc.getObservaciones():detalleHistoriaClinicaE.getObservaciones());
		
		   if(detalleHc.getHistoriaClinica()!=null) {
			CHistoriasClinicas historiaClinicaE=historiaClinicaRepository.findById(detalleHc.getHistoriaClinica().getId())
					.orElseThrow( () -> new EntityNotFoundException("Historia Clinica No Existe¡..."));
		detalleHistoriaClinicaE.setHistoriaClinica(historiaClinicaE);
		   }
		
		return detalleHcRepository.save(detalleHistoriaClinicaE);
	}

	
}
