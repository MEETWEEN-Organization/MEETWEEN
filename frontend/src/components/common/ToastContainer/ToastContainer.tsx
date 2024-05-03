import { ReactNode } from 'react';

import { containerStyle } from './ToastContainer.style';

const ToastContainer = ({ children }: { children: ReactNode }) => {
  return (
    <div css={containerStyle} id="toast-container">
      {children}
    </div>
  );
};

export default ToastContainer;
