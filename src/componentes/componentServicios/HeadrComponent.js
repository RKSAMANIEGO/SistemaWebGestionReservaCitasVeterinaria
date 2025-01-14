import React, { useState } from 'react'
import logo from '../../pictures/reserva.png'
import { FaUserCircle } from 'react-icons/fa';
import '../../componentes/componentServicios/StyleServicios.css'
import { Link } from 'react-router-dom';
const HeadrComponent = () => {
    const [menusOpen, setMenusOpen] = useState(false);
    const togglesMenu = () => {
    setMenusOpen(!menusOpen);
};

  return (
    <div className='App'>
    <header className="App-header">
  <div className="Header-Content">
      <div className="logo" >
        <p>Reserva tu Cita</p>
        <a href='http://google.com'>
          <img src={logo} alt='reserva' height="50px" width="50px" />
        </a>
      </div>
  
        <nav>
            <ul className={`nav-links ${menusOpen ? 'show' : ''}`}>
                    <li><Link to ='/' >Inicio</Link></li>
                    <li><Link to='/servicios' >Servicios</Link></li>
                    <li><Link to='/Profesionales'>Profesionales</Link></li>
                    <li><Link to='/contacto' >Contacto</Link></li>
            </ul>
        </nav>
        <Link to ='/login' > <FaUserCircle className="user-icon" /></Link>

        <button className="hamburger" onClick={togglesMenu}>
          ☰
        </button>

        </div>
        <div className='App-header-title'>
           
        </div>
       
    
    </header>
</div>
  )
}

export default HeadrComponent
