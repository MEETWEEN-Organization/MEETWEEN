import type { Meta, StoryObj } from '@storybook/react';

import MeetStepTitle from '@/components/meet/common/MeetStepTitle/MeetStepTitle';

const meta = {
  title: 'Meet/MeetStepTitle',
  component: MeetStepTitle,
  parameters: {
    layout: 'centered',
  },
  tags: ['autodocs'],
  argTypes: {
    mainDescription: {
      control: {
        type: 'text',
      },
    },
    subDescription: {
      control: {
        type: 'text',
      },
    },
  },
  args: {
    mainDescription: '메인 타이틀 제목 타이틀입니다. 제목 타이틀',
    subDescription: '서브 타이틀 서브 타이틀 서브 타이틀 서브 타이틀 서브 타이틀 서브 타이틀',
  },
} satisfies Meta<typeof MeetStepTitle>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Playground: Story = {
  render: (args) => {
    return (
      <>
        <MeetStepTitle {...args} />
      </>
    );
  },
};
