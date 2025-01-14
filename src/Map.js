import React, { useRef } from 'react'
import { MapContainer, TileLayer, Marker, Popup } from 'react-leaflet';
import 'leaflet/dist/leaflet.css';
import L from 'leaflet';
import iconUrl from './pictures/ubi.png';
import './style/Carousel.css';
const Map = () => {
    const location = { lat: -12.026032181630638, lng:-76.91146622335455 }; 
    const mapRef = useRef(); // Usaremos este ref para habilitar/deshabilitar el mapa


    const customIcon = new L.Icon({
        iconUrl: iconUrl, 
        iconSize: [45, 45], 
        iconAnchor: [17, 46], 
        popupAnchor: [0, -46], 
        
    });

      // Función para habilitar la interacción al hacer clic
      const enableInteraction = () => {
        const map = mapRef.current;
        if (map) {
            map.dragging.enable();
            map.scrollWheelZoom.enable();
            map.doubleClickZoom.enable();
        }
    };

    // Función para deshabilitar la interacción
    const disableInteraction = () => {
        const map = mapRef.current;
        if (map) {
            map.dragging.disable();
            map.scrollWheelZoom.disable();
            map.doubleClickZoom.disable();
        }
    };

  return (
    <>
    <div className="Header-Map">
        <h2>Estamos Ubicados Aqui...📌</h2>
    </div>
    <MapContainer 
    center={location} 
    zoom={15} 
    style={{ height: '400px', width: '100%' }} 
    whenCreated={(mapInstance) => {
        mapRef.current = mapInstance; // Guarda el mapa en el ref
        disableInteraction(); // Desactiva la interacción al cargar el mapa
    }}
    onClick={enableInteraction} // Habilita la interacción al hacer clic
    
    >
            <TileLayer
                url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                   attribution='&copy; <a href="https://osm.org/copyright">OpenStreetMap</a> contributors'
            />
            <Marker position={location} icon={customIcon}> 
                <Popup>Ubicación de la Veterinaria San Juan Bautista</Popup>
            </Marker>
        </MapContainer>
        </>
  )
}

export default Map
