import type { Meta, StoryObj } from '@storybook/react';

import MeetPageTitle from '@/components/meet/common/MeetPageTitle/MeetPageTitle';

const meta = {
  title: 'Meet/MeetPageTitle',
  component: MeetPageTitle,
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
} satisfies Meta<typeof MeetPageTitle>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Playground: Story = {
  render: (args) => {
    return (
      <>
        <MeetPageTitle {...args} />
      </>
    );
  },
};
