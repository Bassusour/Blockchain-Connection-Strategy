import React, { useState, useEffect } from 'react';
import Map from './Map'
import { Link } from 'react-router-dom';
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
import '../App.css';


const fillBlueOptions = { fillColor: 'blue' }
const blackOptions = { color: 'black' }
const limeOptions = { color: 'lime' }
const purpleOptions = { color: 'purple' }
const redOptions = { color: 'red' }

function eatFood() {
  console.log("nom")
}

function AddStrategy() {
  const zoomLv = 13;
  const [selectedStrat, setSelectedStrat] = useState("No strategy selected")
  const center = [51.505, -0.09]

  const handleSubmit = e => {
    e.preventDefault();
    console.log(e.target[2].value);
  };

  return (
    
      <div className="background" id="addStrategy">
        
        <div className='map'>
        <MapContainer center={center} zoom={zoomLv} id='map'>
          <TileLayer
            url="https://{s}.tile.openstreetmap.fr/osmfr/{z}/{x}/{y}.png"
            attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
            maxZoom={20}
          />
          {/* <Circle center={center} pathOptions={fillBlueOptions} radius={200} />  */}
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
            <label htmlFor="endDate">enddate</label><br/>
            <input type="date" id="endDate" name="endDate"/>
            <label htmlFor="endTime">endTime</label>
            <input type="time" id="endTime" name="endTime"/><br/>
            <input htmlFor="asdf" type="submit" value="Submit"></input>
          </form>
        </div>
      </div>);
}


export default AddStrategy;
