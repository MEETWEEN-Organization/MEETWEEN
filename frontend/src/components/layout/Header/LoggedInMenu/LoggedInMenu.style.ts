import { css } from '@emotion/react';

export const imgStyle = css({
  width: '46px',
  height: '46px',
});

export const listStyle = css({
  transform: 'translateY(54px)',

  '& > li': {
    padding: '8px 16px',
    // padding: `${Theme.spacing.spacing2} ${Theme.spacing.spacing4}`,
  },
});
