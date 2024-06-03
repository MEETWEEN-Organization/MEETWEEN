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

  height: '400px',
  overflowY: 'scroll',
});

export const currentCompletedTextStyle = css({
  color: Theme.color.blue500,
  fontSize: Theme.heading.xSmall.fontSize,
});
