import { ComponentPropsWithoutRef, ElementType } from 'react';

import { IFlexStyle, getFlexStyle } from './Flex.style';

interface IFlexProps extends ComponentPropsWithoutRef<'div'> {
  tag?: ElementType;
  styles?: IFlexStyle;
}

const Flex = ({ tag = 'div', styles = {}, children }: IFlexProps) => {
  const Tag = tag;

  return <Tag css={getFlexStyle(styles)}>{children}</Tag>;
};

export default Flex;
