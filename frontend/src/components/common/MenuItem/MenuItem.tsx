import { ComponentPropsWithoutRef } from 'react';

import { itemStyle } from './MenuItem.style';

interface MenuItemProps extends ComponentPropsWithoutRef<'li'> {
  onClick?: () => void;
}

const MenuItem = ({ onClick, children, ...props }: MenuItemProps) => {
  return (
    <li css={itemStyle} onClick={onClick} {...props}>
      {children}
    </li>
  );
};

export default MenuItem;
