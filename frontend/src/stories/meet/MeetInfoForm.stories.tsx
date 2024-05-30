import type { Meta, StoryObj } from '@storybook/react';

import { useState } from 'react';

import MeetInfoForm from '@/components/meet/common/MeetInfoForm/MeetInfoForm';

import { COLOR_PALLETE } from '@/constants/common';

const meta = {
  title: 'Meet/MeetInfoForm',
  component: MeetInfoForm,
  parameters: {
    layout: 'centered',
  },
  tags: ['autodocs'],
  argTypes: {},
} satisfies Meta<typeof MeetInfoForm>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Playground: Story = {
  render: () => {
    const [color, setColor] = useState<string>(COLOR_PALLETE[0]);

    const handleSelect = (color: string) => {
      setColor(color);
    };

    return <MeetInfoForm onSelectColor={handleSelect} selectedColor={color} />;
  },
};
