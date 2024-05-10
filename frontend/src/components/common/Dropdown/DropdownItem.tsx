import { ComponentPropsWithoutRef } from 'react';

import { itemStyle } from '@/components/common/Dropdown/Dropdown.style';

interface DropdownItemProps extends Omit<ComponentPropsWithoutRef<'li'>, 'onSelect'> {
  onSelect?: () => void;
}

/* eslint-disable jsx-a11y/no-noninteractive-element-to-interactive-role */

const DropdownItem = ({ onSelect, children, ...props }: DropdownItemProps) => {
  const handleKeyDown = (e: React.KeyboardEvent) => {
    const { key } = e;
    if (key === 'Enter') {
      onSelect?.();
    }
  };
  return (
    <li
      role="button"
      tabIndex={0}
      css={itemStyle}
      onKeyDown={handleKeyDown}
      onClick={onSelect}
      {...props}
    >
      {children}
    </li>
  );
};

export default DropdownItem;
