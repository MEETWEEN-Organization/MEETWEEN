import { ComponentPropsWithoutRef } from 'react';

import { useOutsideClick } from '@hooks/common';

import { menuStyle } from './Menu.style';

export interface MenuProps extends ComponentPropsWithoutRef<'div'> {
  onCloseMenu?: () => void;
}

const Menu = ({ onCloseMenu = () => {}, children, ...props }: MenuProps) => {
  const targetRef = useOutsideClick<HTMLDivElement>(onCloseMenu);

  return (
    <div css={menuStyle} ref={targetRef} {...props}>
      {children}
    </div>
  );
};

export default Menu;
