import { css } from '@emotion/react';

import { Theme } from '@/styles/theme/theme';

export const containerStyle = css({
  display: 'flex',
  flexDirection: 'column',
  justifyContent: 'space-between',
  alignItems: 'center',
  gap: Theme.spacing.spacing2,

  width: '100%',
  height: '380px',

  padding: '16px',

  borderRadius: Theme.borderRadius.medium,

  boxShadow: Theme.boxShadow.shadow4,

  '& > button': {
    marginTop: Theme.spacing.spacing2,
    width: '100%',
  },
});

export const titleWrapperStyle = css({
  display: 'flex',
  flexDirection: 'column',
  alignItems: 'center',
  gap: Theme.spacing.spacing2,

  '& > p': {
    width: '60%',
    color: Theme.color.gray400,
  },
});
