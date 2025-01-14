import React, { useState } from 'react'
import './StyleMenuVeterinario.css'

const MenuVeterinario = () => {

  const [horarios, setHorarios] = useState([
    { hora: "08:00 AM", lunes: "Disponible", martes: "Ocupado", miercoles: "No Laboral", jueves: "Disponible", viernes: "Disponible", sabado: "Ocupado", domingo: "No Laboral" },
    { hora: "09:00 AM", lunes: "Ocupado", martes: "Disponible", miercoles: "Disponible", jueves: "Disponible", viernes: "Disponible", sabado: "Ocupado", domingo: "No Laboral" },
    { hora: "10:00 AM", lunes: "Ocupado", martes: "Ocupado", miercoles: "Disponible", jueves: "Ocupado", viernes: "Ocupado", sabado: "Disponible", domingo: "No Laboral" },
  ]);

  const handleHorarioClick = (dia, hora) => {
    alert(`Has seleccionado ${dia} a las ${hora}`);
  };



  return (
    <div className="horarios-container">
    <h1>Horarios del Veterinario</h1>
    <table className="tabla-horarios">
      <thead>
        <tr>
          <th>Hora</th>
          <th>Lunes</th>
          <th>Martes</th>
          <th>Miércoles</th>
          <th>Jueves</th>
          <th>Viernes</th>
          <th>Sábado</th>
          <th>Domingo</th>
        </tr>
      </thead>
      <tbody>
        {horarios.map((horario, index) => (
          <tr key={index}>
            <td>{horario.hora}</td>
            {Object.entries(horario)
              .filter(([key]) => key !== "hora")
              .map(([dia, estado], i) => (
                <td
                  key={i}
                  className={estado.toLowerCase().replace(" ", "-")}
                  onClick={() => handleHorarioClick(dia, horario.hora)}
                >
                  {estado}
                </td>
              ))}
          </tr>
        ))}
      </tbody>
    </table>
  </div>

  )
}

export default MenuVeterinario
