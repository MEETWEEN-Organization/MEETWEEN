import { css } from '@emotion/react';

import React, { ComponentPropsWithoutRef } from 'react';

import {
  buttonStyle,
  currentColorDisplayStyle,
  inputWrapperStyle,
} from '@/components/common/ColorPicker/ColorPicker.style';
import Text from '@/components/common/Text/Text';

import DropdownArrow from '@/assets/svg/select-arrow.svg?react';

import { Theme } from '@/styles/theme/theme';

interface ColorSelectorProps extends ComponentPropsWithoutRef<'div'> {
  currentColor: string;
  onClick?: () => void;
}

const ColorSelector = ({ currentColor, onClick, ...props }: ColorSelectorProps) => {
  const handleKeyDown = (e: React.KeyboardEvent<HTMLDivElement>) => {
    const { key } = e;
    if (key === 'Enter') {
      onClick?.();
    }
  };

  return (
    <div
      role="button"
      aria-label="색상 선택 버튼"
      tabIndex={0}
      css={inputWrapperStyle}
      onKeyDown={handleKeyDown}
      {...props}
    >
      <Text
        size="medium"
        css={css`
          color: ${Theme.color.gray400};
          white-space: nowrap;
        `}
      >
        색상을 선택해주세요
      </Text>
      <button onClick={onClick} css={buttonStyle}>
        <div css={currentColorDisplayStyle(currentColor)} />
        <DropdownArrow width={20} height={20} />
      </button>
    </div>
  );
};

export default ColorSelector;
