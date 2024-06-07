import { css } from '@emotion/react';

import Button from '@/components/common/Button/Button';
import Calendar from '@/components/common/Calendar/Calendar';
import Flex from '@/components/common/Flex/Flex';
import MeetStepTitle from '@/components/meet/common/MeetStepTitle/MeetStepTitle';

import { useCalendar } from '@/hooks/common';

import { StepProps } from '@/type/funnel';

import { Theme } from '@/styles/theme/theme';

const MeetDateStep = ({ onNextStep }: StepProps) => {
  const { currentDate, yearMonthData, selectedDate, handleDateClick, handleChangeMonth } =
    useCalendar();

  return (
    <section css={wrapperStyle}>
      <Flex styles={{ direction: 'column', align: 'center', gap: Theme.spacing.spacing5 }}>
        <MeetStepTitle
          mainDescription="날짜 입력하기"
          subDescription="약속의 날짜를 입력해주세요."
        />
        <Calendar
          currentDate={currentDate}
          yearMonthData={yearMonthData}
          onDateClick={handleDateClick}
          onChangeMonth={handleChangeMonth}
          selectedDate={selectedDate}
        />
      </Flex>
      <Button onClick={onNextStep} variant="default" size="large">
        다음으로
      </Button>
    </section>
  );
};

export default MeetDateStep;

const wrapperStyle = css({
  display: 'flex',
  flexDirection: 'column',
  alignItems: 'center',
  justifyContent: 'space-between',

  height: '80vh',
});
