import React from 'react'
import './StyleMenuMascota.css'
import Buscar from '../../../pictures/buscando.png'
import Pet from '../../../pictures/fb1.png'
import Save from '../../../pictures/save.png'
import Update from '../../../pictures/update.png'
import Delete from '../../../pictures/delete.png'
import Clear from '../../../pictures/clear.png'
const MenuMascota = () => {

    const mascotas = [
        {
          nombre: "Firulais",
          raza: "Labrador",
          sexo: "M",
          edad: 3,
          imagen: Pet,
        },
        {
          nombre: "Michi",
          raza: "Siamés",
          sexo: "F",
          edad: 2,
          imagen: Pet,
        },
        {
          nombre: "Rocky",
          raza: "Bulldog",
          sexo: "M",
          edad: 4,
          imagen:Pet,
        },
      ];



  return (
    <div className='container-Menu-Mascotas'>
        <div className='container-buscar-mascota'>
            <input className='txt-buscar' type='text' placeholder='Buscar' ></input>
            <a  className='Boton-Buscar-Animal' href='http://google.com'><img className='logo-buscar' src={Buscar} alt='buscar' width='30px'/></a>
        </div>

        <div className='container-add-mascota'>
            <div className='imagen-pet'><img src={Pet} alt='pet' /></div>
           
            <form className='formulario-mascota'>
            <input type='number' readOnly hidden/>
            <label/>Nombre<input type='text' required />
            <label/>Raza<input type='text' required />

            <div className='campo-edad-sexo'>
            <label>
                Sexo  
                <select required>
                 <option value="M">Masculino</option>
                 <option value="F">Femenino</option>
                </select>
            </label>

            <label/>Edad<input type='number' min='0' max='120' required/><label/>meses
            </div>

            <div className='botones-crud'>
                <a href='guardar'><img src={Save} alt='guardar'/></a>
                <a href='actualizar'><img src={Update} alt='actualizar'/></a>
                <a href='eliminar'><img src={Delete} alt='eliminar'/></a>
                <a href='limpiar'><img src={Clear} alt='limpiar'/></a>
            </div>
            
            </form>
        </div>

    <div className="tabla-container">
      <h2>Lista de Mascotas</h2>
      <table className="tabla-mascotas">
        <thead>
          <tr>
            <th>Nombre</th>
            <th>Raza</th>
            <th>Sexo</th>
            <th>Edad</th>
            <th>Imagen</th>
          </tr>
        </thead>
        <tbody>
          {mascotas.map((mascota, index) => (
            <tr key={index}>
              <td>{mascota.nombre}</td>
              <td>{mascota.raza}</td>
              <td>{mascota.sexo}</td>
              <td>{mascota.edad} años</td>
              <td>
                <img
                  src={mascota.imagen}
                  alt={`Imagen de ${mascota.nombre}`}
                  className="tabla-imagen"
                />
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
    
    </div>
  )
}

export default MenuMascota
