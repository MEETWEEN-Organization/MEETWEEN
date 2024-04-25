import { ComponentPropsWithoutRef } from 'react';

import { SizeType } from '@type/size';

import { headingStyle } from './Heading.style';

export interface IHeading extends ComponentPropsWithoutRef<'h3'> {
  size?: SizeType;
}

/* SizeType의 union type별 태그 */
const TAG_BY_SIZE = {
  xxLarge: 'h1',
  xLarge: 'h2',
  large: 'h3',
  medium: 'h4',
  small: 'h5',
  xSmall: 'h6',
};

const Heading = ({ size = 'large', ...props }: IHeading) => {
  const Tag = TAG_BY_SIZE[size] as JSX.ElementType;

  return (
    <Tag css={headingStyle(size)} {...props}>
      {props.children}
    </Tag>
  );
};

export default Heading;
