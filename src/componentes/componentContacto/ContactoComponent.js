import React, { useEffect, useState } from 'react'
import './StyleContacto.css'
import Logo1 from '../../pictures/ub.png'
import Face from '../../pictures/facebook.png'
import Wsp from '../../pictures/whatsapp.png'
const ContactoComponent = () => {

 
   const [resultado, setResultado] = useState([])
       useEffect( ()=> {
        const url="http://localhost:8080/api/listarClientes";
        fetch(url)
           .then(response => response.json())
           .then(data => {
                setResultado(data)
              
                console.log(data)
           })
           .catch(error => console.error(error))
       } ,[]);
     
    
  return (
    <div className='Container-Contact'>
        <div className='Container-CHeader'>
            <h4>Contactate Con Nosotros</h4>
        </div>

        <div className='Container-CBody'>
            <div className='CContext'>
                <p>Dos magnitudes son inversamente proporcionales cuando al aumentar una, la otra disminuye en la misma proporción, y viceversa.</p>

                <div className='CLogo'>
                    <div className='Caja'>
                        <img src={Logo1} alt='logo' width='50px'height='50px'></img>
                         <div className='CText'>
                             <h5>Dirección</h5>
                             <p> Urb. Los Topacio Mz “B” Lt 5 Ate , Lima, Peru</p>
                          </div>
                    </div>
                    <div className='Caja'>
                        <img src="https://img.icons8.com/ios-filled/50/phone.png" alt="phone" width='50px' height='50px'></img>
                         <div className='CText'>
                             <h5>Teléfono</h5>
                             <p>+51 990 103 626 📱</p>
                          </div>
                    </div>
                    <div className='Caja'>
                        <img src="https://img.icons8.com/ios-filled/50/email.png" alt="email" width='50px' height='50px'></img>
                         <div className='CText'>
                             <h5>Correo Electrónico</h5>
                             <p>veterinariaSJB@gmail.com</p>
                          </div>
                    </div>
                    <div className='Caja'>
                        <img src="https://img.icons8.com/ios-filled/50/alarm-clock--v1.png" alt="alarm-clock--v1" width='50px'height='50px'></img>
                         <div className='CText'>
                             <h5>Horario de Atención</h5>
                             <p>Atencion : Lunes - Sabado
                             Horarios : 9am - 6pm</p>
                          </div>
                    </div>
                </div>
                <h6>Siguenos en:</h6>
                    <div className='Icon'>
                    <a href='https://www.facebook.com/VeterinariaSJB' target='react/jsx-no-target-blank'>
                    <img src={Face} alt='logo' width='40px'/>
                    </a>
                    
                    <a href='https://api.whatsapp.com/send/?phone=51990103626&text&type=phone_number&app_absent=0' target='react/jsx-no-target-blank'>
                    <img src={Wsp} alt='logo'  width='40px'/>
                    </a>
                    </div>
            </div>

            <div className='CForm'>
                <h3>Contactanos</h3>
                <form className='Formulario'>
                    <div className='Caja'>
                        <input type='text' placeholder='Nombres' required/>
                        <input type='text' placeholder='Apellidos' required/>    
                    </div>
                    <div className='Caja'>
                        <input type='text' placeholder='Correo Electronico'required/>
                        <input type='text' placeholder='Telefono'required/>    
                    </div>
                    <input type='text' placeholder='Asunto' required/>    
                   
                    <textarea className="auto-expand" placeholder='Mensaje'required /> 
                    <input className='Boton' type='submit'value='Enviar Mensaje'></input>
                </form>
            </div>
        </div>
        
        <div className="Container-Consumer">
            {resultado.map(cliente => (
            <div className="Consume-Api" key={cliente.id}>
               <div className="Caja-Cliente">
                 <p>Nombre: {cliente.nombre} <br /> Celular: {cliente.celular} </p>
                 <img src={`http://localhost:8080/api/pictures/${cliente.foto}`} alt={`imagen de ${cliente.nombre}`} width="200px" />
               </div>
            </div>
            ))}
        </div>

    </div>
  )
}

export default ContactoComponent
