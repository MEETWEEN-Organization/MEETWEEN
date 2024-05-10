import type { Meta, StoryObj } from '@storybook/react';

import { useState } from 'react';

import ColorPicker from '@components/common/ColorPicker/ColorPicker';

import { containerStyle } from '@stories/style';

const meta = {
  title: 'Common/ColorPicker',
  component: ColorPicker,
  parameters: {},
  tags: [],
  decorators: [
    (Story) => (
      <div css={containerStyle}>
        <Story />
      </div>
    ),
  ],
  args: {
    color: 'black',
    onSelectColor: () => {},
  },
} satisfies Meta<typeof ColorPicker>;

export default meta;
type Story = StoryObj<typeof meta>;

export const PlayGround: Story = {
  render: () => {
    const [color, setColor] = useState('');

    console.log(color);

    const handleSelect = (color: string) => {
      setColor(color);
    };

    return <ColorPicker color={color} onSelectColor={handleSelect} label="카테고리 색상" />;
  },
};
