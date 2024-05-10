import { ComponentPropsWithoutRef, ElementType } from 'react';

import { BoxStyleProps, getBoxStyle } from '@/components/common/Box/Box.style';

interface BoxProps extends ComponentPropsWithoutRef<'div'> {
  tag?: ElementType;
  styles?: BoxStyleProps;
}

const Box = ({ tag = 'div', styles = {}, children, ...props }: BoxProps) => {
  const Tag = tag;
  return (
    <Tag css={getBoxStyle(styles)} {...props}>
      {children}
    </Tag>
  );
};

export default Box;
