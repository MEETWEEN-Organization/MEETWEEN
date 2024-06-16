import { useCallback, useState } from 'react';

type ItemType<T> = {
  [K in keyof T]: T[K];
};

export const useSelect = <T>(defaultValue: T) => {
  const [selectedItem, setSelectedItem] = useState<ItemType<T>>(defaultValue);

  const handleSelect = useCallback((item: T) => {
    setSelectedItem(item);
  }, []);

  return { selectedItem, handleSelect };
};
