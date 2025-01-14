import React, { useContext, useEffect, useState } from 'react'
import './StyleMenuPerfil.css'
import Imagen from '../../../pictures/avatar.png'
import { useNavigate } from 'react-router-dom';
import { UserContext } from '../../../context/UserContext';

  const MenuPerfil =({ enableMenu }) => {   
    // const [userData, setUserData] = useState(null);
    const { userData, setUserData } = useContext(UserContext);
    const navigate = useNavigate();

    const [dni, setDni] = useState('');             //modificado para el dni
    const [telefono, setTelefono] = useState('');   //modificado para el dni
    const [direccion, setDireccion] = useState(''); //modificado para el dni
    const [imagen, setImagen] = useState(null);  
    
    //FETCH CONSUME API REGISTRAR CLIENTE
    const handleSubmit = async (event) => {
      event.preventDefault();

      // Valida que el DNI, teléfono y dirección no estén vacíos
      if (!dni || !telefono || !direccion) {
        alert('Por favor, complete todos los campos obligatorios');
        return;
      }

      try {
        const token = localStorage.getItem('jwt');
        const username = localStorage.getItem('username');

        if (!token || !username) {
          alert('Usuario no autenticado. Por favor, inicie sesión nuevamente.');
          return;
        }

        const formData = new FormData();
        formData.append('cliente', JSON.stringify({ documento: dni, telefono, direccion }));
        if (imagen) {
          formData.append('imagen', imagen);
        }

        const response = await fetch(`http://localhost:8092/cliente/guardar/${username}`, {
          method: 'POST',
          headers: {
            Authorization: `Bearer ${token}`,
          },
          body: formData, // Envío como FormData para incluir la imagen
        });

        if (response.status === 201) {
          const updatedData = await response.json();                                   // MODIFICADO CONTEXT
         // setUserData(updatedData);                                                    // MODIFICADO CONTEXT
         setUserData((prevData) => ({
          ...prevData,
          cliente: {
            documento: updatedData.documento,
            telefono: updatedData.telefono,
            direccion: updatedData.direccion,
          },
        }));
  
          enableMenu(); // Habilita el menú si se guarda exitosamente
          alert('Datos guardados exitosamente');
        } else {
          const errorMsg = await response.text();
          console.error('Error al guardar los datos:', response.status, errorMsg);
          alert(`Error al guardar los datos: ${errorMsg}`);
        }
      } catch (error) {
        console.error('Error en la solicitud:', error);
        alert('Ocurrió un error al guardar los datos.');
      }
    };
    
    
    //------------------------------------------------------------------------------------
    
    //FUNCIONES CONSUME API 
    useEffect(() => {
      const token = localStorage.getItem('jwt');
      const username = localStorage.getItem('username');

      if (!token || !username) {
        console.warn('Faltan datos de usuario o token. Redirigiendo al login...');
        navigate('/login'); // Redirigir si no están los datos
        return;
      }
      // FETCH CONSUME API USUARIOS FINDBYUSERNAME
      const fetchUserData = async () => {
        try {
          const response = await fetch(`http://localhost:8092/usuario/findByUsername/${username}`, {
            method: 'GET',
            headers: {
              Authorization: `Bearer ${token}`,
              'Content-Type': 'application/json',
            },
          });

          if (response.ok) {
            const data = await response.json();
            setUserData(data);
          } else {
            console.error('Error al obtener datos del usuario:', response.status);
          }
        } catch (error) {
          console.error('Error en la solicitud:', error);
        }
      };

      fetchUserData();
    }, [navigate]);

    if (!userData) {
      return <div>Cargando datos del usuario...</div>; // Muestra un mensaje de carga
    }

    return (
      <div className='Container-Menu-Perfil'>

          <section className='Container-Cuenta'>
              <h3>Mi Cuenta</h3>
              <div className='Container-Perfil-Imagen'>
               <img className='Imagen-Perfil' src={imagen ? URL.createObjectURL(imagen) : Imagen}  alt='perfil'/> 
            {/* <img className='Imagen-Perfil' src={Imagen} alt='perfil'/>  */} 
              <label htmlFor="file-upload" className="custom-label"/>
              <input id="file-upload"  className='Logo-New-Imagen' type='file'  onChange={(e) => setImagen(e.target.files[0])} />
              </div>
              
              <div className='Container-Perfil-Cuenta'>
                <div className='Container-Perfil-Campos'>
                  <p>Usuario:</p>
                  <p>Correo :</p>
                  <p>DNI:</p>
                  <p>Teléfono:</p>
                  <p>Dirección:</p>
                  
                </div>

                <div className='Container-Perfil-Datos'>
                  <p>{userData.username}</p>
                  <p>{userData.email}</p>
                  <p>{userData?.cliente?.documento || "No registrado"}</p>
                  <p>{userData?.cliente?.telefono || "No registrado"}</p>
                  <p>{userData?.cliente?.direccion || "No registrado"}</p>
                </div>
              </div>
            
          </section>

          <section className='Container-Datos'>
          <h3>Datos Personales</h3>
          <form className='datos-personales' onSubmit={handleSubmit}>
          <label/>Nombre Completo:<input type='text' value={`${userData.name} ${userData.lastname}`} readOnly/>
          <label/>Documento:<input type='text'  value={dni}  onChange={(e) => setDni(e.target.value)}  required />
          <label/>Correo:<input type='text' value={userData.email}readOnly />
          <label/>Telefono:<input type='text' value={telefono} onChange={(e) => setTelefono(e.target.value)} required/>
          <label/>Direccion:<input type='text' value={direccion} onChange={(e) => setDireccion(e.target.value)}required />
          <input className='Boton-Datos-Personales' type='submit'value='Guardar'></input>
          </form>
          </section>
      </div>
    )
  }

  export default MenuPerfil
