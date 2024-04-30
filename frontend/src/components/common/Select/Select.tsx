import { ComponentPropsWithRef, ForwardedRef, forwardRef } from 'react';

import Label from '@components/common/Label/Label';
import SupportingText from '@components/common/SupportingText/SupportingText';

import { SizeType } from '@type/size';

import DownArrow from '@assets/svg/select-arrow.svg?react';

import * as Styled from './Select.style';

export interface SelectProps extends Omit<ComponentPropsWithRef<'select'>, 'size'> {
  label?: string;
  size?: Extract<SizeType, 'large' | 'medium' | 'small'>;
  variant?: 'default' | 'outline';
  children: JSX.Element | JSX.Element[];
  isError?: boolean;
  supportingText?: string;
}

const Select = (
  {
    label,
    variant = 'default',
    size = 'medium',
    isError = false,
    supportingText,
    children,
    ...props
  }: SelectProps,
  ref: ForwardedRef<HTMLSelectElement>,
) => {
  return (
    <div css={Styled.wrapperStyle}>
      {label && <Label id={props.id}>{label}</Label>}
      <div css={Styled.selectWrapperStyle(variant, isError)}>
        <select
          css={[Styled.selectStyle(variant, isError), Styled.sizeStyle[size]]}
          ref={ref}
          {...props}
        >
          {children}
        </select>
        <DownArrow width="28" height="28" />
      </div>
      {supportingText && <SupportingText isError={isError}>{supportingText}</SupportingText>}
    </div>
  );
};

export default forwardRef(Select);
