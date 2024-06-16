import { useCallback, useRef } from 'react';

export const useDebounce = () => {
  const ref = useRef<NodeJS.Timeout>();

  const debounce = useCallback(
    <T extends (...args: any[]) => void>(callback: T, delay: number = 500) => {
      clearTimeout(ref.current);

      ref.current = setTimeout(() => {
        callback();
      }, delay);
    },
    [],
  );

  return { debounce };
};
