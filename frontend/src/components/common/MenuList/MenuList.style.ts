import { css } from '@emotion/react';

import { Theme } from '@/styles/theme/theme';

import { MenuListProps } from './MenuList';

export const listStyle = (position: Required<MenuListProps>['position']) =>
  css({
    position: 'absolute',

    zIndex: Theme.zIndex.overlayHigh,

    borderRadius: Theme.borderRadius.medium,
    boxShadow: Theme.boxShadow.blur200,

    transform: `translateY(${position})`,

    transition: 'all 0.2s ease-in',
  });
