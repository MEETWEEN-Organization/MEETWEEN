import { css } from '@emotion/react';

import { Theme } from '@/styles/theme/theme';

export const inputWrapperStyle = css({
  display: 'flex',
  alignItems: 'center',
  justifyContent: 'space-between',

  padding: '12px 32px',

  border: `1px solid ${Theme.color.gray200}`,

  borderRadius: Theme.borderRadius.medium,

  cursor: 'pointer',

  '& > :not(:first-child)': {
    transform: 'translateX(10px)',
  },
});

export const currentColorDisplayStyle = (color: string) =>
  css({
    width: '24px',
    height: '24px',

    borderRadius: '50%',

    backgroundColor: color,
  });

export const buttonStyle = css({
  display: 'flex',
  alignItems: 'center',

  border: 'none',
  backgroundColor: 'transparent',

  cursor: 'pointer',
});
