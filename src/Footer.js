import React from 'react'
import Wsp from './pictures/whatsapp.png'
import Fb from './pictures/facebook.png'
import Maps from './pictures/maps.png'
import './style/Footer.css'

const Footer = () => {
  return (
    <div className='Footer'>
     <div className="Footer-Context">
        <div>
            <h3>¿Cómo Ubicarnos?</h3>
            <ul>
                <li>Ubicación : Urb. Los Topacio Mz “B” Lt 5 Ate , Lima, Peru</li>
                <li>Referencia: A media Cuadra del Mercado Nvo Horizonte</li>
            </ul>
        </div>

        <div>
        <h3>Horarios</h3>
            <ul>
                <li>Atencion : Lunes - Sabado</li>
                <li>Horarios : 9am - 6pm</li>
                <li>Feriados : 9am - 6pm</li>
                <p>( Los Horarios pueden variar...)</p>
            </ul>
        </div>

        <div>
        <h3>Contactos</h3>
            <ul>
                <li>Celular  : 990 103 626 📞</li>
                <li>WhatsApp : 990 103 626 📱</li>
                <li>Facebook : <a href='https://www.facebook.com/VeterinariaSJB' target='react/jsx-no-target-blank'>Veterinaria S.J.B</a></li>
            </ul>
        </div>
      </div>

      <div className='Footer-Copy'>
        <p className='copy'>&copy; 2024 - Veterinaria San Juan Bautista</p>
        <div className='Footer-Icons'>
            <a href='https://api.whatsapp.com/send/?phone=51990103626&text&type=phone_number&app_absent=0' target='react/jsx-no-target-blank' >
            <img src={Wsp} alt="WhatsApp Icon"/>
            </a>

            <a href='https://www.facebook.com/VeterinariaSJB' target='react/jsx-no-target-blank' >
            <img src={Fb} alt="Facebook Icon"/>
            </a>

            <a href='https://www.google.com/maps/place/Veterinaria+San+Juan+Bautista/@-12.0261888,-76.9112244,21z/data=!4m22!1m15!4m14!1m5!1m1!1s0x9105c3fa3a7a8c7b:0xf784ab8f4e2132c6!2m2!1d-76.9109426!2d-12.0261887!1m6!1m2!1s0x9105c3fa3a7a8c7b:0xf784ab8f4e2132c6!2sVeterinaria+San+Juan+Bautista,+Urb,+Ate+15491!2m2!1d-76.9109426!2d-12.0261887!3e0!3m5!1s0x9105c3fa3a7a8c7b:0xf784ab8f4e2132c6!8m2!3d-12.0261881!4d-76.9109374!16s%2Fg%2F11fzrzbgw5?entry=ttu&g_ep=EgoyMDI0MTAyNy4wIKXMDSoASAFQAw%3D%3D' target='react/jsx-no-target-blank'>
            <img src={Maps} alt="Map Icon"/>
            </a>
        </div>
      </div>
    </div>
  )
}

export default Footer
