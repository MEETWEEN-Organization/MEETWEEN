import { css } from '@emotion/react';

import { Theme } from '@/styles/theme/theme';

import { SelectProps } from './Select';

/* select 랩퍼 div 스타일 */
export const selectWrapperStyle = css({
  display: 'flex',
  alignItems: 'center',

  position: 'relative',

  backgroundColor: 'transparent',
  paddingRight: Theme.spacing.spacing3,

  border: `1px solid ${Theme.color.gray200}`,
  borderRadius: Theme.borderRadius.medium,

  cursor: 'pointer',

  '& > button, & > input': {
    border: 'none',
    backgroundColor: 'transparent',
  },

  '& > p': {
    textAlign: 'center',
  },
});

/* size별 스타일 */
export const sizeStyle = (width: string = '', size: Required<SelectProps>['size']) => {
  const style = {
    large: {
      padding: '14px 16px',

      fontSize: Theme.text.large.fontSize,
      lineHeight: Theme.text.large.lineHeight,
    },

    medium: {
      padding: '12px 16px',

      fontSize: Theme.text.medium.fontSize,
      lineHeight: Theme.text.medium.lineHeight,
    },

    small: {
      padding: '8px 12px',

      fontSize: Theme.text.small.fontSize,
      lineHeight: Theme.text.small.lineHeight,
    },
  };

  return css({
    width,
    ...style[size],
  });
};
