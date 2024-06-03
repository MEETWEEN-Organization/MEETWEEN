import { css } from '@emotion/react';

import { Theme } from '@/styles/theme/theme';

export const wrapperStyle = css({
  display: 'flex',
  alignItems: 'center',
  gap: Theme.spacing.spacing3,

  '& > p': {
    color: Theme.color.gray600,
  },
});
