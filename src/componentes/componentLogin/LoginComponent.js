import React, { useState } from 'react'
import Facebook from '../../pictures/fb1.png'
import Instagram from '../../pictures/ig1.png'
import Twiter from '../../pictures/tw.png'
import { useNavigate } from 'react-router-dom'

const LoginComponent = () => {

  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [errorMessage, setErrorMessage] = useState('');
  const navigate = useNavigate();

  //LOGIN
  const handleSubmit = async (event) => {
    event.preventDefault();

    const loginData = { username, password };

    try {
      const response = await fetch('http://localhost:8092/auth/log-in', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(loginData),
      });

      if (response.ok) {
        const data = await response.json();
        // Guardar el JWT en localStorage
        
        localStorage.setItem('jwt', data.jwt);
        localStorage.setItem('username', data.username);
        localStorage.setItem('nameComplet', data.name);
        alert('Login exitoso');
        navigate('/MenuPrincipal');
        console.log('Username:', localStorage.getItem('username'));
        console.log('Token:', localStorage.getItem('jwt')); 
        console.log('Token:', localStorage.getItem('nameComplet')); 
        // Aquí puedes redirigir al usuario o hacer otras acciones
      } else {
        const errorData = await response.json();
        setErrorMessage(errorData.message || 'Error en el login');
      }
    } catch (error) {
      setErrorMessage('Hubo un problema al intentar iniciar sesión');
    }
  };

  //MODAL PARA EL REGISTRO

  const [mostrarModal, setMostrarModal] = useState(false);

    const abrirModal = (e) => {
        e.preventDefault(); // Evita la navegación por defecto del enlace
        setMostrarModal(true);
    };

    const cerrarModal = () => setMostrarModal(false);


    const registro = (e) => {
      e.preventDefault(); // Prevenir la acción por defecto del botón
      alert("Registrado correctamente");
    };
 
  return (
    <div className='Container-Main'>
    <section className='Container-Body'>

       <article className='post'> 
        <h1>Iniciar Sesion</h1>

        <form  onSubmit={handleSubmit}>
        <label htmlFor="username">Usuario</label> <br/>
        <input type="text" id="username" placeholder="Ingrese su usuario" required value={username}   
              onChange={(e) => setUsername(e.target.value)} /> <br/>

        <label htmlFor="password">Contraseña</label> <br/>
        <input type="password" id="password" placeholder="Ingrese su contraseña" required  value={password}
              onChange={(e) => setPassword(e.target.value)} /> <br/>

        <p className="datos">Olvidastes tu Contraseña?</p>
        <button type="submit" className='btn-Ingresar'>LOGIN</button>
        </form>
        {errorMessage && <p style={{ color: 'red', fontSize:'15px' , textAlign:'center'}}>{errorMessage}</p>}
       </article>

         <article className="reference">
            <p className="datos">Registrate usando</p>
            <div className="imagen">
             <a href="https://www.facebook.com/login/?next=https%3A%2F%2Fwww.facebook.com%2F" target='react/jsx-no-target-blank' ><img src={Facebook} alt='imgFb' width="40"/></a>
             <a href="https://www.instagram.com/" target='react/jsx-no-target-blank'> <img src={Instagram} alt='imgIg' width="40"/></a>
             <a href="https://x.com/?lang=en" target='react/jsx-no-target-blank'> <img src={Twiter} alt='imgTw' width="40"/></a>
            </div> 
        </article>

        <footer className='Container-Footer'>   
         <p className="datos">Oh Registrate Aqui¡</p>      
         <p className="datos">

          <a  href="https://www.instagram.com/" onClick={abrirModal} className="enlace-abrir"> Registrarse</a>
         
          {mostrarModal && (
                <div className="modal">
                    <div className="modal-contenido">
                        <span onClick={cerrarModal} className="cerrar">&times;</span>
                        <h2>Registrate</h2>
                        <form className='formulario-registrar'>
                            <label>Nombre</label>
                            <input type="text" name="nombre"  required />
                      
                            <label>Apellido</label>
                            <input type="text" name="apellido"  required />

                            <label>Correo</label>
                            <input type="text" name="correo"  required />
                          
                            <label>Usuario</label>
                            <input type="text" name="usuario"  required />
                          
                            <label>Contraseña</label>
                            <input type="password" id="password"  name="contrasenia" required />
                            <button type="submit" onClick={registro}>Registrar</button>
                        </form>
                    </div>
                </div>
            )}
         </p>  
        </footer>  

    </section>
    </div>
  )
}

export default LoginComponent
