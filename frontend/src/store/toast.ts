import { atom } from 'recoil';

import { ToastType } from '@/type/toast';

export const toastListState = atom<ToastType[]>({
  key: 'toastListState',
  default: [],
});
