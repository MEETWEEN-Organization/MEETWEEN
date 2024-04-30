import { css } from '@emotion/react';

import { Theme } from '@styles/theme/theme';

import { SelectProps } from './Select';

/* 최상위 컨테이너 스타일 */
export const wrapperStyle = css({
  display: 'flex',
  flexDirection: 'column',
  gap: Theme.spacing.spacing3,
});

/* select 랩퍼 div 스타일 */
export const selectWrapperStyle = (
  variant: Required<SelectProps>['variant'],
  isError: Required<SelectProps['isError']>,
) =>
  css({
    display: 'flex',
    alignItems: 'center',

    position: 'relative',

    backgroundColor: isError
      ? Theme.color.red100
      : variant === 'default'
        ? Theme.color.gray100
        : 'transparent',
    paddingRight: Theme.spacing.spacing3,

    border: `1px solid ${isError ? Theme.color.red200 : variant === 'default' ? 'transparent' : Theme.color.gray200}`,
    borderRadius: Theme.borderRadius.small,
  });

/* size별 스타일 */
export const sizeStyle = {
  large: css({
    padding: '14px 16px',

    fontSize: Theme.text.large.fontSize,
    lineHeight: Theme.text.large.lineHeight,
  }),

  medium: css({
    padding: '12px 16px',

    fontSize: Theme.text.medium.fontSize,
    lineHeight: Theme.text.medium.lineHeight,
  }),

  small: css({
    padding: '8px 12px',

    fontSize: Theme.text.small.fontSize,
    lineHeight: Theme.text.small.lineHeight,
  }),
};

/* select 스타일 */
export const selectStyle = (
  variant: Required<SelectProps>['variant'],
  isError: Required<SelectProps['isError']>,
) =>
  css({
    WebkitAppearance: 'none',
    appearance: 'none',
    MozAppearance: 'none',
    /*화살표 배경 넣기*/

    width: '100%',
    backgroundColor: isError
      ? Theme.color.red100
      : variant === 'default'
        ? Theme.color.gray100
        : 'transparent',
    border: 'none',
    outline: 0,
  });
