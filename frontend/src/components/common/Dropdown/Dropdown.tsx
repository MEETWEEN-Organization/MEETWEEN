import DropdownItem from '@/components/common/Dropdown/DropdownItem';
import DropdownList from '@/components/common/Dropdown/DropdownList';
import { DropdownTrigger } from '@/components/common/Dropdown/DropdownTrigger';
import { useOutsideClick } from '@/hooks/common';

import { ComponentPropsWithoutRef } from 'react';

import Label from '@components/common/Label/Label';

import { wrapperStyle } from './Dropdown.style';

interface ColorPickerDropdownProps extends ComponentPropsWithoutRef<'div'> {
  label?: string;
  onClose?: () => void;
}

const Dropdown = ({ onClose = () => {}, label, children }: ColorPickerDropdownProps) => {
  const ref = useOutsideClick(onClose);

  return (
    <div ref={ref} css={wrapperStyle}>
      <Label id={label}>{label}</Label>
      {children}
    </div>
  );
};

const CompositionDropdown = Object.assign(Dropdown, {
  Trigger: DropdownTrigger,
  List: DropdownList,
  Item: DropdownItem,
});

export default CompositionDropdown;
