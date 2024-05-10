import type { Meta, StoryObj } from '@storybook/react';

import Button, { ButtonProps } from '@components/common/Button/Button';

import Icon from '@assets/svg/create.svg?react';

const meta = {
  title: 'Common/Button',
  component: Button,
  parameters: {
    layout: 'centered',
  },
  tags: ['autodocs'],
  argTypes: {
    variant: {
      control: { type: 'radio' },
      options: ['primary', 'secondary', 'default', 'outline'],
    },
    size: {
      control: { type: 'radio' },
      options: ['xLarge', 'large', 'medium', 'small'],
    },
    children: {
      control: { type: 'text' },
    },
  },
  args: {
    variant: 'default',
    size: 'medium',
    children: 'Button',
  },
} satisfies Meta<typeof Button>;

export default meta;
type Story = StoryObj<typeof meta>;

const createButtonStory = (variant: ButtonProps['variant']) => ({
  args: {
    variant,
  },
  argsType: {
    variant: {
      control: false,
    },
  },
});

export const Default: Story = createButtonStory('default');

export const Primary: Story = createButtonStory('primary');

export const Secondary: Story = createButtonStory('secondary');

export const Outline: Story = createButtonStory('outline');

export const WithIcon: Story = {
  args: {
    icon: <Icon />,
  },
  argTypes: {
    icon: {
      control: false,
    },
  },
};
