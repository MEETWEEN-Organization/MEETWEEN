import { css } from '@emotion/react';

import { Theme } from '@/styles/theme/theme';

export const getTabStyle = (selected: boolean) =>
  css({
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',

    padding: '10px 16px',

    color: selected ? Theme.color.black : Theme.color.gray400,
    fontWeight: 600,
    fontSize: Theme.text.medium.fontSize,
    lineHeight: Theme.text.medium.lineHeight,

    cursor: 'pointer',

    transition: 'all .2s ease-in',
  });

export const getPanelStyle = (selected: boolean) =>
  css({
    display: selected ? 'block' : 'none',

    width: '100%',
    height: '4px',

    backgroundColor: 'black',
    borderRadius: Theme.borderRadius.small,
  });
