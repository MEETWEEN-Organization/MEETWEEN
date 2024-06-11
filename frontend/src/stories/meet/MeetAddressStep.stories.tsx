import type { Meta, StoryObj } from '@storybook/react';

import MeetAddressStep from '@/components/meet/MeetAddressStep/MeetAddressStep';

const meta = {
  title: 'Meet/MeetAddressStep',
  component: MeetAddressStep,
  parameters: {
    layout: 'centered',
  },
  tags: ['autodocs'],
} satisfies Meta<typeof MeetAddressStep>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Default: Story = {};
