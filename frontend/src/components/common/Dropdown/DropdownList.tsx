import { ComponentPropsWithoutRef } from 'react';

import { listStyle } from '@/components/common/Dropdown/Dropdown.style';

interface DropdownListProps extends ComponentPropsWithoutRef<'ul'> {
  isOpen?: boolean;
}

const DropdownList = ({ isOpen, children, ...props }: DropdownListProps) => {
  return (
    isOpen && (
      <ul css={listStyle} {...props}>
        {children}
      </ul>
    )
  );
};

export default DropdownList;
