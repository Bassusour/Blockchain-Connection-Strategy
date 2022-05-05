import { useEffect, useContext} from "react";
import { useLeafletContext } from "@react-leaflet/core";
import L from 'leaflet'
import "@geoman-io/leaflet-geoman-free";
import "@geoman-io/leaflet-geoman-free/dist/leaflet-geoman.css";
import { StratContext } from './AddStrategy';

function Map() {
  const context = useLeafletContext();
  const { selectedStrat, setSelectedStrat } = useContext(StratContext)
  const map = context.map;

  useEffect(() => {
    map.pm.addControls({
      drawMarker: false,
      drawCircleMarker: false,
      drawPolyline: false,
      drawPolygon: false,
      cutPolygon: false,
      rotateMode: false,
      drawRectangle: false
    });

    map.pm.setGlobalOptions({ snapable: true });

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
        drawCircle: false,
      })

      map.pm
        .getGeomanLayers()
        .map((layer, index) => layer.bindPopup("marked area for strategy"));

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
          }
          ))
        }
      });
    });

    map.on("pm:remove", (e) => {
      map.pm.addControls({
        drawCircle: true,
      })
    });

    return () => {
      map.pm.removeControls();
    };
  }, [context, setSelectedStrat, map]);

  return (
    null
  );
};

export default Map;
