import { ComponentPropsWithRef, ForwardedRef, forwardRef } from 'react';

import { listStyle } from './MenuList.style';

export interface MenuListProps extends ComponentPropsWithRef<'ul'> {
  isOpen?: boolean;
  position?: string;
}

const MenuList = (
  { isOpen, position = '50px', children, ...props }: MenuListProps,
  ref: ForwardedRef<HTMLUListElement>,
) => {
  return (
    isOpen && (
      <ul css={listStyle(position)} ref={ref} {...props}>
        {children}
      </ul>
    )
  );
};

export default forwardRef(MenuList);
