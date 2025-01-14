import React, { useState } from 'react'
import logo from './pictures/reserva.png';
import { FaUserCircle } from 'react-icons/fa';
import { Link } from 'react-router-dom';

const Cabecera = () => {
  const [menuOpen, setMenuOpen] = useState(false);
  const toggleMenu = () => {
    setMenuOpen(!menuOpen);
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
                <ul className={`nav-links ${menuOpen ? 'show' : ''}`}>

                    <li><Link to ='/' >Inicio</Link></li>
                    <li><Link to='/servicios' >Servicios</Link></li>
                    <li><Link to='/Profesionales'>Profesionales</Link></li>
                    <li><Link to='/contacto' >Contacto</Link></li>
                </ul>
            </nav>
              {/* <Link to ='/login' > </Link>*/} 
              <Link to ='/login' > <FaUserCircle className="user-icon"></FaUserCircle></Link>

            <button className="hamburger" onClick={toggleMenu}>
              ☰
            </button>

            </div>
            <h3>Veterinaria..🦴</h3>
            <h1> San Juan Bautista</h1>  
            <div className='App-header-title'/>     
        
        </header>
    </div>
  )
}

export default Cabecera
