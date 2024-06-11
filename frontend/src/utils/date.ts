import { DayInfoType, YearMonthType } from '@/type/date';

import { DAY_IN_MONTHS } from '@/constants/common';

export const getDaySize = (yearMonthDate: YearMonthType) => {
  return yearMonthDate.firstDay + yearMonthDate.lastDate <= DAY_IN_MONTHS.MIN
    ? DAY_IN_MONTHS.MIN
    : DAY_IN_MONTHS.MAX;
};

export const formatDate = (
  year: string | number,
  month: string | number,
  date: string | number,
) => {
  return `${year}-${String(month).padStart(2, '0')}-${String(date).padStart(2, '0')}`;
};

export const formatRealDate = (dateString: YearMonthType, index: number) => {
  const date = new Date(`${dateString.year}-${dateString.month}`);
  date.setDate(index);

  return formatDate(date.getFullYear(), date.getMonth() + 1, date.getDate());
};

export const getDayInfo = ({ index, yearMonthData, currentDate, selectedDate }: DayInfoType) => {
  const date = index - yearMonthData.firstDay + 1;

  const realDate = new Date(`${yearMonthData.year}-${yearMonthData.month}`);
  realDate.setDate(date);

  const dateString = formatDate(
    realDate.getFullYear(),
    realDate.getMonth() + 1,
    realDate.getDate(),
  );

  const isCurrentMonthDate = index >= yearMonthData.firstDay && yearMonthData.lastDate >= date;

  const todayString = formatDate(
    currentDate.getFullYear(),
    currentDate.getMonth() + 1,
    currentDate.getDate(),
  );
  const isToday = todayString === dateString;

  const isSunday = realDate.getDay() === 0;
  const isSaturday = realDate.getDay() === 6;

  const isHoliday = {
    isSunday,
    isSaturday,
  };

  const isSelected = selectedDate === dateString;

  return { date, dateString, isCurrentMonthDate, isToday, isHoliday, isSelected };
};

export const getNewYearMonthInfo = (date: YearMonthType, change: number = 1) => {
  const startDate = new Date(`${date.year}-${date.month}`);

  startDate.setMonth(startDate.getMonth() + change);
  const newDate = new Date(startDate);

  return getYearMonthInfo(newDate);
};

export const getYearMonthInfo = (date: Date) => {
  const year = String(date.getFullYear());
  const month = String(date.getMonth() + 1).padStart(2, '0');
  const startDate = new Date(`${year}-${month}`);

  const firstDay = startDate.getDay();
  const lastDate = new Date(date.getFullYear(), date.getMonth() + 1, 0).getDate();

  return { month, year, startDate, firstDay, lastDate };
};

export const getResultDate = (dateString: string, time: string) => {
  const [year, month, date] = dateString.split('-');

  const isAm = Number(time.split(':').at(0)) < 12;

  return `${year}년 ${month}월 ${date}일 ${time} ${isAm ? 'AM' : 'PM'}`;
};
