import { css } from '@emotion/react';

import { Theme } from '@/styles/theme/theme';

export const dayContainerStyle = css({
  display: 'grid',
  gridTemplateColumns: 'repeat(7, 1fr)',
});

export const calendarContainerStyle = css({
  display: 'inline-block',

  padding: Theme.spacing.spacing4,

  borderRadius: Theme.borderRadius.medium,
  boxShadow: Theme.boxShadow.blur200,

  '&  h6': {
    textAlign: 'center',
    margin: '12px',
  },
});

export const headerStyle = css({
  display: 'flex',
  alignItems: 'center',
  justifyContent: 'space-around',

  '& > svg': {
    width: 24,
    height: 24,

    stroke: Theme.color.gray600,
    strokeWidth: 2.5,

    cursor: 'pointer',
  },
});
