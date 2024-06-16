import { useCallback, useState } from 'react';

import { getNewYearMonthInfo, getYearMonthInfo } from '@/utils/date';

import { YearMonthType } from '@/type/date';

export const useCalendar = () => {
  const currentDate = new Date();
  const currentYearMonth = getYearMonthInfo(currentDate);

  const [selectedDate, setSelectedDate] = useState<string>('');
  const [yearMonthData, setYearMonthData] = useState<YearMonthType>(currentYearMonth);

  const handleDateClick = useCallback((date: string) => {
    setSelectedDate(date);
  }, []);

  const handleChangeMonth = useCallback((change: number) => {
    setSelectedDate('');
    setYearMonthData((prev) => getNewYearMonthInfo(prev, change));
  }, []);

  return { currentDate, yearMonthData, selectedDate, handleDateClick, handleChangeMonth };
};
