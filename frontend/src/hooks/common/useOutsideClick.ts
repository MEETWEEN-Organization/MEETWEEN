import { useCallback, useEffect, useRef } from 'react';

type PropsType = () => void;

export const useOutsideClick = <T extends HTMLElement = HTMLDivElement>(onClose: PropsType) => {
  const targetRef = useRef<T | null>(null);

  const handleClick = useCallback(
    (event: MouseEvent) => {
      if (!targetRef.current) return;

      if (!targetRef.current.contains(event.target as Node)) {
        onClose?.();
      }
    },
    [onClose],
  );

  const handleEscape = useCallback(
    (event: KeyboardEvent) => {
      const { key } = event;

      if (key === 'Escape') {
        onClose?.();
      }
    },
    [onClose],
  );

  useEffect(() => {
    window.addEventListener('click', handleClick);
    window.addEventListener('keypress', handleEscape);

    return () => {
      window.removeEventListener('click', handleClick);
      window.removeEventListener('keypress', handleEscape);
    };
  }, [onClose, targetRef]);

  return targetRef;
};
