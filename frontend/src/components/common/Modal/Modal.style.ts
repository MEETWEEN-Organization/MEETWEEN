import { css } from '@emotion/react';

import { fadeIn } from '@styles/animation';
import { Theme } from '@styles/theme/theme';

export const backgroundStyle = css({
  position: 'fixed',
  top: 0,
  left: 0,

  zIndex: Theme.zIndex.overlayMiddle,

  width: '100%',
  height: '100%',

  backgroundColor: 'rgba(0, 0, 0, 0.15)',

  animation: `${fadeIn} .15s ease-in`,
});

export const dialogStyle = css({
  position: 'absolute',
  top: '50%',
  transform: 'translateY(-50%)',

  display: 'flex',
  flexDirection: 'column',
  justifyContent: 'center',
  alignItems: 'center',

  zIndex: Theme.zIndex.overlayTop,

  minWidth: '300px',
  maxWidth: '800px',

  minHeight: '120px',

  padding: Theme.spacing.spacing5,
  margin: 'auto auto',

  border: 'none',
  borderRadius: Theme.borderRadius.large,

  backgroundColor: Theme.color.white,
  boxShadow: Theme.boxShadow.blur300,

  animation: `${fadeIn} .15s ease-in`,
});

export const closeBtnStyle = css({
  position: 'absolute',
  top: '24px',
  right: '24px',

  width: '18px',
  height: '18px',

  border: 'none',
  backgroundColor: 'transparent',

  cursor: 'pointer',
});
