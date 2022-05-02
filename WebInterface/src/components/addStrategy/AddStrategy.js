import React, { useState, createContext } from 'react';
import Map from './AddStrategyMap'
import SixG_Strategy from '../../artifacts/contracts/SixG_Strategy.sol/SixG_Strategy.json'
import { ethers } from 'ethers'
import {
  MapContainer,
  TileLayer,
} from 'react-leaflet'
import '../../App.css';

const StratContext = createContext();

function AddStrategy(props) {
  const zoomLv = 13;
  const {contractAddress, provider, initialPos} = props
  const [newStrat, setNewStrat] = useState({name: 'no strategy selected'})

  const handleSubmit = async e => {
    e.preventDefault(); //prevents refresh
    var name = e.target[0].value
    var prio = e.target[1].value
    var desc = e.target[2].value
    var type = e.target[3].value
    var startDate = e.target[4].value
    var startTime = e.target[5].value
    var endDate = e.target[6].value
    var endTime = e.target[7].value

    if (name === "" || prio === "" || type === "" || startDate === "" || startTime === "" || endDate === "" || endTime === "") {
      alert("Please Fill All Required Field");
      return
    }
    if (newStrat.latlng === undefined){
      alert("Please select area")
      return
    }
    
    setNewStrat(prev => ({
      ...prev,
      name: name,
      priority: prio,
      description: desc,
      connection_type: type,
      start_date: startDate,
      start_time: startTime,
      end_date: endDate,
      end_time: endTime
    }))

    const signer = provider.getSigner("0x8626f6940e2eb28930efb4cef49b2d1f2c9c1199")
    // const signer = new ethers.Wallet( "0x59c6995e998f97a5a0044966f0945389dc9e86dae88c7a8412f4603b6b78690d", provider)

    const start = e.target[5].valueAsNumber +  e.target[4].valueAsNumber
    const end = e.target[6].valueAsNumber +  e.target[7].valueAsNumber

    const contract = new ethers.Contract(contractAddress, SixG_Strategy.abi, signer)
    const transaction = await contract.makeStrategy(newStrat.latlng.lng.toFixed(5) * (10 ** 5), 
                                                    newStrat.latlng.lat.toFixed(5) * (10 ** 5), 
                                                    newStrat.radius.toFixed(5) * (10 ** 5),
                                                    start, 
                                                    end,
                                                    type,
                                                    prio, 
                                                    desc,
                                                    name
                                                    )
    await transaction.wait()
    e.target.reset();
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
          <form name="form" onSubmit={handleSubmit}>
            <label htmlFor="stratName">Strategy name</label><br/>
            <input type="text" id="stratName" name="stratName"/><br/>
            <label htmlFor="prio">Priority</label><br/>
            <input type="number" id="prio" name="prio"/><br/>
            <label htmlFor="desc">Description</label><br/>
            <textarea name="desc" cols="40" rows="5"></textarea><br/>
            <label htmlFor="connectionType">Connection type</label><br/>
            <input type="text" id="connectionType" name="connectionType"/><br/>
            <label htmlFor="startDate">Startdate</label><br/>
            <input type="date" id="startDate" name="startDate"/>
            <label htmlFor="startTime">StartTime</label>
            <input type="time" id="startTime" name="startTime"/><br/>
            <label htmlFor="endDate">Enddate</label><br/>
            <input type="date" id="endDate" name="endDate"/>
            <label htmlFor="endTime">EndTime</label>
            <input type="time" id="endTime" name="endTime"/><br/>
            <input htmlFor="submit" type="submit" value="Submit"></input>
          </form>
        </div>
      </div>
      </StratContext.Provider>
      );
}

export {AddStrategy,
        StratContext};