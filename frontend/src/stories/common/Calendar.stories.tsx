import type { Meta, StoryObj } from '@storybook/react';

import Calendar from '@/components/common/Calendar/Calendar';

import { useCalendar } from '@/hooks/common';

import { getYearMonthInfo } from '@/utils/date';

import { containerStyle } from '../style';

const meta = {
  title: 'Common/Calendar',
  component: Calendar,
  parameters: {},
  tags: [],
  decorators: [
    (Story) => (
      <div css={containerStyle}>
        <Story />
      </div>
    ),
  ],
  argTypes: {},
  args: {
    currentDate: new Date(),
    yearMonthData: getYearMonthInfo(new Date()),
  },
} satisfies Meta<typeof Calendar>;
type Story = StoryObj<typeof meta>;

export default meta;

export const PlayGround: Story = {
  render: () => {
    const { currentDate, yearMonthData, selectedDate, handleDateClick, handleChangeMonth } =
      useCalendar();

    return (
      <Calendar
        currentDate={currentDate}
        yearMonthData={yearMonthData}
        selectedDate={selectedDate}
        onDateClick={handleDateClick}
        onChangeMonth={handleChangeMonth}
      />
    );
  },
};
