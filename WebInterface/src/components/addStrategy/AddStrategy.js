import React, { useState, useEffect, useContext, createContext } from 'react';
import Map from './AddStrategyMap'
import Greeter from '../../artifacts/contracts/Greeter.sol/Greeter.json'
import { ethers } from 'ethers'
import {
  Circle,
  CircleMarker,
  MapContainer,
  Polyline,
  Polygon,
  Popup,
  Rectangle,
  TileLayer,i
} from 'react-leaflet'
import '../../App.css';

const StratContext = createContext();

function AddStrategy() {
  const zoomLv = 13;
  const initialPos = [55.78373878553941, 12.518501326376303];
  const greeterAddress = "0x5FbDB2315678afecb367f032d93F642f64180aa3"
  const [newStrat, setNewStrat] = useState({name: 'no strategy selected'})

  const handleSubmit = async e => {
    e.preventDefault();
    setNewStrat(prev => ({
      ...prev,
      name: e.target[0].value,
      priority: e.target[1].value,
      description: e.target[2].value,
      connection_type: e.target[3].value,
      start_date: e.target[4].value,
      start_time: e.target[5].value,
      end_date: e.target[6].value,
      end_time: e.target[7].value,
    }))

    const provider = new ethers.providers.JsonRpcProvider();
    const signer = provider.getSigner("0x8626f6940e2eb28930efb4cef49b2d1f2c9c1199")
    // const signer = new ethers.Wallet( "0x59c6995e998f97a5a0044966f0945389dc9e86dae88c7a8412f4603b6b78690d", provider)

    const contract = new ethers.Contract(greeterAddress, Greeter.abi, signer)
    const transaction = await contract.setCircle(newStrat.latlng.lat+"", newStrat.latlng.lng+"", newStrat.radius+"")

    await transaction.wait()
  };

  return (
    
      <StratContext.Provider value={{ selectedStrat: newStrat, setSelectedStrat: setNewStrat }}>
      <div className="background" id="addStrategy">
        
        <div className='map'>
        <MapContainer center={initialPos} zoom={zoomLv} id='map'>
          <TileLayer
            url="https://{s}.tile.openstreetmap.fr/osmfr/{z}/{x}/{y}.png"
            attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
            maxZoom={20}
          />
          <Map/>
        </MapContainer>
        </div>
        <div className="inputs">
          <form onSubmit={handleSubmit}>
            <label htmlFor="stratName">Strategy name</label><br/>
            <input type="text" id="stratName" name="stratName"/><br/>
            <label htmlFor="prio">Priority</label><br/>
            <input type="number" id="prio" name="prio"/><br/>
            <label htmlFor="desc">Description</label><br/>
            <textarea name="Text1" cols="40" rows="5"></textarea><br/>
            <label htmlFor="desc">Connection type</label><br/>
            <input type="text" id="connectionType" name="connectionType"/><br/>
            <label htmlFor="startDate">Startdate</label><br/>
            <input type="date" id="startDate" name="startDate"/>
            <label htmlFor="startTime">StartTime</label>
            <input type="time" id="startTime" name="startTime"/><br/>
            <label htmlFor="endDate">Enddate</label><br/>
            <input type="date" id="endDate" name="endDate"/>
            <label htmlFor="endTime">EndTime</label>
            <input type="time" id="endTime" name="endTime"/><br/>
            <input htmlFor="asdf" type="submit" value="Submit"></input>
          </form>
        </div>
      </div>
      </StratContext.Provider>
      );
}

export {AddStrategy,
        StratContext};