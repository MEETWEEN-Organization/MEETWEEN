import type { Meta, StoryObj } from '@storybook/react';

import { useState } from 'react';

import Button from '@components/common/Button/Button';
import ProgressBar from '@components/common/ProgressBar/ProgressBar';

import { containerStyle } from '../style';

const meta = {
  title: 'Common/ProgressBar',
  component: ProgressBar,
  decorators: [
    (Story) => (
      <div css={containerStyle}>
        <Story />
      </div>
    ),
  ],
  argTypes: {
    degree: {
      control: 'number',
    },
  },
  args: {
    degree: 0,
  },
} satisfies Meta<typeof ProgressBar>;

export default meta;
type Story = StoryObj<typeof meta>;

export const PlayGround: Story = {
  render: () => {
    const [count, setCount] = useState(0);
    return (
      <div
        css={[
          containerStyle,
          {
            display: 'flex',
            flexDirection: 'column',
          },
        ]}
      >
        <ProgressBar degree={count} maxLength={6} />
        <Button onClick={() => setCount((prev) => prev + 1)}>Next Step</Button>
      </div>
    );
  },
};
