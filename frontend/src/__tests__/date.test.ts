import { formatDate, formatRealDate, getYearMonthInfo } from '@/utils/date';

describe('Date util 함수 관련 작동 테스트', () => {
  test('Date가 YYYY-MM-DD 형식으로 출력된다.', () => {
    expect(formatDate('2024', '2', '3')).toBe('2024-02-03');
  });

  test('Date에 따른 YearMonthInfo가 정상적으로 출력된다.', () => {
    expect(getYearMonthInfo(new Date('2024-05-01'))).toEqual({
      year: '2024',
      month: '05',
      firstDay: 3,
      startDate: new Date('2024-05'),
      lastDate: 31,
    });
  });

  test('YearMonthData을 입력 시 실제 dateString이 출력된다.', () => {
    const yearMonthData = getYearMonthInfo(new Date('2024-05-01')); // 현재 날짜

    expect(formatRealDate(yearMonthData, -1)).toBe('2024-04-29');
    expect(formatRealDate(yearMonthData, 0)).toBe('2024-04-30');
    expect(formatRealDate(yearMonthData, 1)).toBe('2024-05-01');
  });
});
