import { css } from '@emotion/react';

import { HTMLAttributes } from 'react';

import { Theme } from '@/styles/theme/theme';

interface CutoutCircleProps extends Omit<HTMLAttributes<HTMLDivElement>, 'position'> {
  position: 'left' | 'right';
}

const CutoutCircle = ({ position }: CutoutCircleProps) => {
  return <div css={[circleStyle, positionStyle(position)]}></div>;
};

export default CutoutCircle;

const circleStyle = css({
  position: 'absolute',
  top: '24px',
  bottom: 0,

  width: '42px',
  height: '42px',
  zIndex: 1,

  margin: 'auto auto',

  border: `1px solid ${Theme.color.gray200}`,
  borderRadius: '50%',

  backgroundColor: Theme.color.white,
});

const positionStyle = (position: string) =>
  css({
    left: position === 'left' ? '-21px' : '',
    right: position === 'right' ? '-21px' : '',
  });
