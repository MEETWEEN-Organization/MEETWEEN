import { useEffect, useRef, useState } from 'react';

interface MapProps {
  center: {
    lat: string;
    lng: string;
  };
}

const Map = ({ center }: MapProps) => {
  const wrapperRef = useRef<HTMLDivElement | null>(null);
  const [_, setMap] = useState<google.maps.Map>();

  useEffect(() => {
    if (wrapperRef.current) {
      const map = new window.google.maps.Map(wrapperRef.current, {
        center: new window.google.maps.LatLng(center.lat, center.lng),
        zoom: 16,
        minZoom: 10,
        maxZoom: 20,
        mapId: import.meta.env.VITE_GOOGLE_MAP_ID,
        disableDefaultUI: true,
        gestureHandling: 'greedy',
      });

      setMap(map);
      console.log(map);
    }
  }, [center]);

  return <div id="map" ref={wrapperRef} css={{ height: 'calc(100vh - 76px)', width: '100%' }} />;
};

export default Map;
