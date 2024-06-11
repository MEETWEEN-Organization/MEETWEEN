import { css } from '@emotion/react';

import { Theme } from '@/styles/theme/theme';

export const cardContainerStyle = (isSelected: boolean) =>
  css({
    display: 'flex',
    flexDirection: 'column',
    gap: Theme.spacing.spacing4,

    minWidth: '500px',

    padding: '16px 24px',

    border: 'none',
    borderRadius: Theme.borderRadius.medium,

    boxShadow: `inset 0px 0px 0px ${isSelected ? '1.5' : '1'}px ${isSelected ? Theme.color.blue400 : Theme.color.gray200}`,

    cursor: 'pointer',
  });

export const arrowStyle = css({
  display: 'flex',
  justifyContent: 'center',
  alignItems: 'center',

  width: '24px',
  height: '24px',

  paddingBottom: '2px',

  borderRadius: '50%',

  backgroundColor: Theme.color.gray400,
});

export const durationAverageTextStyle = css({
  display: 'flex',
  alignItems: 'center',
  gap: '6px',

  minWidth: '50%',

  '& > span': {
    color: Theme.color.blue500,

    fontSize: '20px',
    fontWeight: 600,
  },
  '& > p': {
    fontWeight: 500,
  },
});
