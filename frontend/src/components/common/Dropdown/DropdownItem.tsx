import { ComponentPropsWithoutRef } from 'react';

import { itemStyle } from './Dropdown.style';

interface DropdownItemProps extends Omit<ComponentPropsWithoutRef<'li'>, 'onSelect'> {
  onSelect?: () => void;
}

/* eslint-disable jsx-a11y/click-events-have-key-events */

/* eslint-disable jsx-a11y/no-noninteractive-element-interactions */
const DropdownItem = ({ onSelect, children, ...props }: DropdownItemProps) => {
  return (
    <li css={itemStyle} onClick={onSelect} {...props}>
      {children}
    </li>
  );
};

export default DropdownItem;
