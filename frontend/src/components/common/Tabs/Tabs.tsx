import { ComponentPropsWithRef, ReactNode } from 'react';

import { wrapperStyle } from './Tabs.style';

export interface TabsProps extends ComponentPropsWithRef<'ul'> {
  children: ReactNode;
}

const Tabs = ({ children, ...props }: TabsProps) => {
  return (
    <ul role="tablist" tabIndex={-1} css={wrapperStyle} {...props}>
      {children}
    </ul>
  );
};

export default Tabs;
