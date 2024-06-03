import { LatLngType } from '@/type/place';

export const getCalulatedMidPoint = (origins: Omit<LatLngType, 'key'>[]) => {
  const latSum = origins.reduce((acc, cur) => acc + Number(cur.x), 0);
  const lngSum = origins.reduce((acc, cur) => acc + Number(cur.y), 0);

  return {
    lat: +(latSum / origins.length).toFixed(4),
    lng: +(lngSum / origins.length).toFixed(4),
  };
};
