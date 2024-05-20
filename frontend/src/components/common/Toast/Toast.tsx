import { ComponentPropsWithoutRef, useCallback, useEffect, useRef, useState } from 'react';
import { createPortal } from 'react-dom';

import { textStyle, toastStyle, variantStyle } from '@/components/common/Toast/Toast.style';

import CloseBtn from '@/assets/svg/close.svg?react';

export interface ToastProps extends ComponentPropsWithoutRef<'div'> {
  /* Toast 종류 */
  variant?: 'default' | 'error' | 'success';

  /* Toast 유지 시간 */
  showDuration?: number;

  /* 닫힘 버튼 유무 */
  hasCloseBtn?: boolean;

  /* 닫는 함수 */
  onClose: () => void;
}

const Toast = ({
  variant = 'default',
  showDuration = 3000,
  hasCloseBtn = false,
  onClose,
  children,
}: ToastProps) => {
  const [isRendered, setIsRendered] = useState(true);
  const [isShown, setIsShown] = useState(true);

  const hideRef = useRef<NodeJS.Timeout>();
  const showRef = useRef<NodeJS.Timeout>();

  // let hideRef: NodeJS.Timeout;
  // let showRef: NodeJS.Timeout;

  const handleClose = useCallback(() => {
    hideRef.current = setTimeout(() => {
      // useEffect의 setTimeOut Clear 해야함
      setIsRendered(false);
      onClose();
      clearTimeout(showRef.current);
    }, 600);
  }, [onClose, setIsRendered]);

  useEffect(() => {
    showRef.current = setTimeout(() => {
      // isShown은 true 상태이므로 마운트 시, show 애니메이션 시작
      // 컴포넌트 마운트 시 2초 후에 hide 애니메이션을 시작하고 handleClose 호출
      setIsShown(false);
      handleClose();
    }, showDuration);

    return () => clearTimeout(hideRef.current);
  }, [showDuration, handleClose]);

  return isRendered
    ? createPortal(
        <div css={[variantStyle(variant), toastStyle(isShown)]}>
          {hasCloseBtn && <CloseBtn onClick={onClose} />}
          <span css={textStyle}>{children}</span>
        </div>,
        document.getElementById('toast-container') as Element,
      )
    : createPortal(<div />, document.getElementById('toast-container') as Element);
};

export default Toast;
