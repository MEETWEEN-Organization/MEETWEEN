import { ComponentPropsWithoutRef, useCallback, useEffect } from 'react';
import { createPortal } from 'react-dom';

import { backgroundStyle, closeBtnStyle, dialogStyle } from '@/components/common/Modal/Modal.style';

import CloseBtn from '@/assets/svg/close.svg?react';

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

/* eslint-disable jsx-a11y/no-static-element-interactions */

/* eslint-disable jsx-a11y/click-events-have-key-events */
const Modal = ({
  isOpen,
  close,
  isClosableByBackdrop = false,
  hasCloseBtn,
  children,
  ...props
}: ModalProps) => {
  const parentElement = document.body;

  const handleKeyDown = useCallback(
    (e: KeyboardEvent) => {
      const { key } = e;

      if (key === 'Escape') {
        close();
      }
    },
    [close],
  );

  useEffect(() => {
    if (isOpen) {
      document.body.style.overflowY = 'hidden';
      window.addEventListener('keypress', handleKeyDown);
    }

    return () => {
      document.body.style.overflowY = 'auto';
      window.removeEventListener('keypress', handleKeyDown);
    };
  }, [isOpen, handleKeyDown]);

  return (
    isOpen &&
    createPortal(
      <>
        <div onClick={isClosableByBackdrop ? close : () => {}} css={backgroundStyle} />
        <dialog aria-modal={isOpen} css={dialogStyle} {...props}>
          {hasCloseBtn && (
            <button aria-label="모달 닫기 버튼" css={closeBtnStyle} onClick={close}>
              <CloseBtn strokeWidth={2} />
            </button>
          )}
          {children}
        </dialog>
      </>,
      parentElement,
    )
  );
};

export default Modal;
