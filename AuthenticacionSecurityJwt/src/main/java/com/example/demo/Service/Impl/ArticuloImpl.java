package com.example.demo.Service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.CArticulo;
import com.example.demo.Repository.IArticuloRepository;
import com.example.demo.Service.IArticulosService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ArticuloImpl implements IArticulosService {

	@Autowired
	private IArticuloRepository aR;
	
	@Override
	public List<CArticulo> listarArticulo() {
		return aR.findAll();
	}

	@Override
	public CArticulo guardarArticulo(CArticulo a) {
		   if (a == null) {
		        throw new IllegalArgumentException("El artículo no puede ser nulo");
		    }

		   if (a.getNombre() == null) {
		        throw new IllegalArgumentException("El nombre del artículo no puede ser nulo");
		    }

		    if (!aR.findByNombre(a.getNombre()).isPresent()) {
		        CArticulo articuloNew = CArticulo.builder()
		            .nombre(a.getNombre())
		            .descripcion(a.getDescripcion())
		            .tipo(a.getTipo())
		            .precio(a.getPrecio())
		            .stock(a.getStock())
		            .imagen(a.getImagen())
		            .build();
		       CArticulo aN= aR.save(articuloNew);
		        return aN;
		    }
		    
		    return null;  // o lanza una excepción si el artículo ya existe
	}

	@Override
	public CArticulo actualizarArticulo(CArticulo a, Long id) {
		CArticulo articuloE= aR.findById(id)
				.orElseThrow(()-> new EntityNotFoundException("Articulo No EXISTE¡..") );
		
		articuloE.setNombre     (a.getNombre()     !=null ? a.getNombre()     :articuloE.getNombre() );
		articuloE.setDescripcion(a.getDescripcion()!=null ? a.getDescripcion():articuloE.getDescripcion());
		articuloE.setTipo       (a.getTipo()       !=null ? a.getTipo()       :articuloE.getTipo());
		articuloE.setPrecio     (a.getPrecio()     !=null ? a.getPrecio()     :articuloE.getPrecio());
		articuloE.setStock      (a.getStock()      !=null ? a.getStock()      :articuloE.getStock());
		articuloE.setImagen     (a.getImagen()     !=null ? a.getImagen()     :articuloE.getImagen());
		
		return aR.save(articuloE);
	}

	@Override
	public String desactivar(Long id) {
		
		CArticulo articuloE= aR.findById(id)
				.orElseThrow(()-> new EntityNotFoundException("Articulo No EXISTE¡..") );
			
		articuloE.setEstado(false);aR.save(articuloE);
		return "Articulo "+articuloE.getNombre()+" Desactivado.";
	}

}
