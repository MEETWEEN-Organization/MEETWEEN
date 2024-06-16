import type { Meta, StoryObj } from '@storybook/react';

import MeetResultCard from '@/components/meet/common/MeetResultCard/MeetResultCard';

import { listStyle } from '@/stories/style';

const meta = {
  title: 'Meet/MeetResultCard',
  component: MeetResultCard,
  parameters: {
    layout: 'centered',
  },
  tags: ['autodocs'],
  decorators: [
    (Story) => (
      <div css={listStyle}>
        <Story />
      </div>
    ),
  ],
  argTypes: {
    meetTitle: {
      control: { type: 'text' },
    },
    meetPlace: {
      control: { type: 'text' },
    },
    memberCount: {
      control: { type: 'range', min: 1, max: 10 },
    },
    meetTime: {
      control: { type: 'text' },
    },
    inviteCode: {
      control: { type: 'text' },
    },
  },
  args: {
    meetTitle: '우리들의 스터디',
    meetCategory: '스터디',
    memberCount: 3,
    meetTime: '2024년 6월 12일 18:00PM',
    meetPlace: '인천광역시 미추홀구 주안역',
    inviteCode: 123123,
  },
} satisfies Meta<typeof MeetResultCard>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Default: Story = {};
