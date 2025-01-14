import React from 'react'
import Cabecera from '../../Cabecera';
import Footer from '../../Footer'
import BodyProfesionales from '../../componentes/componentProfesionales/ProfesionalesComponent'

const page_profesionales = () => {
  return (
    <div>
      <div>
            <Cabecera/>
            <BodyProfesionales/>
            <Footer/>
        </div>
    </div>
  )
}

export default page_profesionales
