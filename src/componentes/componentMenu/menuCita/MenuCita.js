import React from 'react'
import './StyleMenuCita.css'

const MenuCita = () => {
  return (
    <div>
      <div className="header-citas">
         <h2>Citas de Cliente: Juan Pérez</h2>
         <p>Mascota: Rocky (Perro, Labrador, 3 años)</p>
      </div>

      <div className="acciones-citas">
        <input type="date" className="filtro-fecha" />
        <select className="filtro-estado">
        <option value="">Todos los estados</option>
        <option value="confirmada">Confirmada</option>
        <option value="cancelada">Cancelada</option>
        <option value="finalizada">Finalizada</option>
        </select>
        <button className="btn-buscar">Buscar</button>
      </div>

    <table className="tabla-citas">
       <thead>
        <tr>
         <th>Fecha de Creación</th>
         <th>Servicio</th>
         <th>Motivo</th>
         <th>Ubicación</th>
         <th>Estado</th>
         <th>Acciones</th>
        </tr>
       </thead>
       <tbody>
        <tr>
         <td>2024-11-19</td>
         <td>Consulta General</td>
         <td>Chequeo anual</td>
         <td>Veterinaria Central</td>
         <td>Confirmada</td>
         <td>
           <button className="btn-accion">Ver</button>
           <button className="btn-accion">Cancelar</button>
         </td>
        </tr>
        <tr>
         <td>2024-11-15</td>
         <td>Vacunación</td>
         <td>Refuerzo antirrábico</td>
         <td>Sucursal Sur</td>
         <td>Finalizada</td>
        <td>
        <button className="btn-accion">Ver</button>
        </td>
       </tr>
      </tbody>
    </table>

    </div>
  )
}

export default MenuCita
