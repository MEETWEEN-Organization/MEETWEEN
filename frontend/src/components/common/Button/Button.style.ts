import { css } from '@emotion/react';

import { Theme } from '@/styles/theme/theme';

export const variantStyle = {
  primary: css({
    backgroundColor: Theme.color.blue800,
    color: Theme.color.white,

    '&:hover:enabled': {
      backgroundColor: Theme.color.blue900,
    },
  }),

  secondary: css({
    backgroundColor: Theme.color.blue600,
    color: Theme.color.white,

    '&:hover:enabled': {
      backgroundColor: Theme.color.blue700,
    },
  }),

  outline: css({
    backgroundColor: Theme.color.white,
    color: Theme.color.gray800,

    boxShadow: `inset 0 0 0 1.5px ${Theme.color.gray200}`,

    '&:hover:enabled': {
      backgroundColor: Theme.color.gray100,
    },
  }),

  default: css({
    backgroundColor: Theme.color.black,
    color: Theme.color.white,

    '&:hover:enabled': {
      backgroundColor: Theme.color.blue900,
    },
  }),

  text: css({
    backgroundColor: 'transparent',
  }),
};

export const sizeStyle = {
  xLarge: css({
    padding: '16px 64px',
    fontSize: Theme.text.large.fontSize,
    lineHeight: Theme.text.large.lineHeight,
  }),

  large: css({
    padding: '14px 32px',
    fontSize: Theme.text.medium.fontSize,
    lineHeight: Theme.text.medium.lineHeight,
  }),

  medium: css({
    padding: '12px 18px',
    fontSize: Theme.text.medium.fontSize,
    lineHeight: Theme.text.medium.lineHeight,
  }),

  small: css({
    padding: '8px 12px',
    fontSize: Theme.text.small.fontSize,
    lineHeight: Theme.text.small.lineHeight,
  }),
};

export const buttonStyle = css({
  display: 'flex',
  justifyContent: 'center',
  alignItems: 'center',
  gap: '6px',

  border: 'none',
  borderRadius: Theme.borderRadius.medium,

  backgroundColor: 'white',

  cursor: 'pointer',

  fontWeight: 600,

  transition: 'all .2s ease-in-out',

  '& svg': {
    width: '18px',
    height: '18px',
  },
});
