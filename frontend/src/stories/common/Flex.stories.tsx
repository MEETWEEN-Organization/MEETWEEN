import Heading from '@/components/common/Heading/Heading';
import { Theme } from '@/styles/theme/theme';
import type { Meta, StoryObj } from '@storybook/react';

import Box from '@components/common/Box/Box';
import Flex from '@components/common/Flex/Flex';

const meta = {
  title: 'Common/Flex',
  component: Flex,

  decorators: [
    (Story) => (
      <ul>
        <Story />
      </ul>
    ),
  ],
} satisfies Meta<typeof Flex>;

export default meta;
type Story = StoryObj<typeof meta>;

export const FlexColumn: Story = {
  render: (args) => (
    <Flex
      styles={{
        ...args.styles,
        width: '300px',
        direction: 'column',
      }}
    >
      <Heading size="medium">First</Heading>
      <Box
        styles={{
          width: '100%',
          padding: '12px 16px',
          backgroundColor: Theme.color.blue400,
          borderRadius: Theme.borderRadius.medium,
        }}
      >
        Box 1
      </Box>
      <Heading size="medium">Second</Heading>
      <Box
        styles={{
          width: '100%',
          padding: '12px 16px',
          backgroundColor: Theme.color.gray400,
          borderRadius: Theme.borderRadius.medium,
        }}
      >
        Box 2
      </Box>
      <Heading size="medium">Third</Heading>
      <Box
        styles={{
          width: '100%',
          padding: '12px 16px',
          backgroundColor: Theme.color.red200,
          borderRadius: Theme.borderRadius.medium,
        }}
      >
        Box 3
      </Box>
    </Flex>
  ),

  argTypes: {
    styles: {
      control: {
        type: 'object',
      },
    },
  },
  args: {
    styles: {
      align: 'flex-end',
    },
  },
};

export const FlexRow: Story = {
  render: (args) => (
    <Flex
      styles={{
        ...args.styles,
        direction: 'row',
        justify: 'space-around',
        align: 'center',
        gap: '12px',
      }}
    >
      <Heading size="medium">First</Heading>
      <Box
        styles={{
          padding: '12px 16px',
          backgroundColor: Theme.color.blue400,
          borderRadius: Theme.borderRadius.medium,
        }}
      >
        Box 1
      </Box>
      <Heading size="medium">Second</Heading>
      <Box
        styles={{
          padding: '12px 16px',
          backgroundColor: Theme.color.gray400,
          borderRadius: Theme.borderRadius.medium,
        }}
      >
        Box 2
      </Box>
      <Heading size="medium">Third</Heading>
      <Box
        styles={{
          padding: '12px 16px',
          backgroundColor: Theme.color.red200,
          borderRadius: Theme.borderRadius.medium,
        }}
      >
        Box 3
      </Box>
    </Flex>
  ),
};
