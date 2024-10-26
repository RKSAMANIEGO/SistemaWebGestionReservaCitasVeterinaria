package com.example.demo.Service.Impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.CCitas;
import com.example.demo.Model.CDetalleHistoriaClinica;
import com.example.demo.Model.CRecetas;
import com.example.demo.Repository.ICitasRepository;
import com.example.demo.Repository.IDetalleHcRepository;
import com.example.demo.Repository.IRecetasRepository;
import com.example.demo.Service.IRecetasService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RecetasImpl implements IRecetasService {

	@Autowired
	private IRecetasRepository recetasR;
	
	@Autowired
	private IDetalleHcRepository detallehistoriaClinicaR;
	
	@Autowired
	private ICitasRepository citasR;
	
	@Override
	public List<CRecetas> listarRecetas() {
		return recetasR.findAll();
	}

	@Override
	public CRecetas guardarRecetas(CRecetas r) {
		
		CRecetas recetaN=CRecetas.builder()
				.medicamentos(r.getMedicamentos())
				.descripcion(r.getDescripcion())
				.fecha(LocalDate.now())
				.build();
		
		CCitas citaE=citasR.findById(r.getCita().getId())
				.orElseThrow( () -> new EntityNotFoundException("Cita no Existe¡..."));
		recetaN.setCita(citaE);
		
		CDetalleHistoriaClinica detalleHistoriaClinicaE=detallehistoriaClinicaR.findById(r.getDetalleHistoriaClinica().getId())
				.orElseThrow(() -> new EntityNotFoundException("Detalle Historia Clinica No Existe¡..."));
		recetaN.setDetalleHistoriaClinica(detalleHistoriaClinicaE);

		return recetasR.save(recetaN);
	}

	@Override
	public CRecetas actualizarRecetas(CRecetas r, Long id) {
		
		CRecetas recetaE=recetasR.findById(id).orElseThrow( ()-> new EntityNotFoundException("Receta No Existe¡..."));
		
		recetaE.setMedicamentos(r.getMedicamentos()!=null ? r.getMedicamentos() : recetaE.getMedicamentos());
		recetaE.setDescripcion (r.getDescripcion() !=null ? r.getDescripcion()  : recetaE.getDescripcion());
		
		if(r.getCita()!=null) {
			CCitas citaE=citasR.findById(r.getCita().getId())
					.orElseThrow( () -> new EntityNotFoundException("Cita no Existe¡..."));
			recetaE.setCita(citaE);		
		}
		if(r.getDetalleHistoriaClinica()!=null){
			CDetalleHistoriaClinica detalleHistoriaClinicaE=detallehistoriaClinicaR.findById(r.getDetalleHistoriaClinica().getId())
					.orElseThrow(() -> new EntityNotFoundException("Detalle Historia Clinica No Existe¡..."));
			recetaE.setDetalleHistoriaClinica(detalleHistoriaClinicaE);
		}
		return recetasR.save(recetaE);
	}

}
