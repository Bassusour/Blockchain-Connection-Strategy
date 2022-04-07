import Web3 from 'web3'
import React, { useState, createContext } from 'react'
import { HashRouter as Router, Route, Routes, Link } from 'react-router-dom';
import { MapContainer, TileLayer } from "react-leaflet";
import './App.css'
import Nav from './components/navbar/Navbar'
import Home from './components/Home'
import About from './components/About';
import Contact from './components/Contact';
import ChangeStrategy from './components/selectStrategy/ChangeStrategy';
import Map from './components/selectStrategy/Map';
import Footer from './components/Footer';
import "leaflet/dist/leaflet.css";
import "leaflet-area-select";

const StratContext = createContext();

function App() {
  const initialPos = [55.78373878553941, 12.518501326376303];
  const zoomLv = 13;
  const [selectedStrat, setSelectedStrat] = useState("No strategy selected")

  return (
    <>
    <Nav/>
    <Home/>

    <StratContext.Provider value={{ selectedStrat, setSelectedStrat }}>
      <div className="background">
        <div className='map'>
        <MapContainer center={initialPos} zoom={zoomLv} id='map'>
          <TileLayer
            url="https://{s}.tile.openstreetmap.fr/osmfr/{z}/{x}/{y}.png"
            attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
            maxZoom={20}
          />
          <Map />
        </MapContainer>
        </div>
        <div className="changestrategy">
          <ChangeStrategy/>
        </div>
      </div>
    </StratContext.Provider>

    <Contact/>
    <Footer/>
    </>
  );
}

export {App,
        StratContext
}