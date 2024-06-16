import type { Meta, StoryObj } from '@storybook/react';

import { useState } from 'react';

import MeetGuestCounter from '@/components/meet/common/MeetGuestCounter/MeetGuestCounter';

const meta = {
  title: 'Meet/MeetGuestCounter',
  component: MeetGuestCounter,
  parameters: {
    layout: 'centered',
  },
  tags: ['autodocs'],
  args: {
    guestCount: 0,
  },
} satisfies Meta<typeof MeetGuestCounter>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Playground: Story = {
  render: () => {
    const [count, setCount] = useState(0);

    const handleChange = (change: number) => {
      setCount((prev) => prev + change);
    };
    return <MeetGuestCounter guestCount={count} onCountChange={handleChange} />;
  },
};
