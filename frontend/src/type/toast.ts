export type ToastType = {
  id: number;
  variant: 'default' | 'success' | 'error';
  message: string;
  hasCloseBtn?: boolean;
  showDuration?: number;
};
