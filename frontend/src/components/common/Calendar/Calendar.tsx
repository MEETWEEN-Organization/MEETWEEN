import Heading from '@/components/common/Heading/Heading';

import { getDayInfo, getDaySize } from '@/utils/date';

import { YearMonthType } from '@/type/date';

import { DAYS_OF_WEEK } from '@/constants/common';

import PrevMonth from '@/assets/svg/left-arrow.svg?react';
import NextMonth from '@/assets/svg/right-arrow.svg?react';

import { calendarContainerStyle, dayContainerStyle, headerStyle } from './Calendar.style';
import Day from './Day/Day';

interface CalendarProps {
  currentDate: Date;
  yearMonthData: YearMonthType;
  selectedDate?: string;
  onDateClick?: (date: string) => void;
  onChangeMonth?: (change: number) => void;
}

const Calendar = ({
  currentDate,
  yearMonthData,
  selectedDate,
  onDateClick,
  onChangeMonth,
}: CalendarProps) => {
  console.log(selectedDate);
  return (
    <div css={calendarContainerStyle}>
      <header css={headerStyle}>
        <PrevMonth onClick={() => onChangeMonth?.(-1)} />
        <Heading size="xSmall">
          {yearMonthData.month}월,&nbsp;&nbsp;{yearMonthData.year}
        </Heading>
        <NextMonth onClick={() => onChangeMonth?.(1)} />
      </header>
      <section css={[dayContainerStyle]}>
        {DAYS_OF_WEEK.map((day) => (
          <Day key={day} day={day} />
        ))}
      </section>
      <section css={dayContainerStyle}>
        {Array.from({ length: getDaySize(yearMonthData) }).map((_, index) => {
          const { date, dateString, isCurrentMonthDate, isToday, isHoliday, isSelected } =
            getDayInfo({
              index,
              yearMonthData: yearMonthData,
              currentDate,
              selectedDate,
            });
          return (
            <Day
              key={dateString}
              year={yearMonthData.year}
              month={yearMonthData.month}
              day={date}
              validDate={dateString}
              isToday={isToday}
              isHoliday={isHoliday}
              isCurrentMonthDate={isCurrentMonthDate}
              isSelected={isSelected}
              onClick={() => onDateClick?.(dateString)}
            />
          );
        })}
      </section>
    </div>
  );
};

export default Calendar;
