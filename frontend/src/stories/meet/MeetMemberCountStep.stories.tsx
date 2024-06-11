import type { Meta, StoryObj } from '@storybook/react';

import MeetMemberCountStep from '@/components/meet/MeetMemberCountStep/MeetMemberCountStep';

const meta = {
  title: 'Meet/MeetMemberCountForm',
  component: MeetMemberCountStep,
  parameters: {
    layout: 'centered',
  },
  tags: ['autodocs'],
} satisfies Meta<typeof MeetMemberCountStep>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Playground: Story = {};
