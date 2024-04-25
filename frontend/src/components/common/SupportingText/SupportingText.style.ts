import { css } from '@emotion/react';

import { Theme } from '@styles/theme/theme';

import { IText } from './SupportingText';

export const getTextStyle = (isError: Required<IText>['isError']) =>
  css({
    fontSize: Theme.text.medium.fontSize,
    lineHeight: Theme.text.medium.lineHeight,

    color: isError ? Theme.color.red300 : Theme.color.gray600,
    fontWeight: 600,
  });
