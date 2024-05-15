import type { Meta, StoryObj } from '@storybook/react';

import Box from '@/components/common/Box/Box';

import { Theme } from '@/styles/theme/theme';

import { listStyle } from '../style';

const meta = {
  title: 'Common/Box',
  component: Box,
} satisfies Meta<typeof Box>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Default: Story = {
  render: (args) => (
    <ul css={listStyle}>
      <Box
        styles={{
          ...args.styles,
          backgroundColor: Theme.color.gray400,
          color: Theme.color.white,
          padding: '14px 16px',
        }}
      >
        Box 1
      </Box>
      <Box
        styles={{
          ...args.styles,
          boxShadow: Theme.boxShadow.blur300,
          padding: '10px 20px',
          margin: '14px',
        }}
      >
        Box 2
      </Box>
    </ul>
  ),

  args: {
    styles: { borderRadius: '6px' },
  },
  argTypes: {
    styles: {
      control: {
        type: 'object',
      },
    },
  },
};
