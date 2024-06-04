import { css } from '@emotion/react';

import { Theme } from '@/styles/theme/theme';

export const listStyle = css({
  display: 'flex',
  flexDirection: 'column',

  position: 'absolute',

  transform: 'translateY(50px)',

  minWidth: '140px',

  zIndex: Theme.zIndex.overlayHigh,

  padding: `${Theme.spacing.spacing2} 0`,

  backgroundColor: Theme.color.white,
  borderRadius: Theme.borderRadius.medium,

  boxShadow: Theme.boxShadow.blur100,

  transition: 'all 0.2s ease-in',
});
