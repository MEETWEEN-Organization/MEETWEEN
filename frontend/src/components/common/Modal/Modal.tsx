import { ComponentPropsWithoutRef } from 'react';
import { createPortal } from 'react-dom';

import CloseBtn from '@assets/svg/close.svg?react';

import { backgroundStyle, closeBtnStyle, dialogStyle } from './Modal.style';

export interface ModalProps extends ComponentPropsWithoutRef<'dialog'> {
  /* 모달이 열려있는지에 대한 여부 */
  isOpen: boolean;

  /* Backdrop 클릭으로 모달 닫을 수 있는지에 대한 여부 */
  isClosableByBackdrop?: boolean;

  /* 닫는 버튼 유무 */
  hasCloseBtn?: boolean;

  /* 모달 닫는 함수 */
  close: () => void;
}

const Modal = ({
  isOpen,
  close,
  isClosableByBackdrop = false,
  hasCloseBtn,
  children,
  ...props
}: ModalProps) => {
  const parentElement = document.body;
  const modal = createPortal(
    <dialog css={dialogStyle} {...props}>
      {hasCloseBtn && <CloseBtn onClick={close} css={closeBtnStyle} strokeWidth={2} />}
      {children}
    </dialog>,
    parentElement,
  );

  return (
    isOpen && (
      <>
        <div onClick={isClosableByBackdrop ? close : () => {}} css={backgroundStyle} />
        {modal}
      </>
    )
  );
};

export default Modal;
