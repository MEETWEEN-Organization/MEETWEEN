import { css } from '@emotion/react';

import { Theme } from '@/styles/theme/theme';

export const wrapperStyle = css({
  display: 'flex',
});

export const formStyle = css({
  display: 'flex',
  flexDirection: 'column',
  alignItems: 'center',
  gap: Theme.spacing.spacing5,

  padding: '12px 24px',

  maxHeight: '340px',

  overflowY: 'scroll',
});

export const textStyle = css({
  color: Theme.color.black,
  fontSize: Theme.heading.xSmall.fontSize,
  fontWeight: 600,

  '& > span:first-of-type': {
    color: Theme.color.blue500,
    marginRight: '0.5ch',
  },
  '& > span:nth-child(3)': {
    marginLeft: '0.5ch',
  },
});
