import type { Meta, StoryObj } from '@storybook/react';

import Heading from '@/components/common/Heading/Heading';

import { listStyle } from '../style';

const meta = {
  title: 'Common/Heading',
  component: Heading,
  tags: ['autodocs'],
  parameters: {
    layout: 'centered',
  },
  argTypes: {
    size: {
      control: 'radio',
    },
  },
  args: {
    size: 'large',
  },
} satisfies Meta<typeof Heading>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Sizes: Story = {
  render: () => (
    <ul css={listStyle}>
      <li>
        <Heading size="xxLarge">Heading 1</Heading>
      </li>
      <li>
        <Heading size="xLarge">Heading 2</Heading>
      </li>
      <li>
        <Heading size="large">Heading 3</Heading>
      </li>
      <li>
        <Heading size="medium">Heading 4</Heading>
      </li>
      <li>
        <Heading size="small">Heading 5</Heading>
      </li>
      <li>
        <Heading size="xSmall">Heading 6</Heading>
      </li>
    </ul>
  ),

  argTypes: {
    size: {
      control: false,
    },
  },
};
