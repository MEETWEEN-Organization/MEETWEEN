import { useEffect } from 'react';

import { PlaceLocationType } from '@/type/place';

import { Theme } from '@/styles/theme/theme';

interface MapPolylineProps {
  paths: PlaceLocationType[][] | PlaceLocationType[];
  map: google.maps.Map;
}

const MapPolyline = ({ map, paths }: MapPolylineProps) => {
  useEffect(() => {
    if (Array.isArray(paths[0])) {
      const lines: google.maps.Polyline[] = [];

      paths.forEach((path) => {
        const polyline = new window.google.maps.Polyline({
          path,
          strokeOpacity: 1,
          strokeColor: Theme.color.blue400,
          strokeWeight: 2,
          map,
        }) as google.maps.Polyline;

        polyline.setMap(map);

        lines.push(polyline);
      });

      return () => {
        lines.forEach((line) => line.setMap(null));
      };
    } else {
      const polyline = new window.google.maps.Polyline({
        path: paths,
        strokeOpacity: 1,
        strokeColor: Theme.color.blue400,
        strokeWeight: 2,
        map,
      }) as google.maps.Polyline;

      polyline.setMap(map);

      return () => {
        polyline.setMap(null);
      };
    }
  }, [paths, map]);

  return null;
};

export default MapPolyline;
