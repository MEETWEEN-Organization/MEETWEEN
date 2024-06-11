import type { Meta, StoryObj } from '@storybook/react';

import MeetMidPointCard from '@/components/meet/common/MeetMidPointCard/MeetMidPointCard';

import { listStyle } from '@/stories/style';

const meta = {
  title: 'Meet/MeetMidPointCard',
  component: MeetMidPointCard,
  parameters: {
    layout: 'centered',
  },
  tags: ['autodocs'],
  decorators: [
    (Story) => (
      <ul css={listStyle}>
        <Story />
      </ul>
    ),
  ],
  args: {
    carDuration: 18,
    subWayDuration: 30,
    destination: '주안역',
    routeUrl: 'https://github.com/wuzoo',
    averageDuration: '1시간 25분',
  },
} satisfies Meta<typeof MeetMidPointCard>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Default: Story = {};
