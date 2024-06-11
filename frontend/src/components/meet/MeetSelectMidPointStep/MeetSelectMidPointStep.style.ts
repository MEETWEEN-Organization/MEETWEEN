import { css } from '@emotion/react';

import { Theme } from '@/styles/theme/theme';

export const listStyle = css({
  display: 'flex',
  flexDirection: 'column',
  alignItems: 'center',
  gap: Theme.spacing.spacing5,
});

export const buttonStyle = css({
  width: '180px',

  justifySelf: 'end',
});

export const containerStyle = css({
  display: 'flex',
  flexDirection: 'column',
  alignItems: 'center',
  gap: Theme.spacing.spacing5,

  position: 'relative',

  height: 'calc(100vh - 66px)',

  padding: `${Theme.spacing.spacing7} ${Theme.spacing.spacing5}`,

  '& > button': {
    position: 'absolute',
    bottom: Theme.spacing.spacing7,
  },

  '& img': {
    display: 'none',
  },
});
