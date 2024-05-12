import type { Meta, StoryObj } from '@storybook/react';

import Input from '@/components/common/Input/Input';

import Icon from '@/assets/svg/react.svg?react';

const meta = {
  title: 'Common/Input',
  component: Input,
  parameters: {
    layout: 'centered',
  },
  tags: ['autodocs'],
  argTypes: {
    variant: {
      control: { type: 'radio' },
      options: ['default', 'text'],
    },
    size: {
      control: { type: 'radio' },
      options: ['large', 'medium', 'small'],
    },
    label: {
      control: { type: 'text' },
    },
    supportingText: {
      control: { type: 'text' },
    },
  },
  args: {
    variant: 'default',
    size: 'medium',
    label: '라벨입니다.',
    placeholder: '입력해주세요.',
  },
} satisfies Meta<typeof Input>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Sizes: Story = {
  render: ({ variant, isError, placeholder }) => (
    <ul>
      <li>
        <h6>Large</h6>
        <Input size="large" variant={variant} isError={isError} placeholder={placeholder} />
      </li>
      <li>
        <h6>Medium</h6>
        <Input size="medium" variant={variant} isError={isError} placeholder={placeholder} />
      </li>
      <li>
        <h6>Small</h6>
        <Input size="small" variant={variant} isError={isError} placeholder={placeholder} />
      </li>
    </ul>
  ),
  argTypes: {
    size: {
      control: false,
    },
  },
};

export const Default: Story = {
  args: {
    variant: 'default',
  },
  argTypes: {
    variant: {
      control: false,
    },
  },
};

export const Text: Story = {
  args: {
    variant: 'text',
  },
  argTypes: {
    variant: {
      control: false,
    },
  },
};

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

export const Error: Story = {
  args: {
    isError: true,
  },
  argTypes: {
    isError: {
      control: false,
    },
  },
};
