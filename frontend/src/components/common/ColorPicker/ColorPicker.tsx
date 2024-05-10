import { css } from '@emotion/react';

import Box from '@/components/common/Box/Box';
import ColorSelector from '@/components/common/ColorPicker/ColorInput';
import Dropdown from '@/components/common/Dropdown/Dropdown';

import { useOverlay } from '@/hooks/common';

import { COLOR_PALLETE } from '@/constants/common';

import { Theme } from '@/styles/theme/theme';

interface ColorPickerProps {
  color: string;
  onSelectColor: (color: string) => void;
  label?: string;
}

const ColorPicker = ({ color, onSelectColor, label }: ColorPickerProps) => {
  const { isOpen, close, toggle } = useOverlay();

  const handleKeyDown = (e: React.KeyboardEvent, color: string) => {
    const { key } = e;
    if (key === 'Enter') {
      onSelectColor(color);
    }
  };

  return (
    <Dropdown onClose={close} label={label}>
      <Dropdown.Trigger as={<ColorSelector onClick={toggle} currentColor={color} />} />
      <Dropdown.List css={palleteListStyle} isOpen={isOpen}>
        {COLOR_PALLETE.map((color) => (
          <Box
            role="button"
            tabIndex={0}
            onKeyDown={(e) => handleKeyDown(e, color)}
            onClick={() => onSelectColor(color)}
            styles={{
              backgroundColor: color,
            }}
          />
        ))}
      </Dropdown.List>
    </Dropdown>
  );
};

const palleteListStyle = css({
  bottom: 0,
  display: 'grid',
  gridTemplateColumns: 'repeat(4, 1fr)',
  gap: Theme.spacing.spacing3,

  padding: Theme.spacing.spacing4,

  transform: 'translateX(calc(100% + 0.5rem))',

  '& > div': {
    placeSelf: 'center',

    width: '28px',
    height: '28px',

    borderRadius: '50%',

    cursor: 'pointer',
  },
});

export default ColorPicker;
