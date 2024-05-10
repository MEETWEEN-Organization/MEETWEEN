import { css } from '@emotion/react';

import { Theme } from '@/styles/theme/theme';

export const progressBarStyle = css({
  appearance: 'none',
  width: '100%',

  '::-webkit-progress-bar': {
    background: Theme.color.gray200,

    borderRadius: Theme.borderRadius.large,
    height: '10px',
    overflow: 'hidden',
  },

  '::-webkit-progress-value': {
    background: Theme.color.blue500,

    borderRadius: 0,

    transition: 'all .3s ease-in-out',
  },
});
