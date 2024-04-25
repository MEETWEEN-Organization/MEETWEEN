import { ComponentPropsWithoutRef, forwardRef } from 'react';

import * as Styled from '@components/common/Counter/Counter.style';
import SupportingText from '@components/common/SupportingText/SupportingText';

export interface ICounter extends ComponentPropsWithoutRef<'div'> {
  count: number;
  isError?: boolean;
  supportingText?: string;
}

const Counter = ({ count, isError = false, supportingText, ...props }: ICounter) => {
  return (
    <div css={Styled.containerStyle}>
      <div css={Styled.counterStyle(isError)} {...props}>
        <p css={Styled.countTextStyle}>{count}</p>
      </div>
      <SupportingText isError={isError}>{supportingText}</SupportingText>
    </div>
  );
};

export default forwardRef(Counter);
