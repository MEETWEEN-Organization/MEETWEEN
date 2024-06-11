import type { Meta, StoryObj } from '@storybook/react';

import MeetAddressForm from '@/components/meet/common/MeetAddressForm/MeetAddressForm';

const meta = {
  title: 'Meet/MeetAddressForm',
  component: MeetAddressForm,
  parameters: {
    layout: 'centered',
  },
  tags: ['autodocs'],
  argTypes: {
    addressCount: {
      controls: {
        type: 'text',
      },
    },
  },
} satisfies Meta<typeof MeetAddressForm>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Playground: Story = {
  render: () => {
    return <MeetAddressForm addressCount={7} />;
  },
};
