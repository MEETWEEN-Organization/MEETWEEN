import { ComponentPropsWithoutRef, ReactNode } from 'react';

import { textStyle } from '@/components/common/Text/Text.style';

import { SizeType } from '@/type/size';

export interface TextProps extends ComponentPropsWithoutRef<'p'> {
  size: Extract<SizeType, 'large' | 'medium' | 'small' | 'xSmall'>;
  children: ReactNode;
}

const Text = ({ size = 'medium', children, ...props }: TextProps) => {
  return (
    <p css={textStyle[size]} {...props}>
      {children}
    </p>
  );
};

export default Text;
