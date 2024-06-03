import { ComponentPropsWithoutRef } from 'react';

import { badgeStyle, sizeStyle } from '@/components/common/Badge/Badge.style';

import { SizeType } from '@/type/size';

import { COLOR } from '@/constants/common';

export interface BadgeProps extends Omit<ComponentPropsWithoutRef<'span'>, 'color'> {
  text: string;
  size?: Extract<SizeType, 'large' | 'medium' | 'small'>;
  color?: keyof typeof COLOR;
}

const Badge = ({ text, size = 'medium', color, ...props }: BadgeProps) => {
  return (
    <span css={[badgeStyle(color), sizeStyle[size]]} {...props}>
      {text}
    </span>
  );
};

export default Badge;
