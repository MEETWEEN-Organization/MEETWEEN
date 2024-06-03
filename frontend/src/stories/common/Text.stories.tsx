import type { Meta, StoryObj } from '@storybook/react';

import Text, { TextProps } from '@/components/common/Text/Text';

import { containerStyle, listStyle } from '@/stories/style';

const meta = {
  title: 'Common/Text',
  component: Text,
  argTypes: {
    size: {
      control: 'radio',
      options: ['large', 'medium', 'small', 'xSmall'],
    },
    children: {
      control: 'text',
    },
  },
  args: {
    size: 'medium',
    children: 'Text',
  },
  decorators: [
    (Story) => (
      <div css={containerStyle}>
        <Story />
      </div>
    ),
  ],
} satisfies Meta<typeof Text>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Sizes: Story = {
  render: () => (
    <ul css={listStyle}>
      <li>
        <Text size="large">Large</Text>
      </li>
      <li>
        <Text size="medium">Medium</Text>
      </li>
      <li>
        <Text size="small">Small</Text>
      </li>
      <li>
        <Text size="xSmall">xSmall</Text>
      </li>
    </ul>
  ),
  argTypes: {
    size: {
      control: false,
    },
  },
};

const createTextStory = (size: TextProps['size']) => ({
  args: {
    size,
  },
  argTypes: {
    size: {
      control: false,
    },
  },
});

export const XSmall: Story = createTextStory('xSmall');

export const Small: Story = createTextStory('small');

export const Medium: Story = createTextStory('medium');

export const Large: Story = createTextStory('large');
