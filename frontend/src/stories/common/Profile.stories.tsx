import type { Meta, StoryObj } from '@storybook/react';

import Profile from '@components/common/Profile/Profile';

import icon from '@assets/profile_sample.jpeg';

const meta = {
  title: 'Common/Profile',
  component: Profile,
  parameters: {
    layout: 'centered',
  },
  argTypes: {
    imgUrl: {
      control: 'text',
    },
  },
  args: {
    imgUrl: icon,
  },
} satisfies Meta<typeof Profile>;

export default meta;
type Story = StoryObj<typeof meta>;

export const PlayGround: Story = {};
