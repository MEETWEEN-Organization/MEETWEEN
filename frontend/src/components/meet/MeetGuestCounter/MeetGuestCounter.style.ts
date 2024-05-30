import { css } from '@emotion/react';

import { Theme } from '@/styles/theme/theme';

export const wrapperStyle = css({
  display: 'flex',
  alignItems: 'center',
  gap: Theme.spacing.spacing6,
});

export const buttonStyle = css({
  width: '52px',
  height: '60px',

  border: `1.5px solid ${Theme.color.gray200}`,
  borderRadius: Theme.borderRadius.small,

  backgroundColor: 'transparent',

  fontWeight: 600,
  fontSize: Theme.heading.medium.fontSize,
  lineHeight: Theme.heading.medium.lineHeight,

  transition: 'all .1s ease-in',

  '&:hover': {
    backgroundColor: Theme.color.black,
    color: Theme.color.white,
  },
});
