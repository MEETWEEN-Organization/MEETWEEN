import { ComponentPropsWithRef, ReactNode } from 'react';

import { getTextStyle } from '@/components/common/SupportingText/SupportingText.style';

export interface TextProps extends ComponentPropsWithRef<'span'> {
  isError?: boolean;
  children: ReactNode;
}

const SupportingText = ({ isError = false, children, ...props }: TextProps) => {
  return (
    <span css={getTextStyle(isError)} {...props}>
      {children}
    </span>
  );
};

export default SupportingText;
