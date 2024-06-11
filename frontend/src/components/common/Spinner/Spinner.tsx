import { spinnerStyle } from '@/components/common/Spinner/Spinner.style';

export interface SpinnerProps {
  size?: number;
  border?: number;
  variant?: 'linear' | 'bezier';
}

const Spinner = ({ size = 40, border = 3, variant = 'linear' }: SpinnerProps) => {
  return <div css={spinnerStyle({ size, border, variant })} />;
};

export default Spinner;
