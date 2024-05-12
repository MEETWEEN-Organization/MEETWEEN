import type { Meta, StoryObj } from '@storybook/react';

import Toggle from '@/components/common/Toggle/Toggle';

const meta = {
  title: 'Common/Toggle',
  component: Toggle,
  parameters: {
    layout: 'centered',
  },

  args: {
    onToggle: (e: React.ChangeEvent<HTMLInputElement>) => {
      e;
    },
    isChecked: false,
  },
  argTypes: {
    isChecked: {
      control: false,
    },
  },
} satisfies Meta<typeof Toggle>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Default: Story = {};
