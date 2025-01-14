import React, { useEffect, useState } from 'react'
import LogoMenu from '../../pictures/logo7.png'
import Inicio from './menuInicio/MenuInicio'
import Perfil from './MenuPerfil/MenuPerfil'
import Mascota from './menuMascota/MenuMascota'
import Veterinario from './menuVeterinario/MenuVeterinario'
import Cita from './menuCita/MenuCita'

const MenuComponent = () => {
    


  // Estado para el contenido actual
  const [activeSection, setActiveSection] = useState("Perfil");
  const [username, setUsername] = useState('');
  const [name,setName] = useState('')
  const [isMenuEnabled, setIsMenuEnabled] = useState(false);    // modificado 






  useEffect(() => {
    // Recupera el nombre de usuario del localStorage
    const storedUsername = localStorage.getItem('username');
    if (storedUsername) {
      setUsername(storedUsername);  // Establece el nombre de usuario en el estado
    }

    const storedName=localStorage.getItem('nameComplet')
    if(storedName){
      setName(storedName)
    }
  }, []); // Solo se ejecuta una vez al cargar el componente

  // Función para manejar el cambio de sección
  const handleMenuClick = (section) => {             // modificado 
    if (isMenuEnabled || section === "Perfil") {   
      setActiveSection(section);
    }
  };

  // funcion que ira al menu principal
  const enableMenu = () => {                           // modificado 
    setIsMenuEnabled(true); // Habilita el menú
  };

  // Renderiza contenido dinámico
  const renderContent = () => {
      switch (activeSection) {
          case "Perfil":
            return <Perfil  enableMenu={enableMenu} />;
          case "Inicio":
            return <Inicio />;
          case "Mascotas":
            return <Mascota/>
          case "Veterinario":
              return <Veterinario/>;
          case "Cita":
              return <Cita/>
          default:
              return <div>Seleccione una opción del menú</div>;
      }
  };


  


  return (
   <div className="container">
      {/*Menú lateral */}
   <aside className="menu-lateral">
    <img src={LogoMenu} alt='logo-Menu' width='200px'></img>
    <h2>San Juan Bautista</h2>
        <ul>
                    <li onClick={() => handleMenuClick("Perfil")}>Perfil</li>
                    <li  className={!isMenuEnabled ? "disabled" : ""} onClick={() => handleMenuClick("Inicio")}>Inicio</li>             {/* modificado */}
                    <li  className={!isMenuEnabled ? "disabled" : ""} onClick={() => handleMenuClick("Mascotas")}>Mascotas</li>         {/* modificado */}
                    <li  className={!isMenuEnabled ? "disabled" : ""} onClick={() => handleMenuClick("Veterinario")}>Veterinarios</li>  {/* modificado */}
                    <li  className={!isMenuEnabled ? "disabled" : ""} onClick={() => handleMenuClick("Cita")}>Citas</li>                {/* modificado */}
                    <li>Salir</li>
        </ul>
    </aside> -- 


    {/* Contenedor principal */}
      <main className="main-content">
        {/* Encabezado */}
        <header className="header">
            <div className="apartado"> {activeSection}
            </div>
            <div className="usuario">  Bienvenid@ , <strong>{name}</strong></div>
        </header>

        {/* Contenido */}
        <section className="contenido">

            {renderContent()}
        </section>
    </main> 
</div>
  );
}

export default MenuComponent