import { AddressType, KeywordResultType, LatLngType } from '@/type/place';

export const getCalulatedMidPoint = (origins: Omit<LatLngType, 'key'>[]) => {
  const latSum = origins.reduce((acc, cur) => acc + Number(cur.x), 0);
  const lngSum = origins.reduce((acc, cur) => acc + Number(cur.y), 0);

  return {
    lat: latSum / origins.length,
    lng: lngSum / origins.length,
  };
};

const { kakao } = window;

export const getKeywordResult = (center: Omit<AddressType, 'address_name'>) => {
  const places = new window.kakao.maps.services.Places();

  places.keywordSearch(
    '지하철역',
    (result: KeywordResultType[]) => {
      console.log(result);
    },
    {
      location: kakao.maps.LatLng(center.x, center.y),
      sort: window.kakao.maps.services.SortBy.DISTANCE,
    },
  );
};
