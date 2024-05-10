import { css } from '@emotion/react';

import { Theme } from '@/styles/theme/theme';

export const toggleStyle = css({
  appearance: 'none',

  position: 'relative',
  width: '60px',
  height: '32px',

  //   border: `1px solid ${Theme.color.gray200}`,
  borderRadius: Theme.borderRadius.large,
  backgroundColor: Theme.color.gray300,
  boxSizing: 'content-box',

  cursor: 'pointer',

  '&:checked': {
    backgroundColor: Theme.color.blue700,
  },

  '&::before': {
    position: 'absolute',
    left: '6px',
    top: 0,
    bottom: 0,

    width: '20px',
    height: '20px',

    margin: 'auto 0',

    borderRadius: '50%',
    backgroundColor: Theme.color.white,

    content: '""',

    transition: 'all 0.1s linear',
  },

  '&:checked::before': {
    left: '34px',
  },
});
