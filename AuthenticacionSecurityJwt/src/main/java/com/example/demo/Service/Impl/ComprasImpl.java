package com.example.demo.Service.Impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.CArticulo;
import com.example.demo.Model.CCompras;
import com.example.demo.Repository.IArticuloRepository;
import com.example.demo.Repository.IComprasRepository;
import com.example.demo.Service.IComprasService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ComprasImpl implements IComprasService{

	@Autowired
	private IComprasRepository comprasRepository;
	
	@Autowired
	private IArticuloRepository articuloRepository;
	
	@Override
	public List<CCompras> listarCompras() {
		return comprasRepository.findAll();
	}

	@Override
	public CCompras guardarCompras(CCompras compras) {
			
		CCompras compraN=CCompras.builder()
				.cantidad(compras.getCantidad())
				.fecha(LocalDate.now())
				.precio(compras.getPrecio())
				.total(compras.getPrecio().multiply(new BigDecimal(compras.getCantidad())))
				.proveedor(compras.getProveedor())
				.build();
			
		Optional<CArticulo> articuloE=articuloRepository.findById(compras.getArticulo().getId());
		
	    if (articuloE.isPresent()) {
	        CArticulo articulo = articuloE.get();
	        articulo.setStock(articulo.getStock() + compras.getCantidad());
	        compraN.setArticulo(articulo); 
	    } else {
	    	compras.getArticulo().setStock(compras.getCantidad());
	    	CArticulo nuevoArticulo = articuloRepository.save(compras.getArticulo()); 
	        compraN.setArticulo(nuevoArticulo);
	    }
		
		return comprasRepository.save(compraN);
	}

	@Override
	public CCompras actualizarCompras(CCompras c, Long id) {
		
		CCompras compraE= comprasRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("La compra con Id "+ id+"no Existe"));
		int cantidadActual=compraE.getCantidad();
	    compraE.setCantidad (c.getCantidad() !=null ? c.getCantidad() : compraE.getCantidad());
		compraE.setPrecio   (c.getPrecio()   !=null ? c.getPrecio()   : compraE.getPrecio());
		
		if(c.getCantidad()!=null || c.getPrecio()!=null) {
		compraE.setTotal(compraE.getPrecio().multiply(new BigDecimal(compraE.getCantidad())));
		}
		
		compraE.setProveedor(c.getProveedor()!=null ? c.getProveedor(): compraE.getProveedor());
		
		if(c.getArticulo()!=null && c.getArticulo().getId().equals(compraE.getArticulo().getId())) {
			CArticulo articuloE=articuloRepository.findById(compraE.getArticulo().getId()).get();
			articuloE.setNombre     (c.getArticulo().getNombre()     !=null ? c.getArticulo().getNombre()     :articuloE.getNombre());
			articuloE.setDescripcion(c.getArticulo().getDescripcion()!=null ? c.getArticulo().getDescripcion():articuloE.getDescripcion());
			articuloE.setTipo       (c.getArticulo().getTipo()       !=null ? c.getArticulo().getTipo()       :articuloE.getTipo());
			articuloE.setPrecio     (c.getArticulo().getPrecio()     !=null ? c.getArticulo().getPrecio()     :articuloE.getPrecio());
			articuloE.setStock      ((articuloE.getStock()-cantidadActual)+ compraE.getCantidad());  //AL ACTUALIZAR NO SE ENVIA EL STOCK , CONFIGURADO...
			articuloE.setImagen     (c.getArticulo().getImagen()     !=null ? c.getArticulo().getImagen()     :articuloE.getImagen());
			
			articuloRepository.save(articuloE);
			compraE.setArticulo(articuloE);
		}
		if(c.getArticulo()!=null && !c.getArticulo().getId().equals(compraE.getArticulo().getId())) {
			CArticulo articuloE=articuloRepository.findById(c.getArticulo().getId()).get();
			articuloE.setNombre     (c.getArticulo().getNombre()     !=null ? c.getArticulo().getNombre()     :articuloE.getNombre());
			articuloE.setDescripcion(c.getArticulo().getDescripcion()!=null ? c.getArticulo().getDescripcion():articuloE.getDescripcion());
			articuloE.setTipo       (c.getArticulo().getTipo()       !=null ? c.getArticulo().getTipo()       :articuloE.getTipo());
			articuloE.setPrecio     (c.getArticulo().getPrecio()     !=null ? c.getArticulo().getPrecio()     :articuloE.getPrecio());
			articuloE.setStock      (articuloE.getStock()+ compraE.getCantidad());  //AL ACTUALIZAR NO SE ENVIA EL STOCK , CONFIGURADO...
			articuloE.setImagen     (c.getArticulo().getImagen()     !=null ? c.getArticulo().getImagen()     :articuloE.getImagen());
			articuloRepository.save(articuloE);
			
			//devolviendo el stock que tenia anteriormente el articulo vinculado con el id de la compra
			CArticulo articuloActual=compraE.getArticulo();
			articuloActual.setStock(articuloActual.getStock()-cantidadActual);
			articuloRepository.save(articuloActual);
			
			compraE.setArticulo(articuloE);
			
		}
		
		if(c.getArticulo()==null) {
			CArticulo articuloE=articuloRepository.findById(compraE.getArticulo().getId()).get();
			articuloE.setStock      ((articuloE.getStock()-cantidadActual)+ compraE.getCantidad());
			articuloRepository.save(articuloE);
			compraE.setArticulo(articuloE);
		}

		return comprasRepository.save(compraE);
	}

	@Override
	public String eliminarCompras(Long id) {
		CCompras compraE=comprasRepository.findById(id)
				.orElseThrow(()-> new EntityNotFoundException("Articulo no EXISTE"));
		
		CArticulo articuloE= compraE.getArticulo();
		articuloE.setStock(articuloE.getStock() - compraE.getCantidad());
		articuloRepository.save(articuloE);
		comprasRepository.delete(compraE);
		return "La compra del articulo "+compraE.getArticulo().getNombre()+" fue Eliminado.";
	}

	
	
}
