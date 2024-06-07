import { ComponentPropsWithoutRef } from 'react';

import { headingStyle, textStyle } from '@/components/common/Heading/Heading.style';

import { SizeType } from '@/type/size';

export interface HeadingProps extends ComponentPropsWithoutRef<'h3'> {
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

const Heading = ({ size = 'large', ...props }: HeadingProps) => {
  const Tag = TAG_BY_SIZE[size] as JSX.ElementType;

  return (
    <Tag css={[headingStyle(size), textStyle]} {...props}>
      {props.children}
    </Tag>
  );
};

export default Heading;
