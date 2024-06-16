import { css } from '@emotion/react';

import { Theme } from '@/styles/theme/theme';

export const wrapperStyle = css({
  display: 'flex',
  flexDirection: 'column',
  alignItems: 'center',
  gap: Theme.spacing.spacing7,

  paddingTop: Theme.spacing.spacing8,

  '& > button': {
    placeSelf: 'revert',
    width: '180px',
  },
});
