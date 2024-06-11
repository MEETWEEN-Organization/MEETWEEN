import { css } from '@emotion/react';
import type { Meta, StoryObj } from '@storybook/react';

import MeetSelectMidPointStep from '@/components/meet/MeetSelectMidPointStep/MeetSelectMidPointStep';

const meta = {
  title: 'Meet/MeetSelectMidPointStep',
  component: MeetSelectMidPointStep,
  parameters: {
    layout: 'centered',
  },
  tags: ['autodocs'],
  decorators: [
    (Story) => (
      <div
        css={css`
          width: 100vw;
        `}
      >
        <Story />
      </div>
    ),
  ],
} satisfies Meta<typeof MeetSelectMidPointStep>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Default: Story = {};
