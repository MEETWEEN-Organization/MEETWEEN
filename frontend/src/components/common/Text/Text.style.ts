import { css } from '@emotion/react';

import { Theme } from '@/styles/theme/theme';

export const textStyle = {
  large: css({
    fontSize: Theme.text.large.fontSize,
    lineHeight: Theme.text.large.lineHeight,
  }),

  medium: css({
    fontSize: Theme.text.medium.fontSize,
    lineHeight: Theme.text.medium.lineHeight,
  }),

  small: css({
    fontSize: Theme.text.small.fontSize,
    lineHeight: Theme.text.small.lineHeight,
  }),

  xSmall: css({
    fontSize: Theme.text.xSmall.fontSize,
    lineHeight: Theme.text.xSmall.lineHeight,
  }),
};
