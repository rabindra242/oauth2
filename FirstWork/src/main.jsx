import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.jsx'
import './index.css'
import Navbar from "./commons/Navbar.jsx";
import Footer from "./commons/Footer.jsx";

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
      <Navbar></Navbar>
    <App />
      <Footer></Footer>
  </React.StrictMode>,
)
