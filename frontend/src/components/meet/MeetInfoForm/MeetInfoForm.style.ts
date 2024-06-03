import { css } from '@emotion/react';

import { Theme } from '@/styles/theme/theme';

export const formStyle = css({
  display: 'inline-flex',
  flexDirection: 'column',
  gap: Theme.spacing.spacing4,

  maxWidth: '500px',
});
