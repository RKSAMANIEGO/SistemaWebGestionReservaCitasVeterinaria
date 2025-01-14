import './App.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import PageHome from './pages/pageHome/page_home';
import PageService from './pages/pageServicios/page_servicios'
import PageContacto from './pages/pageContactanos/page_contacto'
import PageLogin from './pages/pageLogin/page_login'
import PageMenu from './pages/pageMenu/page_menu'
import PageProfesionales from './pages/pageProfesionales/page_profesionales'


function App() {
  return (
    <Router>
    <div>
      <Routes>
        <Route path="/" element={<PageHome />} />
        <Route path="/servicios" element={<PageService />} />
        <Route path="/contacto" element={<PageContacto/>} />
        <Route path="/login" element={<PageLogin/>} />
        <Route path="/MenuPrincipal" element={<PageMenu/>} />
        <Route path='/Profesionales' element={<PageProfesionales/>}/>
      </Routes>
    </div>
  </Router>
  );
}

export default App;
