import { css } from '@emotion/react';

import { CounterProps } from '@/components/common/Counter/Counter';

import { Theme } from '@/styles/theme/theme';

export const containerStyle = css({
  display: 'flex',
  flexDirection: 'column',
  alignItems: 'center',
  gap: Theme.spacing.spacing4,
});

export const counterStyle = (isError: Required<CounterProps>['isError']) =>
  css({
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',

    width: '100px',
    height: '100px',

    borderRadius: Theme.borderRadius.medium,

    backgroundColor: Theme.color.gray200,

    boxShadow: isError
      ? `inset 0 0 0 2px ${Theme.color.red200}, ${Theme.boxShadow.shadow5}`
      : Theme.boxShadow.shadow5,
  });

export const countTextStyle = css({
  fontSize: Theme.heading.large.fontSize,
  lineHeight: Theme.heading.large.lineHeight,

  fontWeight: 600,
});
