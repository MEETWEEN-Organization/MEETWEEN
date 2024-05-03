import { css } from '@emotion/react';

import { fadeIn, fadeOut, moveUp } from '@styles/animation';
import { Theme } from '@styles/theme/theme';

import { ToastProps } from './Toast';

export const variantStyle = (variant: Required<ToastProps>['variant']) => {
  const style = {
    default: css({
      backgroundColor: Theme.color.gray400,
    }),

    success: {
      backgroundColor: Theme.color.blue500,
    },

    error: {
      backgroundColor: Theme.color.red200,
    },
  };

  return style[variant];
};

export const toastStyle = (isShown: boolean) =>
  css({
    display: 'flex',
    justifyContent: 'space-between',
    alignItems: 'center',

    minWidth: '240px',

    padding: `14px ${Theme.spacing.spacing4}`,

    borderRadius: Theme.borderRadius.medium,

    boxShadow: Theme.boxShadow.blur200,

    color: Theme.color.white,

    animation: isShown
      ? `${fadeIn} 0.2s ease-in, ${moveUp} 0.2s ease-in`
      : `${fadeOut} 0.2s ease-in forwards`,
  });

export const textStyle = css({
  fontWeight: 600,

  fontSize: Theme.text.medium.fontSize,
  lineHeight: Theme.text.medium.lineHeight,
});
