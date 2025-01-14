import React from 'react'
import './StyleMenuInicio.css'


const MenuInicio = () => {
  return (
    <div className='Container-Menu-Inicio'>
         <h1>Es momento de engreir a nuestras mascotas,<br/> Compra y Reserva aqui. </h1>

      <div className='Container-Menu-Inicio-Caja' >
         <div className='Container-Main-Context'> 
         <h4 className='hover-popup'>Tienda</h4>   
         <section className='Menu-Inicio-Caja1'/>
         </div> 

         <div className='Container-Main-Context'>
         <h4  className='hover-popup'>Reserva tu cita</h4>   
         <section className='Menu-Inicio-Caja2'/>       
         </div> 
      </div>

    </div>
  )
}

export default MenuInicio
