import type { Meta, StoryObj } from '@storybook/react';

import MeetResultStep from '@/components/meet/MeetResultStep/MeetResultStep';

const meta = {
  title: 'Meet/MeetResultStep',
  component: MeetResultStep,
  parameters: {
    layout: 'centered',
  },
  tags: ['autodocs'],
} satisfies Meta<typeof MeetResultStep>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Playground: Story = {};
