import type { Meta, StoryObj } from '@storybook/react';

import { RecoilRoot } from 'recoil';

import Button from '@components/common/Button/Button';
import ToastContainer from '@components/common/ToastContainer/ToastContainer';
import ToastFactory from '@components/common/ToastFactory/ToastFactory';

import { useToast } from '@hooks/common';

import { listStyle } from '../style';

const meta = {
  title: 'Common/Toast',
  component: ToastFactory,
  parameters: {},
  decorators: [
    (Story) => (
      <RecoilRoot>
        <Story />
      </RecoilRoot>
    ),
  ],
  argTypes: {
    variant: {
      control: { type: 'radio' },
      options: ['default', 'success', 'error'],
    },
    hasCloseBtn: {
      control: { type: 'boolean' },
    },
    showDuration: {
      control: { type: 'number' },
    },
  },
  args: {
    variant: 'default',
    hasCloseBtn: false,
    showDuration: 5000,
    children: 'Some message',
  },
} satisfies Meta<typeof ToastFactory>;

export default meta;
type Story = StoryObj<typeof meta>;

export const PlayGround: Story = {
  render: () => {
    const { createToast } = useToast();

    return (
      <>
        <ToastContainer>
          <ToastFactory />
        </ToastContainer>
        <ul css={listStyle}>
          <Button variant="default" onClick={() => createToast('완료되었습니다.')}>
            Create Default
          </Button>
          <Button
            variant="primary"
            onClick={() => createToast('성공적으로 확인되었습니다.', 'success')}
          >
            Create Success
          </Button>
          <Button variant="secondary" onClick={() => createToast('다시 시도해주세요.', 'error')}>
            Create Error
          </Button>
        </ul>
      </>
    );
  },
};
