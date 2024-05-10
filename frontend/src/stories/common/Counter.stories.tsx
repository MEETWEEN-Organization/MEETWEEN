import type { Meta, StoryObj } from '@storybook/react';

import Counter from '@components/common/Counter/Counter';

import { containerStyle } from '../style';

const meta = {
  title: 'Common/Counter',
  component: Counter,
  decorators: [
    (Story) => (
      <div css={containerStyle}>
        <Story />
      </div>
    ),
  ],
  args: {
    count: 0,
  },
} satisfies Meta<typeof Counter>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Default: Story = {
  args: {
    count: 0,
  },
  argTypes: {
    count: {
      control: 'text',
    },
  },
};

export const Error: Story = {
  args: {
    isError: true,
    supportingText: '에러 발생',
  },
};
