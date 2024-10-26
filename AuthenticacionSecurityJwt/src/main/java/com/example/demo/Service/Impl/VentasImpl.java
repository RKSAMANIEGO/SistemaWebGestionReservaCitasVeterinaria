package com.example.demo.Service.Impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.VentaGuardarResponse;
import com.example.demo.Model.CArticulo;
import com.example.demo.Model.CCliente;
import com.example.demo.Model.CVentas;
import com.example.demo.Repository.IArticuloRepository;
import com.example.demo.Repository.IClientesRepository;
import com.example.demo.Repository.IVentasRepository;
import com.example.demo.Service.IVentasService;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VentasImpl implements IVentasService {

	@Autowired
	private IVentasRepository iVentasRepository;
	
	@Autowired
	private IArticuloRepository articuloRepository;
	
	@Autowired
	private IClientesRepository clientesRepository;
	
	@Override
	public List<CVentas> listarVentas() {
		return iVentasRepository.findAll();
	}

	@Override
	public VentaGuardarResponse guardarVentas(CVentas v) {
		
	
			
			//VALIDANDO SI EXISTE EL ARTICULO Y CLIENTE POR SU ID
			CArticulo articuloE=articuloRepository.findById(v.getArticulo().getId())
					.orElseThrow(()-> new EntityNotFoundException("Articulo No Existe¡..") );
			CCliente clienteE=clientesRepository.findById(v.getCliente().getId())
					.orElseThrow(() -> new EntityNotFoundException("Cliente No Existe¡.."));
			
		 if(articuloE.getStock()>=3) {
			//GUARDANDO LOS DATOS A LA TABLA VENTA
			CVentas ventaNew=CVentas.builder()
					.fecha(LocalDate.now())
					.cantidad(v.getCantidad())
					.total(articuloE.getPrecio().multiply(new BigDecimal(v.getCantidad())))
					.articulo(articuloE)
					.cliente(clienteE)
					.build();
			
			//ACTUALIZANDO EL STOCK DEL ARTICULO
			articuloE.setStock(articuloE.getStock()-v.getCantidad());
			articuloRepository.save(articuloE);
			
			//CONFIRMANDO LA CREACION DE LA VENTA
			iVentasRepository.save(ventaNew);
			
			
			//CONVIRTIENDO LOS DATOS DE LA TABLA VENTA A UNA CLASE DTO
			VentaGuardarResponse ventaN= VentaGuardarResponse.builder()
					.id(ventaNew.getId())
					.fechaRegistro(ventaNew.getFecha())
					.precio(ventaNew.getArticulo().getPrecio())
					.cantidad(ventaNew.getCantidad())
					.total(ventaNew.getTotal())
					.cliente(ventaNew.getCliente().getNombre())
					.articulo(ventaNew.getArticulo().getNombre())
					.build();

			return ventaN;
		}
		
	 log.info("Articulo "+ articuloE.getNombre()+" sin STOCK¡...");	
	 return null;
	}

	//--------------------------------------ACTUALIZAR VENTA-----------------------------------------------
	@Override
	public CVentas actualizarVentas(CVentas v, Long id) {
		
		CVentas ventaEncontrada=iVentasRepository.findById(id)
				.orElseThrow(()-> new EntityNotFoundException("Venta No registrada ") );
		
		int cantidadReal=ventaEncontrada.getCantidad();
		CArticulo articuloActual=ventaEncontrada.getArticulo();
		
		if(v.getCantidad()!=null) {
			
			ventaEncontrada.setCantidad(v.getCantidad());
			
			articuloActual.setStock(articuloActual.getStock()+cantidadReal-ventaEncontrada.getCantidad());
			articuloRepository.save(articuloActual); 
			
			ventaEncontrada.setTotal(articuloActual.getPrecio().multiply(new BigDecimal(ventaEncontrada.getCantidad())));
			iVentasRepository.save(ventaEncontrada);
			}

				//VALIDANDO SI EN EL CAMPO ARTICULO HAY MODIFICACIONES EN EL  ID DEL ARTICULO
		if(v.getArticulo()!=null) {
			
		    	CArticulo articuloE=articuloRepository.findById(v.getArticulo().getId())
					.orElseThrow(()->new EntityNotFoundException("Articulo no Existe¡...") );

		    	//DEVOLVIENDO EL STOCK REAL DEL ARTICULO ACTUAL
		    	articuloActual.setStock(articuloActual.getStock()+ventaEncontrada.getCantidad()); //CONFLICTO
		    	articuloRepository.save(articuloActual);
		    	//DESCONTAR EL STOCK DEL ARTICULO A ACTUALIZAR
		    	articuloE.setStock(articuloE.getStock()-ventaEncontrada.getCantidad());
		    	articuloRepository.save(articuloE);
			
		    	ventaEncontrada.setTotal(articuloE.getPrecio().multiply(new BigDecimal(ventaEncontrada.getCantidad())));
		    	ventaEncontrada.setArticulo(articuloE);
		    }
	
		if(v.getCliente()!=null) {
		 		CCliente clienteE=clientesRepository.findById(v.getCliente().getId())
					.orElseThrow( ()-> new EntityNotFoundException("Cliente no Existe¡...") );		
		 		ventaEncontrada.setCliente(clienteE);
		    }
	return iVentasRepository.save(ventaEncontrada);
	}

	@Override
	public String cancelarVentas(Long id) {
		//VALIDANDO SI EXISTE LA VENTA
		CVentas ventaE=iVentasRepository.findById(id)
				.orElseThrow( () -> new EntityNotFoundException("Venta No Encontrada"));
		
		//REESTABLECIENDO EL STOCK DEL ARTICULO AL CANCELAR LA VENTA
		CArticulo articuloE=ventaE.getArticulo();
		articuloE.setStock(articuloE.getStock()+ ventaE.getCantidad());
		articuloRepository.save(articuloE);
		
		//ELIMINADO LA VENTA ENCONTRADA
		iVentasRepository.delete(ventaE);
		
		return "La venta de " +ventaE.getCliente().getNombre()+" fue Cancelada.";
	}

}
