import Web3 from 'web3'
import React, { useState, createContext } from 'react'
import { HashRouter as Router, Route, Routes, Link } from 'react-router-dom';
// import { MapContainer, TileLayer } from "react-leaflet";
import './App.css'
import Nav from './components/navbar/Navbar'
import Home from './components/Home'
import About from './components/About';
import AddStrategy from './components/AddStrategy';
import ChangeStrategy from './components/currentStrategies/StrategyGrid';
import Footer from './components/Footer';
import "leaflet/dist/leaflet.css";
import "leaflet-area-select";
import {
  Circle,
  CircleMarker,
  MapContainer,
  Polyline,
  Polygon,
  Popup,
  Rectangle,
  TileLayer,
} from 'react-leaflet'

const StratContext = createContext();

function App() {
  const initialPos = [55.78373878553941, 12.518501326376303];
  const zoomLv = 13;
  const [selectedStrat, setSelectedStrat] = useState({name: 'no strategy selected'})

/* const center = [51.505, -0.09] */

/* const rectangle = [
  [51.49, -0.08],
  [51.5, -0.06],
] */

/* const fillBlueOptions = { fillColor: 'blue' }
const blackOptions = { color: 'black' }
const limeOptions = { color: 'lime' }
const purpleOptions = { color: 'purple' }
const redOptions = { color: 'red' } */

  return (
    <>
    <Nav/>
    <Home/>
    {/* Current strategies */}
    <StratContext.Provider value={{ selectedStrat, setSelectedStrat }}>
      <div className="background">
        <div className='map'>
        <MapContainer center={initialPos} zoom={zoomLv} id='map'>
          <TileLayer
            url="https://{s}.tile.openstreetmap.fr/osmfr/{z}/{x}/{y}.png"
            attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
            maxZoom={20}
          />
        {/* <Circle center={center} pathOptions={fillBlueOptions} radius={200} />
        <CircleMarker
          center={[51.51, -0.12]}
          pathOptions={redOptions}
          radius={20}>
          <Popup>Popup in CircleMarker</Popup>
        </CircleMarker>
        <Rectangle bounds={rectangle} pathOptions={blackOptions} /> */}
        </MapContainer>
        </div>
        <div className="changestrategy">
          <ChangeStrategy/>
        </div>
      </div>
      
      <AddStrategy/>
    </StratContext.Provider>
    
    <Footer/>
    </>
  );
}

export {
  App,
  StratContext
}