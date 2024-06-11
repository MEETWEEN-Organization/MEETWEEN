import type { Meta, StoryObj } from '@storybook/react';

import ToastContainer from '@/components/common/ToastContainer/ToastContainer';
import ToastFactory from '@/components/common/ToastFactory/ToastFactory';
import MeetResultCard from '@/components/meet/common/MeetResultCard/MeetResultCard';

import { listStyle } from '@/stories/style';

const meta = {
  title: 'Meet/MeetResultCard',
  component: MeetResultCard,
  parameters: {
    layout: 'centered',
  },
  tags: ['autodocs'],
  decorators: [
    (Story) => (
      <div css={listStyle}>
        <Story />
      </div>
    ),
  ],
  argTypes: {
    meetTitle: {
      control: { type: 'text' },
    },
    meetPlace: {
      control: { type: 'text' },
    },
    memberCount: {
      control: { type: 'range', min: 0, max: 10 },
    },
    meetTime: {
      control: { type: 'text' },
    },
    inviteCode: {
      control: { type: 'text' },
    },
  },
  args: {
    memberCount: 3,
    meetTime: '2024-06-21 18:00 PM',
    meetPlace: '서울특별시 강남구 강남역 2번출구',
    inviteCode: 123123,
  },
} satisfies Meta<typeof MeetResultCard>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Playground: Story = {
  render: (args) => {
    return (
      <>
        <MeetResultCard {...args} />
        <ToastContainer>
          <ToastFactory />
        </ToastContainer>
      </>
    );
  },
};
