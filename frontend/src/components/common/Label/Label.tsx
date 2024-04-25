import { ComponentPropsWithRef } from 'react';

import { textStyle } from './Label.style';

export interface ILabel extends ComponentPropsWithRef<'label'> {
  id?: string;
}

const Label = ({ id, ...props }: ILabel) => {
  return (
    <label css={textStyle} htmlFor={id} {...props}>
      {props.children}
    </label>
  );
};

export default Label;
