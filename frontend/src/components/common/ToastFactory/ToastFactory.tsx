import { useCallback } from 'react';

import { useRecoilState } from 'recoil';

import Toast from '@/components/common/Toast/Toast';

import { toastListState } from '@/store/toast';

/* eslint-disable react-hooks/exhaustive-deps */
const ToastFactory = () => {
  const [toastList, setToastList] = useRecoilState(toastListState);

  const removeToast = useCallback(
    (id: number) => {
      const removed = toastList.filter((toast) => toast.id !== id);
      setToastList(removed);
    },
    [setToastList, toastList],
  );

  return (
    <>
      {toastList.map((toast) => (
        <Toast
          key={toast.id}
          variant={toast.variant}
          showDuration={toast.showDuration}
          onClose={() => removeToast(toast.id)}
        >
          {toast.message}
        </Toast>
      ))}
    </>
  );
};

export default ToastFactory;
