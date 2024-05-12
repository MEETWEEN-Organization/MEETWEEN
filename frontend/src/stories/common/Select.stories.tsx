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
              role="listbox"
              tabIndex={0}
              aria-expanded={isOpen}
              onKeyDown={handleKeyDown}
              onClick={toggle}
              css={[selectWrapperStyle]}
            >
              <button css={sizeStyle('140px', 'small')}>{value || '선택해주세요'}</button>
              <DownArrow width={28} height={28} />
            </div>
          }
        />
      </>
    );
  },
};

// export const Variants: Story = {
//   render: ({ size, isError }) => (
//     <ul css={listStyle}>
//       <li>
//         <Select size={size} isError={isError} variant="default">
//           <option value="option">Option</option>
//         </Select>
//       </li>
//       <li>
//         <Select size={size} isError={isError} variant="outline">
//           <option value="option">Option</option>
//         </Select>
//       </li>
//     </ul>
//   ),

//   argTypes: {
//     variant: {
//       control: false,
//     },
//   },
// };

// export const Sizes: Story = {
//   render: ({ variant, isError }) => (
//     <ul css={listStyle}>
//       <li>
//         <Select variant={variant} isError={isError} size="large">
//           <option value="option">Option</option>
//         </Select>
//       </li>
//       <li>
//         <Select variant={variant} isError={isError} size="medium">
//           <option value="option">Option</option>
//         </Select>
//       </li>
//       <li>
//         <Select variant={variant} isError={isError} size="small">
//           <option value="option">Option</option>
//         </Select>
//       </li>
//     </ul>
//   ),
//   argTypes: {
//     size: {
//       control: false,
//     },
//   },
// };

// export const WithLabel: Story = {
//   args: {
//     label: 'Label',
//     isError: false,
//   },
// };

// export const WithSupportingText: Story = {
//   args: {
//     supportingText: 'SupportingText',
//   },
// };

// export const WithError: Story = {
//   args: {
//     isError: true,
//     supportingText: 'SupportingText',
//   },
// };
