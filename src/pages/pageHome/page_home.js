import React from 'react'
import '../../App.css';
import Cabecera from '../../Cabecera';
import Body from '../../ComponenteBody'
import Map from '../../Map'
import Footer from '../../Footer'

const page_home = () => {
    

    const images = [
        'https://ateanuncios.com/wp-content/uploads/2021/04/veterinaria-san-juan-bautista.jpg',
        'https://fotografias.antena3.com/clipping/cmsimages01/2023/04/17/CAE1D076-B306-4CD1-9EE2-7B68CA07DB5D/veterinario_58.jpg?crop=5950,3374,x0,y0&width=1000&height=567&optimize=low&format=webply',
        'https://www.miraflores.gob.pe/wp-content/uploads/2023/05/WhatsApp-Image-2023-05-22-at-21.10.12.jpeg',
        'https://medipet.com.ec/wp-content/uploads/2022/07/dermatologia-pop02-2022-1200x900.jpg',
        'https://leyendas.gob.pe/wp-content/uploads/2023/04/VETERINARIA_PARQUEDELASLEYENDAS-1.png'];

    return (
        <div>
            <Cabecera/>
            <Body  images={images}/>
            <Map/>
            <Footer/>
            {/*<ClientesList/>*/}
        </div>
    )
}

export default page_home
