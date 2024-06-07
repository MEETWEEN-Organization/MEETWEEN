import { css } from '@emotion/react';

import { Theme } from '@/styles/theme/theme';

export const wrapperStyle = css({
  display: 'flex',
  flexDirection: 'column',
  alignItems: 'center',
  gap: Theme.spacing.spacing7,

  padding: Theme.spacing.spacing6,

  '& > button': {
    placeSelf: 'revert',
    width: '180px',
  },
});
