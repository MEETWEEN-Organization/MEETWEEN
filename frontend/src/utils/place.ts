import { LatLngType } from '@/type/place';

export const getCalulatedMidPoint = (origins: Omit<LatLngType, 'key' | 'address_name'>[]) => {
  const latSum = origins.reduce((acc, cur) => acc + Number(cur.y), 0);
  const lngSum = origins.reduce((acc, cur) => acc + Number(cur.x), 0);

  return {
    lat: latSum / origins.length,
    lng: lngSum / origins.length,
  };
};
