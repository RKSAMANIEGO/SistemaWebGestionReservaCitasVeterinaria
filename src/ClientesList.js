import React, { useEffect, useState } from 'react'
import axios from 'axios'

const ClientesList = () => {

    const [clientes, setClientes] = useState([]);

    useEffect(() => {
        const obtenerClientes = async () => {
            try {
                const respuesta = await axios.get('http://localhost:8080/api/listarClientes'); 
                setClientes(respuesta.data);
            } catch (error) {
                console.error('Error al obtener los clientes:', error);
            }
        };
        obtenerClientes();
    }, []);



  return (
    <div>
    <h1>Lista Clientes</h1>
    <div style={{ display: 'flex', justifyContent:"center"}}>
        {clientes.map(cliente => (
            <div 
                key={cliente.id} 
                style={{
                    border: '1px solid #ccc', 
                    borderRadius: '15px', 
                    padding: '16px',
                    margin:"10px",
                    width: '250px', 
                    boxShadow: '2px 2px 10px rgba(0,0,0,0.1)',
                    textAlign: 'center' // Centrar el texto
                }}
            >
                <h3>{cliente.nombre}</h3>
                <p>Celular: {cliente.celular}</p>
                <p>Correo: {cliente.correo}</p>
                <img 
                    src={`http://localhost:8080/api/pictures/${cliente.foto}`} 
                    alt={`Foto de ${cliente.nombre}`} 
                    style={{ width: '100%', height: 'auto', borderRadius: '8px' }} // Ajusta el tamaño y redondea las esquinas
                />
            </div>
        ))}
    </div>
</div>
  )
}

export default ClientesList
