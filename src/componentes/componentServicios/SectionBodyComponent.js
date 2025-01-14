import React, { useEffect, useState } from 'react'
import  './StyleSectBody.css'


const SectionBodyComponent = ({ servicio })=> {
  const [servicios, setServicios] = useState([]);


  useEffect(() => {
    // Función para obtener los datos
    const fetchServicios = async () => {
      try {
        const response = await fetch('http://localhost:8092/tipoServicio/listar');
        if (!response.ok) {
          throw new Error('Error al obtener los datos');
        }
        const data = await response.json();
        setServicios(data);
      } catch (error) {
        console.error('Error:', error);
      }
    };

    fetchServicios();
  }, []);

//IMAGENES 

const imagenes = [
  'https://diagnopet.com.pe/wp-content/uploads/2024/02/24_abr_computarizada-1.jpg',
  'https://revistaboletinbiologica.com.ar/wp-content/uploads/riesgos-de-no-vacunar-a-tu-perro-adulto-todo-lo-que-debes-saber.jpg',
  'https://sp-ao.shortpixel.ai/client/to_auto,q_glossy,ret_img,w_800,h_420/https://hospitalveterinario.cr/wp-content/uploads/2024/06/Cirugia-Veterinaria-Minimamente-Invasiva-Blog-1024x538.jpg',
  'https://asistencia.ikeargentina.com.ar/blogbackoffice/images/1700771927703_medicina-preventiva.jpeg',
  'https://www.portalveterinaria.com/upload/20200326091019visavet.jpg',
  'https://img77.uenicdn.com/image/upload/v1565887692/service_images/shutterstock_403700587.jpg',
  'https://www.naronclinicaveterinaria.com/images/ginecologia-veterinaria-naron.jpg',
  'https://hospitaldemascotasgt.com/wp-content/uploads/2018/12/24.png'
];

//DESCRIPCIONES

const descripciones = [
  "En nuestra veterinaria, ofrecemos consultas generales para garantizar el bienestar de tu mascota, donde nuestro equipo de profesionales realiza un examen completo para detectar posibles problemas de salud y proporcionar atención preventiva. Además, contamos con un servicio de diagnóstico avanzado, utilizando tecnología y pruebas especializadas para identificar enfermedades o afecciones subyacentes. De esta manera, aseguramos que tu mascota reciba un diagnóstico preciso y el tratamiento adecuado para su pronta recuperación.",
  "Brindamos servicios de vacunación y desparasitación para proteger la salud de tu mascota. Las vacunas son fundamentales para prevenir enfermedades contagiosas y mantener a tu mascota protegida a lo largo de su vida. Además, ofrecemos tratamientos de desparasitación para eliminar parásitos internos y externos, garantizando que tu mascota esté libre de infecciones y con una salud óptima. Nos aseguramos de que cada proceso se realice con productos de alta calidad y bajo la supervisión de profesionales comprometidos con el bienestar de tu mascota.",
  "Ofrecemos servicios de cirugía veterinaria para tratar diversas condiciones de salud de tu mascota. Ya sea para procedimientos de urgencia, cirugías programadas o intervenciones complejas, nuestro equipo de veterinarios especializados utiliza técnicas avanzadas y equipos de última tecnología para asegurar el bienestar y la recuperación rápida de tu mascota. Nos enfocamos en realizar cada cirugía con el máximo cuidado, priorizando la seguridad y confort de tu mascota antes, durante y después del procedimiento",
  "La medicina preventiva es fundamental para asegurar la salud a largo plazo de tu mascota. Ofrecemos chequeos regulares, exámenes de salud completos y planes de prevención adaptados a las necesidades específicas de cada animal. A través de vacunas, controles periódicos y asesoramiento nutricional, trabajamos para prevenir enfermedades antes de que ocurran, garantizando una vida saludable y activa para tu mascota. Nuestro objetivo es actuar proactivamente para evitar problemas de salud y asegurar su bienestar continuo",
  "Nuestro laboratorio clínico veterinario, realizamos una amplia variedad de pruebas diagnósticas que nos permiten detectar y tratar enfermedades de manera temprana y precisa. Contamos con tecnología avanzada para realizar análisis de sangre, orina, exámenes microbiológicos y otras pruebas especializadas, lo que nos ayuda a obtener información clave sobre la salud de tu mascota. Con resultados rápidos y confiables, nuestro equipo de profesionales utiliza los datos obtenidos para ofrecer un tratamiento efectivo y personalizado, asegurando que tu mascota reciba la mejor atención posible.",
  "El servicio de peluquería y estética en nuestra veterinaria está diseñado para garantizar que tu mascota no solo se vea bien, sino que también se sienta cómoda y saludable. Ofrecemos cortes de pelo personalizados, baño, cepillado y cuidado de uñas, adaptados a las necesidades específicas de cada animal. Además, contamos con productos de alta calidad para asegurar la salud y el bienestar de su piel y pelaje. Nuestro equipo de profesionales está capacitado para brindar un trato suave y respetuoso, proporcionando una experiencia relajante para tu mascota mientras la dejamos luciendo hermosa y cuidada.",
  "El servicio de reproducción y obstetricia en nuestra veterinaria está dedicado a garantizar la salud reproductiva de tu mascota en todas las etapas de su vida. Ofrecemos asesoramiento especializado sobre planificación de la cría, fertilidad y técnicas de inseminación artificial, así como un seguimiento integral durante el embarazo de las hembras. Además, nuestro equipo realiza monitoreo de la salud materna y fetal, brindando atención durante el parto para asegurar que tanto la madre como los cachorros estén en óptimas condiciones. Nuestro objetivo es ofrecer un cuidado profesional y personalizado para una reproducción segura y exitosa.",
  "Nuestro servicio de emergencias 24/7 está disponible para atender a tu mascota en cualquier momento, día o noche. Ya sea por una urgencia médica inesperada, accidentes, intoxicaciones o cualquier otra situación crítica, nuestro equipo de veterinarios altamente capacitados está listo para brindar atención inmediata y especializada. Contamos con instalaciones equipadas con tecnología avanzada para realizar diagnósticos rápidos y efectivos, garantizando que tu mascota reciba el tratamiento adecuado en el menor tiempo posible. Tu tranquilidad y la salud de tu mascota son nuestra prioridad en todo momento.",
];


  return (
    <div className='Container-Body'>
    <h1 className='Container-Sub'>Servicios❢</h1>

    <div className='Container-Context-Left'>
      
    {servicios.map((servicio, index) => {
       const imagen = imagenes[index % imagenes.length];
       const descripcion = descripciones[index % descripciones.length];
        // Si el índice es par, ponerlo en el contenedor izquierdo
        if (index % 2 === 0) {
          return (
      <div className='Container-Context' key={servicio.id || index} >
        <div className='Container-Description'>
            <h3>{servicio.nombre}</h3>
            <p>{descripcion}</p>
        </div>
        <div className='Container-Imagen'>
            <img src={imagen} alt={`Servicio ${index}`}></img>
        </div>
      </div>
       );
      }
      return (

      <div className='Container-Context' key={servicio.id || index}>
      <div className='Container-Imagen'>
            <img src={imagen} alt='logo'></img>
        </div>
        <div className='Container-Description'>
            <h3>{servicio.nombre}</h3>
            <p>{descripcion}</p>
        </div>
      </div>

);
})}
</div>

    </div>
  )
}

export default SectionBodyComponent
