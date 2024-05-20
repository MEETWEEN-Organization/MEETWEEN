import { ComponentPropsWithRef, ReactElement, ReactNode } from 'react';

import { buttonStyle, sizeStyle, variantStyle } from '@/components/common/Button/Button.style';

import { SizeType } from '@/type/size';

export interface ButtonProps extends ComponentPropsWithRef<'button'> {
  variant?: 'primary' | 'secondary' | 'outline' | 'default' | 'text';
  size?: Extract<SizeType, 'xLarge' | 'large' | 'medium' | 'small'>;
  icon?: ReactElement;
  children: ReactNode;
}

const Button = ({ variant = 'default', size = 'medium', icon, ...props }: ButtonProps) => {
  return (
    <button {...props} css={[buttonStyle, variantStyle[variant], sizeStyle[size]]}>
      {icon}
      {props.children}
    </button>
  );
};

export default Button;
