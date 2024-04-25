import { SizeType } from '@/type/size';

import { ComponentPropsWithoutRef } from 'react';

import { COLOR } from '@constants/color';

import { badgeStyle, sizeStyle } from './Badge.style';

export interface IBadge extends Omit<ComponentPropsWithoutRef<'span'>, 'color'> {
  text: string;
  size?: Extract<SizeType, 'large' | 'medium' | 'small'>;
  color?: keyof typeof COLOR;
}

const Badge = ({ text, size = 'medium', color }: IBadge) => {
  return <span css={[badgeStyle(color), sizeStyle[size]]}>{text}</span>;
};

export default Badge;
