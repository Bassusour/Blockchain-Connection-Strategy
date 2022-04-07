import { useEffect, useContext, useRef } from "react";
import { useLeafletContext } from "@react-leaflet/core";
import "@geoman-io/leaflet-geoman-free";
import "@geoman-io/leaflet-geoman-free/dist/leaflet-geoman.css";
import { StratContext } from '../../App';

const stratColorMap = {
  "Strategy #1": 'orange',
  "Strategy #2": 'blue',
  "Strategy #3": 'green',
  "Strategy #4": 'yellow',
  "Strategy #5": 'red',
  "Strategy #6": 'purple',
}

function Map() {
  const context = useLeafletContext();
  const { selectedStrat, setSelectedStrat } = useContext(StratContext)
  // console.log(selectedStrat)

  useEffect(() => {
    const map = context.map;

    map.pm.addControls({
      drawMarker: false,
      drawCircleMarker: false,
      drawPolyline: false,
      drawPolygon: false,
      cutPolygon: false,
      rotateMode: false
    });

    map.pm.setGlobalOptions({ snapable: true });
    console.log(selectedStrat)
    map.pm.setPathOptions({
      color: stratColorMap[selectedStrat],
      fillColor: stratColorMap[selectedStrat],
      fillOpacity: 0.4,
    });

    // On shape creation
    map.on("pm:create", (e) => {
      const shape = e;
      console.log(e);

      // enable editing of shape
      // shape.layer.pm.enable();

      // console.log(`object created: ${shape.layer.pm.getShape()}`);

      // Set popup
      // map.pm
      //   .getGeomanLayers()
      //   .map((layer, index) => layer.bindPopup(`I am figure N° ${index}`));

      // On shape edit
      shape.layer.on("pm:edit", (e) => {
        console.log('edited')
      });
    });

    // On shape remove
    map.on("pm:remove", (e) => {
      console.log("removed");
    });

    return () => {
      map.pm.removeControls();
      // map.pm.setGlobalOptions({ pmIgnore: true });
    };
  }, [context, selectedStrat]);

  return (
    null
  );
};

export default Map;
