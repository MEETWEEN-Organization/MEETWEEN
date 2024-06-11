import { css } from '@emotion/react';

import { Theme } from '@/styles/theme/theme';

export const imgStyle = css({
  '& > source, & > img': {
    width: '160px',
    height: '160px',
  },
});

export const titleWrapperStyle = css({
  display: 'flex',
  flexDirection: 'column',
  alignItems: 'center',
  gap: Theme.spacing.spacing3,

  '& > h2': {
    textAlign: 'center',
  },

  '& > p': {
    color: Theme.color.gray500,

    textAlign: 'center',
  },
});
