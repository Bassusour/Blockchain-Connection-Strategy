import Web3 from 'web3'
import React, { useState } from 'react'
import { HashRouter as Router, Route, Routes, Link } from 'react-router-dom';
import Nav from './components/navbar/Navbar'
import Home from './components/Home'
import About from './components/About';
import Contact from './components/Contact';
import ChangeStrategy from './components/ChangeStrategy';
import Map from './components/Map';
import Footer from './components/Footer';
import "leaflet/dist/leaflet.css";
import "leaflet-area-select";
import { MapContainer, TileLayer } from "react-leaflet";

function App() {
  const position = [51.505, -0.09];

  return (
    <>
    <Nav/>
    <Home/>
    <ChangeStrategy/>
    <Contact/>
    <MapContainer center={position} zoom={13} style={{ height: "90vh" }}>
      <TileLayer
        attribution='&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
      />
      <Map />
    </MapContainer>
    <Footer/>
    </>

    // <Router>
    //   <Nav/>
    //   <Routes>
    //       <Route path="/" element={<Home/>}/>
    //       <Route path="/about" element={<About/>}/>
    //       <Route path="/contact" element={<Contact/>}/>
    //       <Route path="/changestrategy" element={<ChangeStrategy/>}/>
    //       <Route path="/stats" element={<Stats/>}/>
    //   </Routes>
    //   <Footer/>
    // </Router>
    
  );
}

export default App;
