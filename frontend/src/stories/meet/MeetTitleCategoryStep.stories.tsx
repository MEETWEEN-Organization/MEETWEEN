import type { Meta, StoryObj } from '@storybook/react';

import MeetTitleCategoryStep from '@/components/meet/MeetTitleCategoryStep/MeetTitleCategoryStep';

const meta = {
  title: 'Meet/MeetTitleCategoryStep',
  component: MeetTitleCategoryStep,
  parameters: {
    layout: 'centered',
  },
  tags: ['autodocs'],
} satisfies Meta<typeof MeetTitleCategoryStep>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Playground: Story = {};
