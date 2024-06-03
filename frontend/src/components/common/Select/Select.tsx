import { ComponentPropsWithoutRef } from 'react';

import Dropdown from '@/components/common/Dropdown/Dropdown';
import DropdownItem from '@/components/common/Dropdown/DropdownItem';
import DropdownList from '@/components/common/Dropdown/DropdownList';
import { DropdownTrigger } from '@/components/common/Dropdown/DropdownTrigger';

import { SizeType } from '@/type/size';

import * as Styled from './Select.style';

export interface SelectProps
  extends Omit<ComponentPropsWithoutRef<'div'>, 'size' | 'onSelect' | 'trigger'> {
  label?: string;
  size?: Extract<SizeType, 'large' | 'medium' | 'small'>;
  options: string[];
  isOpen?: boolean;
  onClose?: () => void;
  onSelect?: (option: string) => void;
  trigger: JSX.Element;
}

const Select = ({
  label,
  size = 'medium',
  isOpen,
  onClose,
  onSelect,
  trigger,
  options,
  ...props
}: SelectProps) => {
  const handleSelect = (item: string) => {
    onSelect?.(item);
    onClose?.();
  };
  return (
    <Dropdown role="listbox" label={label} onClose={onClose} {...props}>
      <DropdownTrigger as={trigger} />
      <DropdownList isOpen={isOpen}>
        {options.map((item) => (
          <DropdownItem
            role="option"
            key={item}
            onSelect={() => handleSelect(item)}
            css={Styled.sizeStyle('', size)}
          >
            {item}
          </DropdownItem>
        ))}
      </DropdownList>
    </Dropdown>
  );
};

export default Select;
