import { ComponentPropsWithRef, ForwardedRef, forwardRef } from 'react';

import { progressBarStyle } from '@/components/common/ProgressBar/ProgressBar.style';

interface ProgressBarProps extends ComponentPropsWithRef<'progress'> {
  degree: number;
  maxLength?: number;
}

const ProgressBar = (
  { degree, maxLength = 10 }: ProgressBarProps,
  ref: ForwardedRef<HTMLProgressElement>,
) => {
  return <progress ref={ref} css={progressBarStyle} max={maxLength} value={degree}></progress>;
};

export default forwardRef(ProgressBar);
