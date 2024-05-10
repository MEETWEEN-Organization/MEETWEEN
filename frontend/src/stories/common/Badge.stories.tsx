import type { Meta, StoryObj } from '@storybook/react';

import Badge from '@components/common/Badge/Badge';

import { listStyle } from '../style';

const meta = {
  title: 'Common/Badge',
  component: Badge,
  parameters: {
    layout: 'centered',
  },
  tags: ['autodocs'],
  argTypes: {
    text: {
      control: 'text',
    },
    color: {
      control: 'radio',
      options: ['red', 'orange', 'yellow', 'green', 'skyblue', 'purple200', 'purple100', 'pink'],
    },
  },
  args: {
    text: 'Badge',
    color: 'red',
  },
} satisfies Meta<typeof Badge>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Colors: Story = {
  render: ({ text }) => (
    <ul css={listStyle}>
      <li>
        <Badge text={text} color="green" />
      </li>
      <li>
        <Badge text={text} color="orange" />
      </li>
      <li>
        <Badge text={text} color="pink" />
      </li>
      <li>
        <Badge text={text} color="purple100" />
      </li>
      <li>
        <Badge text={text} color="purple200" />
      </li>
      <li>
        <Badge text={text} color="red" />
      </li>
      <li>
        <Badge text={text} color="skyblue" />
      </li>
      <li>
        <Badge text={text} color="yellow" />
      </li>
    </ul>
  ),
};

export const Sizes: Story = {
  render: ({ text }) => (
    <ul css={listStyle}>
      <li>
        <Badge text={text} size="large" />
      </li>
      <li>
        <Badge text={text} size="medium" />
      </li>
      <li>
        <Badge text={text} size="small" />
      </li>
    </ul>
  ),
};
