import { css } from '@emotion/react';

import { Theme } from '@/styles/theme/theme';

export const cardStyle = css({
  display: 'flex',
  flexDirection: 'column',

  position: 'relative',

  width: '500px',

  padding: '16px 36px',

  border: `1px solid ${Theme.color.gray200}`,
  borderRadius: Theme.borderRadius.large,

  '& > ul:first-of-type': {
    borderBottom: `1px dashed ${Theme.color.gray200}`,
  },
  '&::before, &::after': {
    position: 'absolute',
    top: '45%',

    width: '48px',
    height: '48px',

    backgroundColor: Theme.color.white,

    content: '""',
  },
  '&::before': {
    borderRadius: '0 50% 50% 0',
    borderRight: `1px solid ${Theme.color.gray200}`,

    left: '-24px',
  },
  '&::after': {
    borderRadius: '50% 0 0 50%',
    borderLeft: `1px solid ${Theme.color.gray200}`,

    right: '-24px',
  },
});

export const listStyle = css({
  padding: '12px 0px',
});

export const itemWrapperStyle = css({
  display: 'flex',
  gap: Theme.spacing.spacing4,

  padding: '4px 0px',

  '& > p': {
    width: '60%',
  },
});

export const itemTitleWrapperStyle = css({
  display: 'flex',
  alignItems: 'center',
  gap: Theme.spacing.spacing4,

  width: '40%',

  '& > p': {
    color: Theme.color.gray500,
  },
});

export const inviteCodeStyle = css({
  display: 'flex',
  alignItems: 'center',
  gap: '12px',

  '& > svg': {
    cursor: 'pointer',
  },
});
