import React, { useEffect, useState } from 'react'
import './StyleProfesionales.css'

const ProfesionalesComponent = () => {

    const [resultado, setResultado] = useState([])
    useEffect( ()=> {
     const url="http://localhost:8092/veterinario/listar";
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

        <div className="Container-Consumer">
             {resultado.map(veterinario => (
            <div className="Consume-Api" key={veterinario.id}>
              <div className="Caja-Veterinario">
                <h4>{veterinario.especialidad} </h4>
               <p>Dr {veterinario.nombres} </p>
               <img  src={`http://localhost:8092/veterinario/${veterinario.imagen}`}   alt={`imagen de ${veterinario.nombres}`} width="200px" />
              </div>
            </div>
           ))}
        </div>

    </div>
  )
}

export default ProfesionalesComponent
