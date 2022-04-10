import React, { useState, useEffect, useContext } from 'react';
import { StratContext } from '../../App';
import Map from './AddStrategyMap'
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

function AddStrategy() {
  const zoomLv = 13;
  var { selectedStrat, setSelectedStrat } = useContext(StratContext)
  const initialPos = [55.78373878553941, 12.518501326376303];

  const handleSubmit = e => {
    e.preventDefault();
    setSelectedStrat(prev => ({
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
  };

  return (
      <div className="backgroundd" id="addStrategy">
        <div className='map'>
        <MapContainer center={initialPos} zoom={zoomLv} id='map'>
          <TileLayer
            url="https://{s}.tile.openstreetmap.fr/osmfr/{z}/{x}/{y}.png"
            attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
            maxZoom={20}
          />
          {/* Load current strategies here */}
          <Map/>
        </MapContainer>
        </div>

        <form className="inputs" onSubmit={handleSubmit}>
          <label htmlFor="stratName">Strategy name</label><br/>
          <input type="text" id="stratName" name="stratName"/><br/>
          <label htmlFor="prio">Priority</label><br/>
          <input type="number" id="prio" name="prio"/><br/>
          <label htmlFor="desc">Description</label><br/>
          <textarea name="desc" cols="40" rows="5"></textarea><br/>
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
);
}

export default AddStrategy;