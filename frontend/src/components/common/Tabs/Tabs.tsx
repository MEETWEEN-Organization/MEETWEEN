import { ComponentPropsWithRef, ReactNode } from 'react';

import { wrapperStyle } from './Tabs.style';

export interface ITabs extends ComponentPropsWithRef<'ul'> {
  children: ReactNode;
}

const Tabs = ({ children, ...props }: ITabs) => {
  return (
    <ul role="tablist" tabIndex={-1} css={wrapperStyle} {...props}>
      {children}
    </ul>
  );
};

export default Tabs;
