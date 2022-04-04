import { useEffect } from "react";
import { useLeafletContext } from "@react-leaflet/core";
import "@geoman-io/leaflet-geoman-free";
import "@geoman-io/leaflet-geoman-free/dist/leaflet-geoman.css";

const Map = () => {
  const context = useLeafletContext();

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

    // On shape creation
    map.on("pm:create", (e) => {
      const shape = e;
      console.log(e);

      // enable editing of circle
      shape.layer.pm.enable();

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
  }, [context]);

  return (
    null
  );
};

export default Map;
