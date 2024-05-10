import { ComponentPropsWithRef } from 'react';

import { textStyle } from '@/components/common/Label/Label.style';

export interface LabelProps extends ComponentPropsWithRef<'label'> {
  id?: string;
}

const Label = ({ id, ...props }: LabelProps) => {
  return (
    <label css={textStyle} htmlFor={id} {...props}>
      {props.children}
    </label>
  );
};

export default Label;
