import { ComponentPropsWithoutRef, useCallback } from 'react';

import { itemStyle } from './MenuItem.style';

/* eslint-disable jsx-a11y/no-noninteractive-element-to-interactive-role */

interface MenuItemProps extends ComponentPropsWithoutRef<'li'> {
  onClick?: () => void;
}

const MenuItem = ({ onClick, children, ...props }: MenuItemProps) => {
  const handleKeyDown = useCallback(() => {
    onClick?.();
  }, [onClick]);

  return (
    <li
      role="button"
      aria-label="메뉴 아이템"
      tabIndex={0}
      css={itemStyle}
      onClick={onClick}
      onKeyDown={handleKeyDown}
      {...props}
    >
      {children}
    </li>
  );
};

export default MenuItem;
