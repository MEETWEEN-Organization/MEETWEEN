import { css } from '@emotion/react';

import { Theme } from '@/styles/theme/theme';

export const headerStyle = css({
  position: 'sticky',
  top: 0,

  height: '66px',

  zIndex: Theme.zIndex.overlayMiddle,

  padding: `${Theme.spacing.spacing3} ${Theme.spacing.spacing5}`,

  borderBottom: `1px solid ${Theme.color.gray200}`,
});

export const appNameStyle = css({
  letterSpacing: '-.05rem',
  fontWeight: 700,

  cursor: 'pointer',
});

export const logoStyle = css({
  width: '36px',
  height: '36px',
});
