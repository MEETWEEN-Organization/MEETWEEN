import { css } from '@emotion/react';
import type { Meta, StoryObj } from '@storybook/react';

import Card from '@/components/common/Card/Card';

const meta = {
  title: 'Common/Card',
  component: Card,
  parameters: {
    layout: 'centered',
  },
  tags: [],
  argTypes: {
    variant: {
      control: {
        type: 'radio',
      },
      options: ['create', 'join', 'myInfo'],
    },
  },
  args: {
    variant: 'create',
  },
} satisfies Meta<typeof Card>;
type Story = StoryObj<typeof meta>;

export default meta;

export const Variants: Story = {
  render: (args) => {
    return (
      <section
        css={css`
          display: flex;
          gap: 24px;
        `}
      >
        <Card {...args} variant="create" />
        <Card {...args} variant="join" />
        <Card {...args} variant="myInfo" />
      </section>
    );
  },
};
