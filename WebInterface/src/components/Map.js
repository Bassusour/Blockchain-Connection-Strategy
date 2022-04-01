import { React, useEffect } from 'react';
import { useMap } from "react-leaflet";
import L from "leaflet";
import {TileLayer, Popup, Circle, CircleMarker } from "react-leaflet";




function Map() {
  const map = useMap()

  useEffect(() => {
    if (!map.selectArea) return;

    map.selectArea.enable();

    map.on("areaselected", (e) => {
      // console.log(e.bounds.toBBoxString()); // lon, lat, lon, lat
      // const firstCoord = e.bounds.toBBoxString().split(',')
      const firstCoord = e.bounds.toBBoxString().split(',')[0] +', ' + e.bounds.toBBoxString().split(',')[1]
      const secondCoord = e.bounds.toBBoxString().split(',')[2] +', ' + e.bounds.toBBoxString().split(',')[3]
      // console.log(firstCoord + " " + secondCoord)
      console.log(e.bounds)
      L.rectangle(e.bounds, { color: "blue", weight: 1 }).addTo(map);
      // var currentMarker = L.circleMarker(, {color: 'green', weight: 0, fillOpacity: 0.4}).addTo(map);
      // <CircleMarker center={[firstCoord]}>
      //       <Popup>
      //         A pretty CSS3 popup. <br /> Easily customizable.
      //       </Popup>
      //     </CircleMarker>
      // currentMarker.setRadius(10);
      // currentMarker.addTo(this.map);
    });

    // You can restrict selection area like this:
    const bounds = map.getBounds().pad(-0.25); // save current map bounds as restriction area
    // check restricted area on start and move
    /*map.selectArea.setValidate((layerPoint) => {
      return bounds.contains(this._map.layerPointToLatLng(layerPoint));
    });*/

    // now switch it off
    map.selectArea.setValidate();
  }, []);

  return (
    null
  )
}

export default Map