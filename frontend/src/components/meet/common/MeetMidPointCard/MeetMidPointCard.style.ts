import { css } from '@emotion/react';

import { Theme } from '@/styles/theme/theme';

export const cardContainerStyle = css({
  display: 'flex',
  flexDirection: 'column',
  gap: Theme.spacing.spacing4,

  width: '100%',

  padding: '16px 24px',

  border: `1px solid ${Theme.color.gray200}`,
  borderRadius: Theme.borderRadius.medium,

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
