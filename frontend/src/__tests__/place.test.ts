import { getCalulatedMidPoint } from '@/utils/place';

describe('Place 유틸 함수 테스트', () => {
  test('위도 경도의 평균값(중간 지점)이 정상적으로 도출된다.', () => {
    const origins = [
      { x: '37.5665', y: '126.978' },
      { x: '37.4565', y: '126.7052' },
      { x: '37.5134', y: '126.812' },
    ];

    expect(getCalulatedMidPoint(origins)).toEqual({
      lat: (+origins[0].x + +origins[1].x + +origins[2].x) / origins.length,
      lng: (+origins[0].y + +origins[1].y + +origins[2].y) / origins.length,
    });
  });
});
