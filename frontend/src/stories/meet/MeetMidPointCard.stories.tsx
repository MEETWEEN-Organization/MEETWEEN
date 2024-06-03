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
  argTypes: {},
} satisfies Meta<typeof MeetMidPointCard>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Playground: Story = {
  render: () => {
    return <MeetMidPointCard />;
  },
};
