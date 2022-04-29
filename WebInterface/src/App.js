import Web3 from 'web3'
import React, { useState, createContext, useEffect } from 'react'
import { HashRouter as Router, Route, Routes, Link } from 'react-router-dom';
// import { MapContainer, TileLayer } from "react-leaflet";
import './App.css'
import Nav from './components/navbar/Navbar'
import Home from './components/Home'
import AddStrategy from './components/addStrategy/AddStrategy';
import StrategyGrid from './components/currentStrategies/StrategyGrid';
import Footer from './components/footer/Footer';
import "leaflet/dist/leaflet.css";
import "leaflet-area-select";
import { ethers } from 'ethers'
import Greeter from './artifacts/contracts/Greeter.sol/Greeter.json'
import L from 'leaflet'
import 'leaflet/dist/leaflet.css';
import {
  Marker,
  Circle,
  CircleMarker,
  MapContainer,
  Polyline,
  Polygon,
  Popup,
  Rectangle,
  TileLayer,
} from 'react-leaflet'

import icon from 'leaflet/dist/images/marker-icon.png';
import iconShadow from 'leaflet/dist/images/marker-shadow.png';

let DefaultIcon = L.icon({
    iconUrl: icon,
    shadowUrl: iconShadow
});

L.Marker.prototype.options.icon = DefaultIcon;

const StratContext = createContext();
L.Icon.Default.imagePath='leaflet_images/';
function App() {
  const initialPos = [55.78373878553941, 12.518501326376303];
  const zoomLv = 13;
  const [newStrat, setNewStrat] = useState({name: 'no strategy selected'})

/* const center = [51.505, -0.09] */

const rectangle = [
  [51.49, -0.08],
  [51.5, -0.06],
]

/* const fillBlueOptions = { fillColor: 'blue' }
const blackOptions = { color: 'black' }
const limeOptions = { color: 'lime' }
const purpleOptions = { color: 'purple' }
const redOptions = { color: 'red' } */

// const [data, setData] = useState([])
var data = []
const greeterAddress = "0x5FbDB2315678afecb367f032d93F642f64180aa3"
const provider = new ethers.providers.JsonRpcProvider("http://localhost:8545");
const contract = new ethers.Contract(greeterAddress, Greeter.abi, provider)
const state = {markers: [[51.505, -0.09], [55, 12.51]]}

/* provider.on("block", async (blockNumber) => {
  console.log(blockNumber)
  var _data = await contract.getCircle()
  _data = _data.split(", ") */
  // setData(_data)
  //console.log("Data from App: " + _data)
  // RenderCircle()
  /* await new Promise(r => setTimeout(r, 5000)); */
/* }); */

/* contract.on("SetCircle", (to, amount, from) => {
  console.log(to, amount, from);
}); */

useEffect(async () => {
  var _data = await contract.getCircle()
  _data = _data.split(", ")
  data = _data
  // setData(_data)
  console.log("here1 with " + data)
  // RenderCircle()
  // await new Promise(r => setTimeout(r, 1000));
});

function RenderCircle() {
  console.log("here2 with " + data)
  if (data.length > 1) {
    console.log("here3")
    console.log(parseFloat(data[0]), parseFloat(data[1]), parseFloat(data[2]))
    return <Circle center={[parseFloat(data[0]), parseFloat(data[1])]} radius={parseFloat(data[2])} />
  }
  return null
}

  return (
    <>
    <Nav/>
    <Home/>
    {/* Current strategies */}
    <StratContext.Provider value={{ selectedStrat: newStrat, setSelectedStrat: setNewStrat }}>
      <div className="background">
        <div className='map'>

        <MapContainer center={initialPos} zoom={zoomLv} id='map'>
          <TileLayer
            url="https://{s}.tile.openstreetmap.fr/osmfr/{z}/{x}/{y}.png"
            attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
            maxZoom={20}
          />

         {/*  {data.map((position, idx) => 
          <Marker key={`marker-${idx}`} position={position}>
        </Marker>
        )} */}

          {console.log("test")}
          <RenderCircle/>
          
          {/* <Circle center={initialPos} radius={200} />  */}
          {/* <CircleMarker
            center={[55.98373878553941, 12.518501326376303]}
            radius={20}>
            <Popup>Popup in CircleMarker</Popup>
          </CircleMarker>
          <Rectangle bounds={rectangle}/>  */}
          </MapContainer>
        </div> 
        
        <div className="changestrategy">
          <StrategyGrid/>
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