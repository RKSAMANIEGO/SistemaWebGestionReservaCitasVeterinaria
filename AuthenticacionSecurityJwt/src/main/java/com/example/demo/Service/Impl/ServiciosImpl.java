package com.example.demo.Service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.ActualizarServRequest;
import com.example.demo.Model.CServicios;
import com.example.demo.Model.CTipoServicio;
import com.example.demo.Repository.IServiciosRepository;
import com.example.demo.Repository.ITipoServicioRepository;
import com.example.demo.Service.IServiciosService;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ServiciosImpl implements IServiciosService {

	@Autowired
	private IServiciosRepository iServiciosRepository;
	
	@Autowired
	private ITipoServicioRepository iTipoServicioRepository;

	@Override
	public List<CServicios> listarServicios() {
		return iServiciosRepository.findAll();
	}

	@Override
	public CServicios crearServicios(CServicios s) {
		
		if(!iServiciosRepository.findByNombre(s.getNombre()).isPresent()){
			CServicios servicioN=CServicios.builder()
					.nombre(s.getNombre())
					.descripcion(s.getDescripcion())
					.duracion(s.getDuracion())
					.costo(s.getCosto())
					.imagen(s.getImagen())
					.build();
			
			Optional<CTipoServicio> tipoServE=iTipoServicioRepository.findById(s.getTipoServicio().getId());
			servicioN.setTipoServicio(!tipoServE.isPresent() ? iTipoServicioRepository.save(s.getTipoServicio()): tipoServE.get());
			
			return iServiciosRepository.save(servicioN);
		}
		
		return null;
	}

	@Override
	public ActualizarServRequest actualizarServicios(CServicios s, Long id) {
		
		CServicios servicioE=iServiciosRepository.findById(id)
					.orElseThrow(() -> new EntityNotFoundException("No se Encontro el Servicio con ID "+id));
		
		servicioE.setNombre(s.getNombre() !=null ? s.getNombre() : servicioE.getNombre());
		servicioE.setDescripcion(s.getDescripcion()!=null ? s.getDescripcion(): servicioE.getDescripcion());
		servicioE.setDuracion(s.getDuracion()!=null ? s.getDuracion(): servicioE.getDuracion());
		servicioE.setCosto(s.getCosto()!=null ? s.getCosto(): servicioE.getCosto());
		servicioE.setImagen(s.getImagen()!=null ? s.getImagen():servicioE.getImagen());
		servicioE.setTipoServicio(s.getTipoServicio()!=null? s.getTipoServicio():servicioE.getTipoServicio());
		iServiciosRepository.save(servicioE);
		
		ActualizarServRequest servActualizado=ActualizarServRequest.builder()
				.id(servicioE.getId())
				.nombre(servicioE.getNombre())
				.descripcion(servicioE.getDescripcion())
				.duracion(servicioE.getDuracion())
				.costo(servicioE.getCosto())
				.imagen(servicioE.getImagen())
				.build();	
		return servActualizado;
	}

	@Override
	public CServicios desactivarServicios(Long id) {
		CServicios servicioE=iServiciosRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("No se encontro el Servicio con ID "+id));
		servicioE.setEstado(false);
		return iServiciosRepository.save(servicioE);
	}

	@Override
	public List<CServicios> buscarByIdTipo(Long id) {
		return iServiciosRepository.buscarByTipoServ(id);
	}

	
	
	
}
