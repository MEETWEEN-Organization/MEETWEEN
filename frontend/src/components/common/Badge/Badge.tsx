import { ComponentPropsWithoutRef } from 'react';

import { SizeType } from '@type/size';

import { COLOR } from '@constants/common';

import { badgeStyle, sizeStyle } from './Badge.style';

export interface BadgeProps extends Omit<ComponentPropsWithoutRef<'span'>, 'color'> {
  text: string;
  size?: Extract<SizeType, 'large' | 'medium' | 'small'>;
  color?: keyof typeof COLOR;
}

const Badge = ({ text, size = 'medium', color }: BadgeProps) => {
  return <span css={[badgeStyle(color), sizeStyle[size]]}>{text}</span>;
};

export default Badge;
