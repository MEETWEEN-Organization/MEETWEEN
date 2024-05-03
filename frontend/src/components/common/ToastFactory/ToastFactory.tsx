import { useCallback } from 'react';

import { useRecoilState } from 'recoil';

import Toast from '@components/common/Toast/Toast';

import { toastListState } from '@store/toast';

const ToastFactory = () => {
  const [toastList, setToastList] = useRecoilState(toastListState);

  const removeToast = useCallback(
    (id: number) => {
      const removed = toastList.filter((toast) => toast.id !== id);
      setToastList(removed);
    },
    [setToastList],
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
