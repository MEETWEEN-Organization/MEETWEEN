import { ComponentPropsWithRef } from 'react';

import { toggleStyle } from '@/components/common/Toggle/Toggle.style';

interface ToggleProps extends ComponentPropsWithRef<'input'> {
  onToggle: (e: React.ChangeEvent<HTMLInputElement>) => void;
  isChecked: boolean;
}

const Toggle = ({ onToggle, isChecked }: ToggleProps) => {
  return (
    <input
      role="switch"
      aria-checked={isChecked}
      css={toggleStyle}
      onChange={onToggle}
      type="checkbox"
    />
  );
};

export default Toggle;
