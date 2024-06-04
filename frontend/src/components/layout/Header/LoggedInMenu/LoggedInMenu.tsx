import Menu from '@/components/common/Menu/Menu';
import MenuItem from '@/components/common/MenuItem/MenuItem';
import MenuList from '@/components/common/MenuList/MenuList';
import Profile from '@/components/common/Profile/Profile';
import { imgStyle, listStyle } from '@/components/layout/Header/LoggedInMenu/LoggedInMenu.style';

import { useOverlay } from '@/hooks/common';

import tmp from '@/assets/img/profile_sample.jpeg';

const LoggedInMenu = () => {
  const { isOpen, close, open } = useOverlay();

  return (
    <Menu onCloseMenu={close}>
      <Profile onClick={open} imgUrl={tmp} css={imgStyle} />
      <MenuList css={listStyle} isOpen={isOpen}>
        <MenuItem>내 약속 보러가기</MenuItem>
        <MenuItem>로그아웃</MenuItem>
      </MenuList>
    </Menu>
  );
};

export default LoggedInMenu;
