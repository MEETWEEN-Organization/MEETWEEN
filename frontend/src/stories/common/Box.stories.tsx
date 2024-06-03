import { ThemeProvider } from '@emotion/react';
import type { Meta, StoryObj } from '@storybook/react';

import Box from '@/components/common/Box/Box';

import { Theme } from '@/styles/theme/theme';

import { listStyle } from '../style';

const meta = {
  title: 'Common/Box',
  component: Box,
  decorators: [
    (Story) => (
      <ThemeProvider theme={Theme}>
        <Story />
      </ThemeProvider>
    ),
  ],
  args: {
    styles: {
      borderRadius: Theme.borderRadius.medium,
      padding: '12px 16px',
    },
  },
} satisfies Meta<typeof Box>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Default: Story = {
  render: (args) => (
    <ul css={listStyle}>
      <li>
        <Box
          styles={{
            ...args.styles,
            backgroundColor: Theme.color.blue600,
            color: Theme.color.white,
          }}
        >
          Box 1
        </Box>
      </li>
      <li>
        <Box
          styles={{
            ...args.styles,
            backgroundColor: Theme.color.gray600,
            color: Theme.color.white,
          }}
        >
          Box 1
        </Box>
      </li>
    </ul>
  ),
};
