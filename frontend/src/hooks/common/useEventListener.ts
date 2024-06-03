import { useEffect } from 'react';

export const useEventListener = <T extends keyof WindowEventMap>(
  eventName: T,
  handler: (event: WindowEventMap[T]) => void,
  options?: boolean | AddEventListenerOptions,
) => {
  useEffect(() => {
    window.addEventListener(eventName, handler);

    return () => window.removeEventListener(eventName, handler);
  }, [eventName, handler, options]);

  return;
};
