package com.example.demo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.Model.CArticulo;
import com.example.demo.Model.CCliente;
import com.example.demo.Model.CDetalleHistoriaClinica;
import com.example.demo.Model.CHistoriasClinicas;
import com.example.demo.Model.CMascota;
import com.example.demo.Model.CPermission;
import com.example.demo.Model.CRol;
import com.example.demo.Model.CServicios;
import com.example.demo.Model.CTipoServicio;
import com.example.demo.Model.CUser;
import com.example.demo.Model.CVeterinario;
import com.example.demo.Model.Enum.EnumSexo;
import com.example.demo.Model.Enum.EnumTipo;
import com.example.demo.Repository.IArticuloRepository;
import com.example.demo.Repository.IClientesRepository;
import com.example.demo.Repository.IDetalleHcRepository;
import com.example.demo.Repository.IHistoriaClinicaRepository;
import com.example.demo.Repository.IMascotasRepository;
import com.example.demo.Repository.IPermissionRepository;
import com.example.demo.Repository.IRoleRepository;
import com.example.demo.Repository.ITipoServicioRepository;
import com.example.demo.Repository.IServiciosRepository;
import com.example.demo.Repository.IUserRepository;
import com.example.demo.Repository.IVeterinarioRepository;


@SpringBootApplication
public class AuthenticacionSecurityJwtApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticacionSecurityJwtApplication.class, args);
	}

	@Autowired
	private IUserRepository iUserRepository;

	@Autowired
	private IRoleRepository iRoleRepository;
	
	@Autowired
	private IPermissionRepository iPermissionRepository;

	@Autowired
	private PasswordEncoder pass;

	@Autowired
	private IClientesRepository clientesRepository;
	
	@Autowired
	private ITipoServicioRepository iTipoServicioRepository;
	
	@Autowired
    private IServiciosRepository iServiciosRepository;
	
	@Autowired
	private IVeterinarioRepository iVeterinarioRepository;
	
	@Autowired
	private IArticuloRepository articuloRepository;
	
	@Autowired
	private IMascotasRepository iMascotasRepository;
	
	@Autowired
	private IHistoriaClinicaRepository iHcRepository;
	
	@Autowired
	private IDetalleHcRepository detalleHcRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
   //------------------GUARDANDO 3 REGISTROS EN LA TABLA ARTICULO----------------		
		
		CArticulo articulo1=CArticulo.builder()
				.nombre( "Collares y correas")	
				.descripcion(" Para paseos o control de las mascotas.")
				.tipo(EnumTipo.ACCESORIOS)
				.precio(new BigDecimal("10.00"))
				.stock(25)
				.imagen("collares.png")
				.build();
		CArticulo articulo2=CArticulo.builder()
				.nombre( "Antiparasitarios")	
				.descripcion( "Para prevenir y tratar infestaciones de pulgas, garrapatas y otros parásitos externos e internos")
				.tipo(EnumTipo.MEDICAMENTOS)
				.precio(new BigDecimal("20.00"))
				.stock(50)
				.imagen("Antiparasitarios.png")
				.build();
		CArticulo articulo3=CArticulo.builder()
				.nombre( "Antibióticos")
				.descripcion( "Usados en el tratamiento de infecciones bacterianas")
				.tipo(EnumTipo.MEDICAMENTOS)
				.precio(new BigDecimal("20.00"))
				.stock(52)
				.imagen("Antibióticos.png")
				.build();
		
		articuloRepository.saveAll(List.of(articulo1,articulo2,articulo3));
		
   //------------------GUARDANDO 8 REGISTROS EN LA TABLA TIPO_SERVICIOS----------------	
		CTipoServicio t1=CTipoServicio.builder()
				.nombre("Consultas Generales y Diagnostico")
				.build();
		CTipoServicio t2=CTipoServicio.builder()
				.nombre("Vacunación y Desparasitación")
				.build();
		CTipoServicio t3=CTipoServicio.builder()
				.nombre("Cirugía Veterinaria")
				.build();
		CTipoServicio t4=CTipoServicio.builder()
				.nombre("Medicina Preventiva")
				.build();
		CTipoServicio t5=CTipoServicio.builder()
				.nombre("Laboratorio Clínico")
				.build();
		CTipoServicio t6=CTipoServicio.builder()
				.nombre("Peluquería y Estética")
				.build();
		CTipoServicio t7=CTipoServicio.builder()
				.nombre("Reproducción y Obstetricia")
				.build();
		CTipoServicio t8=CTipoServicio.builder()
				.nombre("Servicio de Emergencias 24/7")
				.build();
		
		iTipoServicioRepository.saveAll(List.of(t1,t2,t3,t4,t5,t6,t7,t8));
		
		
     //------------------GUARDANDO 3 REGISTROS EN LA TABLA  SERVICIO----------------	
		CServicios s1=CServicios.builder()
				.nombre("Examen físico completo")
				.descripcion("Revisión del estado general de la mascota")
				.duracion(60)
				.costo(new BigDecimal("80.00"))
				.imagen("examFisico.png")
				.tipoServicio(t1)
				.build();
		CServicios s12=CServicios.builder()
				.nombre("Diagnóstico de enfermedades comunes")
				.descripcion("Identificación de síntomas y condiciones comunes como infecciones, alergias o problemas digestivos")
				.duracion(60)
				.costo(new BigDecimal("60.00"))
				.imagen("diagnostico.png")
				.tipoServicio(t1)
				.build();
		CServicios s13=CServicios.builder()
				.nombre("Seguimiento médico")
				.descripcion("Control periódico de enfermedades crónicas como diabetes o artritis")
				.duracion(20)
				.costo(new BigDecimal("30.00"))
				.imagen("seguimiento.png")
				.tipoServicio(t1)
				.build();
		
		iServiciosRepository.saveAll(List.of(s1,s12,s13));
 	
		
		//------------------GUARDANDO 5 REGISTROS DE LA TABLA PERMISOS----------------
		CPermission pReader= CPermission.builder()
				.name("READ")
				.build();
		CPermission pCreate= CPermission.builder()
				.name("CREATE")
				.build();
				
		CPermission pUpdate= CPermission.builder()
				.name("UPDATE")
				.build();
		CPermission pDelete= CPermission.builder()
				.name("DELETE")
				.build();
		CPermission pRefactor= CPermission.builder()
				.name("REFACTOR")
				.build();
		
		//if(iPermissionRepository.findAll()==null) {
		iPermissionRepository.saveAll(List.of(pReader, pCreate ,pUpdate , pDelete, pRefactor));
		//}
		
		
		//------------------GUARDANDO 3 REGISTROS DE LA TABLA ROLES----------------
		CRol rAdmin = CRol.builder()
				.nameRole("ADMINISTRADOR")
				.permission(Set.of(pReader,pCreate,pUpdate,pDelete,pRefactor))
				.build();
		CRol rUser = CRol.builder()
				.nameRole("USUARIO")
				.permission(Set.of(pReader,pCreate,pUpdate,pDelete))
				.build();
		CRol rVeterinario = CRol.builder()
				.nameRole("VETERINARIO")
				.permission(Set.of(pReader,pCreate,pUpdate,pDelete))
				.build();
		
		//if(iRoleRepository.findAll()==null) {
			iRoleRepository.saveAll(List.of(rAdmin, rUser, rVeterinario));
		//}
		
	
		//------------------GUARDANDO 4 REGISTROS DE LA TABLA USUARIOS----------------
		CUser uAdmin = CUser.builder()
				.name("enrike rod keler")
				.lastname("samaniego")
				.email("enrike@gmail.com")
				.username("enrike")
				.password(pass.encode("enrike123"))
				.roles(Set.of(rAdmin)).build();
		
		CUser uUserFlor = CUser.builder()
				.name("flor maria")
				.lastname("rodriguez")
				.email("flor@gmail.com")
				.username("flor")
				.password(pass.encode("flor123"))
				.roles(Set.of(rUser)).build();
				
		CUser uVeterinario = CUser.builder()
				.name("nahomi jessica")
				.lastname("guzman")
				.email("nahomi@gmail.com")
				.username("nahomi")
				.password(pass.encode("nahomi123"))
	
				.roles(Set.of(rVeterinario))
				.build();
		
		CUser uUserKarla = CUser.builder()
				.name("karla")
				.lastname("chavez")
				.email("karla@gmail.com")
				.username("karlax")
				.password(pass.encode("karla123"))
				.roles(Set.of(rUser)).build();
		//if(iUserRepository.findAll()==null) {
			iUserRepository.saveAll(List.of(uAdmin, uUserFlor, uVeterinario,uUserKarla));
		//}
        //------------------GUARDANDO 1 REGISTROS EN LA TABLA VETERINARIO----------------	
			CVeterinario vet1=CVeterinario.builder()
					.nombres("pedro")
					.documento("74521368")
					.especialidad("cirujano")
					.telefono("951752846")
					.email("pedroj@gmail.com")
					.fechaRegistro(LocalDate.now())
					.imagen(null)
					.usuario(uVeterinario)
					.fechaRegistro(null)
					.build();
			iVeterinarioRepository.save(vet1);
			
			
		// ------------------GUARDANDO 2 REGISTROS DE LA TABLA CLIENTE----------------

		CCliente cliente1 = CCliente.builder()
				.nombre("flor maria")
				.documento("76754123")
				.email("flor@gmail.com")
				.telefono("916912549")
				.direccion("santa anita")
				.fechaRegistro(LocalDate.now())
				.imagen("flor.jpg")
				.usuario(uUserFlor).build();
		CCliente cliente2 = CCliente.builder()
				.nombre("karla chavez")
				.documento("75782632")
				.email("karla@gmail.com")
				.telefono("985746523")
				.direccion("ate")
				.fechaRegistro(LocalDate.now())
				.imagen("karla.png")
				.usuario(uUserKarla).build();

		clientesRepository.saveAll(List.of(cliente1, cliente2));
			
	
		
		   //------------------GUARDANDO 3 REGISTROS DE LA TABLA MASCOTAS----------------	
		
		   CMascota mascota1=CMascota.builder()
				   .nombre("Boby")
				   .raza("Bulldog")
				   .edad(3)
				   .sexo(EnumSexo.M)
				   .fechaRegistro(LocalDate.now())
				   .imagen("boby.png")
				   .cliente(cliente1)
				   .build();
		   
		   CMascota mascota2=CMascota.builder()
				   .nombre("Lazy")
				   .raza("pastor Aleman")
				   .edad(8)
				   .sexo(EnumSexo.F)
				   .fechaRegistro(LocalDate.now())
				   .imagen("lazy.png")
				   .cliente(cliente1)
				   .build();
		   CMascota mascota3=CMascota.builder()
				   .nombre("cuto")
				   .raza("rottweiler")
				   .edad(7)
				   .sexo(EnumSexo.M)
				   .fechaRegistro(LocalDate.now())
				   .imagen("cutto.png")
				   .cliente(cliente2)
				   .build();
		   
		 //  if(mascotasRepository.findAll()==null) {
		   iMascotasRepository.saveAll(List.of(mascota1,mascota2,mascota3));
		 //  }
		   //------------------GUARDANDO 2 REGISTROS EN LA TABLA HISTORIA CL.----------------		
		   
		  CHistoriasClinicas hCBoby=CHistoriasClinicas.builder()
				  .fecha(LocalDate.now())
				  .observaciones("Paciente leve")
				  .mascota(mascota1)
				  .build(); 
		  
		  CHistoriasClinicas hClazy=CHistoriasClinicas.builder()
				  .fecha(LocalDate.now())
				  .observaciones("Paciente Moderado")
				  .mascota(mascota2)
				  .build(); 
		
		  iHcRepository.saveAll(List.of(hCBoby,hClazy));
		  
		  //------------------GUARDANDO 2 REGISTROS EN LA TABLA DETALLE H.C----------------	
		  
		  CDetalleHistoriaClinica detalleClinico1=CDetalleHistoriaClinica.builder()
				  .fecha(LocalDate.now())
				  .motivo("Revisión de rutina")
				  .diagnostico("Sano")
				  .tratamiento("Ninguno")
				  .observaciones("Ninguna")
				  .historiaClinica(hCBoby)
				  .build();
		  CDetalleHistoriaClinica detalleClinico2=CDetalleHistoriaClinica.builder()
				  .fecha(LocalDate.now())
				  .motivo("Infección en la piel")
				  .diagnostico("Dermatitis")
				  .tratamiento("Antibiótico 10d")
				  .observaciones("Mejorando")
				  .historiaClinica(hCBoby)
				  .build();
		  
		  detalleHcRepository.saveAll(List.of(detalleClinico1,detalleClinico2));
		 
		  //------------------GUARDANDO 2 REGISTROS EN LA TABLA RECETAS----------------	
		  
		  /*
		  CRecetas receta1=CRecetas.builder()
				  .medicamentos("Antibiótico x 500mg")
				  .descripcion("-Cada 12hrs por 10 dias y Administra con Comida")
				  .detalleHistoriaClinica(detalleClinico2)
				  .build();
		  
		  CRecetas receta2=CRecetas.builder()
				  .medicamentos("Crema tópica Y Aplicar")
				  .descripcion("-Cada 8hrs por 7 dias y Aplicar en zonas Afectadas")
				  .detalleHistoriaClinica(detalleClinico2)
				  .build();
		 */
		  
		  
	}

}
