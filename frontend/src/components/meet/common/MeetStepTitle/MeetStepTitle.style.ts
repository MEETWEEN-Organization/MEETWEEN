import { css } from '@emotion/react';

import { MeetStepTitleProps } from '@/components/meet/common/MeetStepTitle/MeetStepTitle';

import { Theme } from '@/styles/theme/theme';

export const imgStyle = (size: Required<MeetStepTitleProps>['logoSize']) =>
  css({
    '& > source, & > img': {
      width: `${size}px`,
      height: `${size}px`,
    },
  });

export const titleWrapperStyle = css({
  display: 'flex',
  flexDirection: 'column',
  alignItems: 'center',
  gap: Theme.spacing.spacing3,

  '& > h2': {
    textAlign: 'center',
  },

  '& > p': {
    color: Theme.color.gray500,

    textAlign: 'center',
  },
});
