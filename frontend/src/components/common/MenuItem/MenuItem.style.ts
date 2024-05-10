import { css } from '@emotion/react';

import { Theme } from '@/styles/theme/theme';

export const itemStyle = css({
  padding: `${Theme.spacing.spacing3} 12px`,

  textAlign: 'center',

  fontSize: Theme.text.medium.fontSize,
  lineHeight: Theme.text.medium.lineHeight,

  cursor: 'pointer',

  transition: `all 0.2s ease-in`,

  '&:hover': {
    backgroundColor: Theme.color.gray200,
  },
});
