import type { Meta, StoryObj } from '@storybook/react';

import Spinner from '@/components/common/Spinner/Spinner';

const meta = {
  title: 'Common/Spinner',
  component: Spinner,
  parameters: {
    layout: 'centered',
  },
  tags: [],
} satisfies Meta<typeof Spinner>;
type Story = StoryObj<typeof meta>;

export default meta;

export const LinearSpinner: Story = {
  args: {
    variant: 'linear',
  },
};

export const BezierSpinner: Story = {
  args: {
    variant: 'bezier',
  },
};
