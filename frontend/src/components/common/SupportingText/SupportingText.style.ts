import { css } from '@emotion/react';

import { TextProps } from '@/components/common/SupportingText/SupportingText';

import { Theme } from '@/styles/theme/theme';

export const getTextStyle = (isError: Required<TextProps>['isError']) =>
  css({
    fontSize: Theme.text.small.fontSize,
    lineHeight: Theme.text.small.lineHeight,

    color: isError ? Theme.color.red300 : Theme.color.gray600,
    fontWeight: 600,
  });
