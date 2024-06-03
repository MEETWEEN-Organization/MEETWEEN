import { useCallback } from 'react';

import { useSetRecoilState } from 'recoil';

import { ToastProps } from '@/components/common/Toast/Toast';

import { toastListState } from '@/store/toast';

export const useToast = () => {
  const setToastList = useSetRecoilState(toastListState);

  const createToast = useCallback(
    (message: string, variant: ToastProps['variant'] = 'default') => {
      const newToast = { id: Number(Date.now()), message, variant };

      setToastList((arr) => [...arr, newToast]);
    },
    [setToastList],
  );

  return { createToast };
};
