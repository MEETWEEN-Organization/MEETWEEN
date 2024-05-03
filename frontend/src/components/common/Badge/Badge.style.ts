import { css } from '@emotion/react';

import { COLOR } from '@constants/common';

import { Theme } from '@styles/theme/theme';

import { BadgeProps } from './Badge';

export const badgeStyle = (color: BadgeProps['color']) =>
  css({
    color: Theme.color.white,
    backgroundColor: color ? COLOR[color] : Theme.color.gray200,

    border: 'none',
    borderRadius: Theme.borderRadius.xLarge,
  });

export const sizeStyle = {
  large: css({
    padding: '12px 18px',

    fontSize: Theme.text.large.fontSize,
    lineHeight: Theme.text.large.lineHeight,
  }),

  medium: css({
    padding: '8px 16px',

    fontSize: Theme.text.medium.fontSize,
    lineHeight: Theme.text.medium.lineHeight,
  }),

  small: css({
    padding: '4px 12px',

    fontSize: Theme.text.small.fontSize,
    lineHeight: Theme.text.small.lineHeight,
  }),
};
