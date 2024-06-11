import { css } from '@emotion/react';

import { Theme } from '@/styles/theme/theme';

import { DayProps } from './Day';

export const dayStyle = (isClickable: boolean, isHoliday: Required<DayProps>['isHoliday']) =>
  css({
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',

    width: '36px',
    height: '36px',

    margin: '2px',

    borderRadius: Theme.borderRadius.small,

    fontSize: Theme.text.medium.fontSize,
    lineHeight: Theme.text.medium.lineHeight,
    letterSpacing: '.04rem',

    color: isHoliday.isSunday
      ? Theme.color.red200
      : isHoliday.isSaturday
        ? Theme.color.blue400
        : Theme.color.black,

    cursor: isClickable ? 'pointer' : 'default',
  });

export const getTodayStyle = (isToday: boolean) =>
  isToday &&
  css({
    backgroundColor: Theme.color.blue100,
  });

export const getCurrentMonthDayStyle = (isCurrent: boolean) =>
  !isCurrent &&
  css({
    color: Theme.color.gray400,
  });

export const getSelectedStyle = (isSelected: boolean) =>
  isSelected &&
  css({
    backgroundColor: Theme.color.blue500,
    color: Theme.color.white,
  });
