import type { Meta, StoryObj } from '@storybook/react';

import { useState } from 'react';

import Select from '@/components/common/Select/Select';
import { selectWrapperStyle, sizeStyle } from '@/components/common/Select/Select.style';

import { useOverlay } from '@/hooks/common';

import DownArrow from '@/assets/svg/select-arrow.svg?react';

const meta = {
  title: 'Common/Select',
  component: Select,
  parameters: {
    layout: 'centered',
  },
  tags: ['autodocs'],
  argTypes: {
    size: {
      control: { type: 'radio' },
      options: ['large', 'medium', 'small'],
    },
    children: {
      description: 'Select에서 선택할 요소',
    },
  },
  args: {
    size: 'medium',
    label: '색상 선택',
    options: ['전체 보기', '나만 보기', '공유하기'],
    trigger: <></>,
  },
} satisfies Meta<typeof Select>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Playground: Story = {
  render: (args) => {
    const [value, setValue] = useState('');
    const { isOpen, toggle, close } = useOverlay();
    const handleSelect = (option: string) => {
      setValue(option);
    };
    const handleKeyDown = (e: React.KeyboardEvent) => {
      const { key } = e;
      if (key === 'Enter') {
        toggle();
      }
    };

    return (
      <>
        <Select
          {...args}
          isOpen={isOpen}
          onClose={close}
          onSelect={handleSelect}
          size="small"
          trigger={
            <div
              role="button"
              aria-label="리스트 박스 토글 버튼"
              tabIndex={0}
              onKeyDown={handleKeyDown}
              onClick={toggle}
              css={[selectWrapperStyle]}
            >
              <p css={sizeStyle('160px', 'small')}>{value || '선택해주세요'}</p>
              <DownArrow width={28} height={28} />
            </div>
          }
        />
      </>
    );
  },
};
