import { useEffect, useContext, useRef } from "react";
import { useLeafletContext } from "@react-leaflet/core";
import L from 'leaflet'
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
  const map = context.map;
  // const container = L.map('map', { pmIgnore: false });
  /* console.log(context) */
  // context.marker([55.78373878553941, 12.518501326376303]).addTo(container);
  
  // if (map != undefined) { map.remove(); }

  // const latLng = {lat: 55.78373878553941, lng: 12.518501326376303};
  // const radius = 1401.7415305616735;
  // const circle = L.circle(latLng,{radius:radius}).addTo(map);
  // circle.pm.disable();

  useEffect(() => {

    map.pm.addControls({
      drawMarker: false,
      drawCircleMarker: false,
      drawPolyline: false,
      drawPolygon: false,
      cutPolygon: false,
      rotateMode: false
    });

    map.pm.setGlobalOptions({ snapable: true });
    map.pm.setPathOptions({
      color: stratColorMap[selectedStrat],
      fillColor: stratColorMap[selectedStrat],
      fillOpacity: 0.4,
    });

    map.on("pm:create", (e) => {
      const shape = e;
      if(e.shape === "Circle"){
        setSelectedStrat(prev => ({
          ...prev,
          shape: e.shape,
          latlng: e.layer._latlng,
          radius: e.layer.options.radius,
        }))
      }

      if(e.shape === "Rectangle"){
        setSelectedStrat(prev => ({
          ...prev,
          nortEast: e.layer._bounds._northEast,
          southWest: e.layer._bounds._southWest,
        }))
      }

      map.pm.addControls({
        drawRectangle: false,
        drawCircle: false,
      })
      // shape.layer.pm.enable();

      map.pm
        .getGeomanLayers()
        .map((layer, index) => layer.bindPopup("popup"));

      shape.layer.on("pm:edit", (e) => {
        if(e.shape === "Circle"){
          setSelectedStrat(prev => ({
            ...prev,
            shape: e.shape,
            latlng: e.layer._latlng,
            radius: e.layer.options.radius,
          }))
        }
  
        if(e.shape === "Rectangle"){
          setSelectedStrat(prev => ({
            ...prev,
            nortEast: e.layer._bounds._northEast,
            southWest: e.layer._bounds._southWest,
          }))
        }
      });
    });

    map.on("pm:remove", (e) => {
      map.pm.addControls({
        drawRectangle: true,
        drawCircle: true,
      })
    });

    return () => {
      map.pm.removeControls();
      // map.pm.setGlobalOptions({ pmIgnore: true });
    };
  }, [context, setSelectedStrat]);

  return (
    null
  );
};

export default Map;
