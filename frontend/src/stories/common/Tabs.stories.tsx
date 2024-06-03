import type { Meta, StoryObj } from '@storybook/react';

import { useState } from 'react';

import Tab from '@/components/common/Tab/Tab';
import Tabs from '@/components/common/Tabs/Tabs';

import { containerStyle } from '@/stories/style';

const meta = {
  title: 'Common/Tabs',
  component: Tabs,
  decorators: [
    (Story) => (
      <div css={containerStyle}>
        <Story />
      </div>
    ),
  ],
  args: {
    children: <></>,
  },
} satisfies Meta<typeof Tabs>;

export default meta;
type Story = StoryObj<typeof meta>;

export const PlayGround: Story = {
  render: () => {
    const [id, setId] = useState<'1' | '2'>('1');

    const handleChange = () => {
      setId((prev) => (prev === '1' ? '2' : '1'));
    };

    return (
      <Tabs>
        <Tab text="Tab 1" tabId="1" selectedId={id} changeSelect={handleChange} />
        <Tab text="Tab 2" tabId="2" selectedId={id} changeSelect={handleChange} />
      </Tabs>
    );
  },
};
