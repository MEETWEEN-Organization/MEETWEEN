import type { Meta, StoryObj } from '@storybook/react';

import MeetDateStep from '@/components/meet/MeetDateStep/MeetDateStep';

const meta = {
  title: 'Meet/MeetDateStep',
  component: MeetDateStep,
  parameters: {
    layout: 'centered',
  },
  tags: ['autodocs'],
} satisfies Meta<typeof MeetDateStep>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Default: Story = {};
