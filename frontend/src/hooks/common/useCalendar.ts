import { useState } from 'react';

import { formatRealDate, getNewYearMonthInfo, getYearMonthInfo } from '@utils/date';

import { YearMonthType } from '@type/date';

const useCalendar = () => {
  const currentDate = new Date();
  const currentYearMonth = getYearMonthInfo(currentDate);

  const [selectedDate, setSelectedDate] = useState<string>(formatRealDate(currentYearMonth, 0));
  const [yearMonthData, setYearMonthData] = useState<YearMonthType>(currentYearMonth);

  const handleDateClick = (date: string) => {
    setSelectedDate(date);
  };

  const handleChangeMonth = (change: number) => {
    setSelectedDate('');
    setYearMonthData((prev) => getNewYearMonthInfo(prev, change));
  };

  return { currentDate, yearMonthData, selectedDate, handleDateClick, handleChangeMonth };
};

export default useCalendar;
