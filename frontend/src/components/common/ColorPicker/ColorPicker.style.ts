import { css } from '@emotion/react';

import { Theme } from '@/styles/theme/theme';

export const inputWrapperStyle = css({
  display: 'flex',
  alignItems: 'center',

  border: `1px solid ${Theme.color.gray200}`,

  padding: '12px 16px',
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
