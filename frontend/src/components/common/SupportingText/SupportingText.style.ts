import { css } from '@emotion/react';
import { Theme } from '@styles/theme/theme';

import { TextProps } from '@/components/common/SupportingText/SupportingText';

export const getTextStyle = (isError: Required<TextProps>['isError']) =>
  css({
    fontSize: Theme.text.small.fontSize,
    lineHeight: Theme.text.small.lineHeight,

    color: isError ? Theme.color.red300 : Theme.color.gray600,
    fontWeight: 600,
  });
