export type YearMonthType = {
  month: string;
  year: string;
  startDate: Date;
  firstDay: number;
  lastDate: number;
};

export type DayInfoType = {
  index: number;
  yearMonthData: YearMonthType;
  currentDate: Date;
  selectedDate?: string;
};
