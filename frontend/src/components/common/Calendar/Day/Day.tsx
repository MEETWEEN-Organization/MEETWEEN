import { dayStyle, getCurrentMonthDayStyle, getSelectedStyle, getTodayStyle } from './Day.style';

export interface DayProps {
  year?: string | number;
  month?: string | number;
  day: string | number;
  validDate?: string;
  isToday?: boolean;
  onClick?: () => void;
  isHoliday?: {
    isSunday?: boolean;
    isSaturday?: boolean;
  };
  isSelected?: boolean;
  isCurrentMonthDate?: boolean;
}

const Day = ({
  year,
  month,
  day,
  isToday = false,
  isCurrentMonthDate = false,
  validDate = '',
  isHoliday = {},
  isSelected = false,
  onClick,
}: DayProps) => {
  const handleKeyDown = (e: React.KeyboardEvent<HTMLSpanElement>) => {
    const { key } = e;

    if (key === 'Enter') {
      onClick?.();
    }
  };

  return (
    <div>
      <span
        role={!!onClick ? 'button' : undefined}
        tabIndex={!!onClick ? 0 : -1}
        aria-label={
          year ? `${year}년 ${month}월 ${validDate?.slice(validDate.length - 2)}일` : `${day}요일`
        }
        css={[
          dayStyle(!!onClick, isHoliday),
          getTodayStyle(isToday),
          getCurrentMonthDayStyle(isCurrentMonthDate || !Boolean(year)),
          getSelectedStyle(isSelected),
        ]}
        onClick={onClick}
        onKeyDown={handleKeyDown}
      >
        {!!validDate ? validDate?.slice(validDate.length - 2) : day}
      </span>
    </div>
  );
};

export default Day;
