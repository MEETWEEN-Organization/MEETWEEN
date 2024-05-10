import type { Meta, StoryObj } from '@storybook/react';

import Menu from '@components/common/Menu/Menu';
import MenuItem from '@components/common/MenuItem/MenuItem';
import MenuList from '@components/common/MenuList/MenuList';
import Profile from '@components/common/Profile/Profile';

import { useOverlay } from '@hooks/common';

import user from '@assets/profile_sample.jpeg';

import { containerStyle } from '../style';

const meta = {
  title: 'Common/Menu',
  component: Menu,

  decorators: [
    (Story) => (
      <div css={containerStyle}>
        <Story />
      </div>
    ),
  ],

  argTypes: {},
  args: {},
} satisfies Meta<typeof Menu>;

export default meta;
type Story = StoryObj<typeof meta>;

export const PlayGround: Story = {
  render: () => {
    const { isOpen, open, close } = useOverlay();
    return (
      <Menu onCloseMenu={close}>
        <Profile onClick={open} imgUrl={user} />
        <MenuList isOpen={isOpen}>
          <MenuItem>내 약속 보러가기</MenuItem>
          <MenuItem>로그아웃</MenuItem>
        </MenuList>
      </Menu>
    );
  },
};
