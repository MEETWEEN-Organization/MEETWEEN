import { ComponentPropsWithRef, ReactNode } from 'react';

import { getTextStyle } from './SupportingText.style';

export interface IText extends ComponentPropsWithRef<'span'> {
  isError?: boolean;
  children: ReactNode;
}

const SupportingText = ({ isError = false, children, ...props }: IText) => {
  return (
    <span css={getTextStyle(isError)} {...props}>
      {children}
    </span>
  );
};

export default SupportingText;
