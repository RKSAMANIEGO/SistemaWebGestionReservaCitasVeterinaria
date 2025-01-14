import React, { useState } from 'react'
/*import somosImg from './pictures/mascotas.jpg'*/
import './style/Carousel.css' 
const ComponenteBody =( { images } )=> {

    const [currentIndex, setCurrentIndex] = useState(0);

    // Función para cambiar a la imagen anterior
    const prevSlide = () => {
        setCurrentIndex(currentIndex === 0 ? images.length - 1 : currentIndex - 1);
    };

    // Función para cambiar a la siguiente imagen
    const nextSlide = () => {
        setCurrentIndex(currentIndex === images.length - 1 ? 0 : currentIndex + 1);
    };

    // Función para seleccionar la imagen mediante los puntos
    const goToSlide = (index) => {
        setCurrentIndex(index);
    };


 return (
<section className='App-Body'>
    <article className='Context-Info'>
        <div>
        <h3>Nosotros</h3>
        <p>Hace 5 años, se Fundo la veterinaria "San Juan Bautista".La clinica 
            es el proyecto  de veterinaria apasionada,fundado con el unico 
            proposito de proporcionar la mejor atencion veterinaria posible a las
            mascotas de la comunidad local. En su primer año de funcionamiento y 
            el pequeño equipo de enfermeros veterinarios  a su cargo brindaron 
            unicamente atencion basica como consultas, vacunas  y ajetreos preventivos
        </p>
        </div>
       
       {/* <img src={somosImg} alt='somos' className='Somos-Imagen'/>*/}
       
        
    </article>

    <article className='Context-Info1'>
        <div>
        <h3>Vision</h3>
        <p>Ser la clínica veterinaria líder en la comunidad, reconocida por brindar atención integral, personalizada y de alta calidad a cada mascota. Nos comprometemos a ser un referente en bienestar animal, innovando continuamente en servicios, tecnología y atención al cliente, para mejorar la salud y calidad de vida de las mascotas y fortalecer los lazos con sus familias.
        </p>
        </div>
       
       {/* <img src={somosImg} alt='somos' className='Somos-Imagen'/>*/}
       
        
    </article>

    <article className='Context-Colaboradores'>
       <h2>Clientes</h2>

        <div className="carousel">
            <button className="carousel-button left" onClick={prevSlide}>&lt;</button>
               <div className="carousel-image-container">
                <img src={images[currentIndex]} alt={`Slide ${currentIndex + 1}`} className="carousel-image" />
               </div>
            <button className="carousel-button right" onClick={nextSlide}>&gt;</button>
            
                <div className="carousel-dots">
                {images.map((_, index) => (
                    <span
                        key={index}
                        className={`carousel-dot ${index === currentIndex ? 'active' : ''}`}
                        onClick={() => goToSlide(index)}
                    ></span>
                ))}
                </div>
        </div>
    </article>
</section>
)
}

export default ComponenteBody
