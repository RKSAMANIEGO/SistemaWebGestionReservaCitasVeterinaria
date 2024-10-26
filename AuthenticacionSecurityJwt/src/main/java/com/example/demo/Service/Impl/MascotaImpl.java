package com.example.demo.Service.Impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.CreateMascotaDto;
import com.example.demo.Model.CCliente;
import com.example.demo.Model.CMascota;
import com.example.demo.Repository.IClientesRepository;
import com.example.demo.Repository.IMascotasRepository;
import com.example.demo.Service.IMascotaService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MascotaImpl implements IMascotaService {

	@Autowired
	private IMascotasRepository mascotasRepository;
	@Autowired
	private IClientesRepository clientesRepository;
	
	//1.- LISTAR MASCOTAS ACTIVAS GENERAL
	@Override
	public List<CMascota> listarMascotasActivas() {
		return mascotasRepository.listarMascotasActivas();
	}
	
	//2.- LISTAR MASCOTAS INACTIVAS GENERAL
	@Override
	public List<CMascota> listarMascotasInactivas() {
		return mascotasRepository.listarMascotasInactivas();
	}
	
	//3.-BUSCAR MASCOTA POR NOMBRE,RAZA,SEXO MEDIANTE EL ESTADO (ACTIVO O INACTIVO)
	@Override
	public List<CMascota> findByNombreRazaSexo(Integer estado,String valor) {
		return mascotasRepository.buscarMascotaByNombreRazaSexo(estado,valor);
	}
	
	
	
	//4.-GUARDAD MASCOTA CON LOS DATOS EN EL BODY Y ENVIANDO POR EL END POINT EL CLIENTE ASOCIADO
	@Override
	public CMascota guardarMascota(CreateMascotaDto m , CCliente c) {
	
			//EnumSexo sexoR= Enum.valueOf(EnumSexo.class ,m.sexo().toString().toUpperCase());
	
			CMascota mascotaN=CMascota.builder()
					.nombre(m.nombres())
					.raza(m.raza())
					.edad(m.edad())
					.sexo(m.sexo())
					.fechaRegistro(LocalDate.now())
					.imagen(m.imagen())
					.cliente(c)
					.build();
			return mascotasRepository.save(mascotaN);
	}

	//6.- ACTUALIZAR MASCOTA INGRESANDO LOS DATOS POR EL BODY Y RECIBIENDO EL ID DEL CLIENTE PARA VALIDAR SI LE CORRESPONDE A ESE CL Y REALIZAR LA ACTUALIZACION
	@Override
	public CMascota actualizarMascota(CMascota m, Long idCliente) {
		
		CCliente clienteE= clientesRepository.findById(idCliente).orElseThrow(() ->new  EntityNotFoundException("Cliente con Id"+idCliente +" no Existe"));
		CMascota mascotaE= mascotasRepository.findById(m.getId())
							.orElseThrow(() -> new EntityNotFoundException("Mascota con ID "+m.getId()+" NO EXISTE, Error: " ));
		
		
		if(mascotaE.getCliente().getId().equals(clienteE.getId())) {
			mascotaE.setNombre(m.getNombre()!=null ? m.getNombre() : mascotaE.getNombre());
			mascotaE.setRaza(m.getRaza()!=null ? m.getRaza(): mascotaE.getRaza() );
			mascotaE.setEdad(m.getEdad()!=null ? m.getEdad(): mascotaE.getEdad());
			mascotaE.setSexo(m.getSexo()!=null ? m.getSexo(): mascotaE.getSexo() );
			mascotaE.setImagen(m.getImagen()!=null ? m.getImagen() : mascotaE.getImagen());
			return mascotasRepository.save(mascotaE);
		}else {
			return null;
		}
		
	}

	//7.- DESACTIVAR LA MASCOTA PARA MANTENER LOS HISTORIALES CLINICOS 
	@Override
	public CMascota desactivarMascota(Long id) {
		CMascota mascotaDes= mascotasRepository.findById(id)
				.orElseThrow(()-> new EntityNotFoundException("Mascota no Existe"));
		mascotaDes.setEstado(false);
		return mascotasRepository.save(mascotaDes);
	}

	//8.- BUSCAR MASCOTA POR EL NOMBRE DE CLIENTE
	@Override
	public List<CMascota> findMascotaByNombreCliente(Integer estado, String nombre) {
		return mascotasRepository.buscarMascotaByNombreCliente(estado, nombre);
	}


	



}
