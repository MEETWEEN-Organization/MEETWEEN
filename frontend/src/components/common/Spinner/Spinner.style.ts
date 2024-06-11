import { css } from '@emotion/react';

import { SpinnerProps } from '@/components/common/Spinner/Spinner';

import { rotate } from '@/styles/animation';
import { Theme } from '@/styles/theme/theme';

export const spinnerStyle = ({ size, border, variant }: SpinnerProps) =>
  css({
    width: `${size}px`,
    height: `${size}px`,

    border: `${border}px solid ${Theme.color.blue500}`,
    borderRadius: '50%',

    borderBottom: `${border}px solid white`,

    animation: `${rotate} 1s ${variant === 'linear' ? 'linear' : 'cubic-bezier(0, 0.1, 0.6, 0.9)'} infinite`,
  });
