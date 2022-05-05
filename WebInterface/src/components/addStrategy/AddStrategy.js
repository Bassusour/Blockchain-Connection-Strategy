import React, { useState, createContext, useEffect } from 'react';
import Map from './AddStrategyMap'
import SixG_Strategy from '../../artifacts/contracts/SixG_Strategy.sol/SixG_Strategy.json'
import { ethers } from 'ethers'
import {
  MapContainer,
  TileLayer,
} from 'react-leaflet'
import '../../App.css';

const StratContext = createContext();
const prioToNum = {
  "Low": 0,
  "Medium": 1,
  "High": 2
}
const connectionTypeToNum = {
  "WiFi": 0,
  "Data": 1
}

function AddStrategy(props) {
  const zoomLv = 13;
  const {contractAddress, provider, contract, initialPos} = props
  const [newStrat, setNewStrat] = useState({name: 'No strategy selected'})
  const [estimatedGas, setEstimatedGas] = useState("Waiting for more information...")
  const [gasPrice, setGasPrice] = useState(0)

  useEffect( async () => {
      const gasPrice = await provider.getGasPrice()
      setGasPrice(gasPrice.toString())
  }, [])

  const getGas = async () => {
    const formEl = document.forms.form 
    if(formEl === undefined){
      return
    }
    const formData = new FormData(formEl);
    const name = formData.get('stratName');
    const prio = formData.get('prio');
    const desc = formData.get('desc');
    const type = formData.get('connectionType');
    const startDate = formData.get('startDate');
    const startTime = formData.get('startTime');
    const endDate = formData.get('endDate');
    const endTime = formData.get('endTime');

    if(name !== "" && prio !== "" && desc !== "" && type !== "" && startDate !== "" && startTime !== "" && endDate !== "" && endTime !== ""){
      console.log("here")
      const startTimeArr = startTime.split(":")
      const endTimeArr = endTime.split(":")
      const start = new Date(startDate).setHours(startTimeArr[0], startTimeArr[1])
      const end = new Date(endDate).setHours(endTimeArr[0], endTimeArr[1])

      // lat, lng, rad is always 5 numbers long, so specific value doesn't matter
      const estimatedGas_ = await contract.estimateGas.makeStrategy(
                                                    12345,
                                                    12345,
                                                    12345,
                                                    start, 
                                                    end,
                                                    connectionTypeToNum[type],
                                                    prioToNum[prio], 
                                                    desc,
                                                    name)
      setEstimatedGas(estimatedGas_ + " units of gas")
    }
  }

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

    if (name === "" || startDate === "" || startTime === "" || endDate === "" || endTime === "") {
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

    if(start > end) {
      alert("Startdate is later than enddate")
      return
    }

    const contract = new ethers.Contract(contractAddress, SixG_Strategy.abi, signer)
    const transaction = await contract.makeStrategy(
                                                    newStrat.latlng.lng.toFixed(5) * (10 ** 5), 
                                                    newStrat.latlng.lat.toFixed(5) * (10 ** 5), 
                                                    newStrat.radius.toFixed(5) * (10 ** 5),
                                                    start, 
                                                    end,
                                                    connectionTypeToNum[type],
                                                    prioToNum[prio], 
                                                    desc,
                                                    name)
    const receipt = await transaction.wait()
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
          <form name="form" id="form" onSubmit={handleSubmit}>
            <label htmlFor="stratName">Strategy name</label><br/>
            <input type="text" id="stratName" name="stratName" onChange={getGas} maxLength="32"/><br/>
            <label htmlFor="prio">Priority</label><br/>
            <select id="prio" name="prio"onChange={getGas} >
              <option value="Low">Low</option>
              <option value="Medium">Medium</option>
              <option value="High">High</option>
            </select><br/>
            <label htmlFor="desc">Description</label><br/>
            <input name="desc" onChange={getGas} maxLength="32"/><br/>
            <label htmlFor="connectionType">Connection type</label><br/>
            <select id="connectionType" name="connectionType" onChange={getGas}>
              <option value="WiFi">WiFi</option>
              <option value="Data">Data</option>
            </select><br/>
            <label htmlFor="startDate">Startdate</label><br/>
            <input type="date" id="startDate" name="startDate"onChange={getGas}/>
            <label htmlFor="startTime">StartTime</label>
            <input type="time" id="startTime" name="startTime"onChange={getGas}/><br/>
            <label htmlFor="endDate">Enddate</label><br/>
            <input type="date" id="endDate" name="endDate"onChange={getGas}/>
            <label htmlFor="endTime">EndTime</label>
            <input type="time" id="endTime" name="endTime"onChange={getGas}/><br/>
            <input htmlFor="submit" type="submit" value="Submit"></input><br/><br/>
            <label htmlFor="gasAmount"><b>Gas estimate: </b> {estimatedGas}</label><br/>
            <label htmlFor="gasPrice"><b>Gas price: </b> {gasPrice + " wei"}</label>
          </form>
        </div>
      </div>
      </StratContext.Provider>
      );
}

export {AddStrategy,
        StratContext};