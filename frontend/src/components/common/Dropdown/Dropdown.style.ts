import { css } from '@emotion/react';

import { Theme } from '@styles/theme/theme';

export const wrapperStyle = css({
  position: 'relative',
});

export const listStyle = css({
  position: 'absolute',
  width: '100%',

  zIndex: Theme.zIndex.overlayMiddle,

  borderRadius: Theme.borderRadius.medium,

  backgroundColor: Theme.color.white,
  boxShadow: Theme.boxShadow.blur100,
});

export const itemStyle = css({
  cursor: 'pointer',

  textAlign: 'center',
  padding: '16px',

  border: `1px solid ${Theme.color.gray200}`,
});
