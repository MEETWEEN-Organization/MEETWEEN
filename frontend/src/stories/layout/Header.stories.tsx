import type { Meta, StoryObj } from '@storybook/react';

import Header from '@/components/layout/Header/Header';

const meta = {
  title: 'Layout/Header',
  component: Header,
  tags: ['autodocs'],
} satisfies Meta<typeof Header>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Default: Story = {};
