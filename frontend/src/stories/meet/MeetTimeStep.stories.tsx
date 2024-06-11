import type { Meta, StoryObj } from '@storybook/react';

import MeetTimeStep from '@/components/meet/MeetTimeStep/MeetTimeStep';

const meta = {
  title: 'Meet/MeetTimeStep',
  component: MeetTimeStep,
  parameters: {
    layout: 'centered',
  },
  tags: ['autodocs'],
} satisfies Meta<typeof MeetTimeStep>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Default: Story = {};
