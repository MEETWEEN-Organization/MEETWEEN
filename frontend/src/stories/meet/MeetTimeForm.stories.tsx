import type { Meta, StoryObj } from '@storybook/react';

import MeetTimeForm from '@/components/meet/common/MeetTimeForm/MeetTimeForm';

const meta = {
  title: 'Meet/MeetTimeForm',
  component: MeetTimeForm,
  parameters: {
    layout: 'centered',
  },
  tags: ['autodocs'],
  argTypes: {},
} satisfies Meta<typeof MeetTimeForm>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Playground: Story = {
  render: () => {
    return <MeetTimeForm />;
  },
};
