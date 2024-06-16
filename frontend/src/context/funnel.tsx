import React, { ReactNode, createContext, useCallback, useContext, useMemo, useState } from 'react';

import { InfoType, MeetFunnelContextType } from '@/type/funnel';
import { AddressType } from '@/type/place';

import { COLOR_PALLETE } from '@/constants/common';

const MeetFunnelContext = createContext<MeetFunnelContextType | undefined>(undefined);

const MeetFunnelInfoProvider = ({ children }: { children: ReactNode }) => {
  const [info, setInfo] = useState<InfoType>({
    title: '',
    category: '',
    categoryColor: COLOR_PALLETE[0],
  });
  const [guestCount, setGuestCount] = useState(1);
  const [date, setDate] = useState('');
  const [time, setTime] = useState('');
  const [origins, setOrigins] = useState<AddressType[]>([]);

  const handleTitleChange = useCallback((e: React.ChangeEvent<HTMLInputElement>) => {
    setInfo((prev) => ({
      ...prev,
      title: e.target.value,
    }));
  }, []);

  const handleCategoryChange = useCallback((e: React.ChangeEvent<HTMLInputElement>) => {
    setInfo((prev) => ({
      ...prev,
      category: e.target.value,
    }));
  }, []);

  const handleSelectColor = useCallback((color: string) => {
    setInfo((prev) => ({
      ...prev,
      categoryColor: color,
    }));
  }, []);

  const handleChangeCount = useCallback((change: number) => {
    setGuestCount((prev) => prev + change);
  }, []);

  const handleChangeDate = useCallback((date: string) => {
    setDate(date);
  }, []);

  const handleChangeTime = useCallback((time: string) => {
    setTime(time);
  }, []);

  const handleAddOrigins = useCallback((origins: AddressType[]) => {
    setOrigins(origins);
  }, []);

  const context = useMemo(
    () => ({
      info,
      handleTitleChange,
      handleCategoryChange,
      handleSelectColor,
      guestCount,
      handleChangeCount,
      date,
      handleChangeDate,
      time,
      handleChangeTime,
      origins,
      handleAddOrigins,
    }),
    [
      info,
      guestCount,
      date,
      time,
      origins,
      handleTitleChange,
      handleCategoryChange,
      handleSelectColor,
      handleChangeCount,
      handleChangeDate,
      handleChangeTime,
      handleAddOrigins,
    ],
  );

  return <MeetFunnelContext.Provider value={context}>{children}</MeetFunnelContext.Provider>;
};

const useFunnelInfo = () => {
  const context = useContext(MeetFunnelContext);

  if (context === undefined) throw new Error('funnel context is undefined');

  return context;
};

export { MeetFunnelInfoProvider, useFunnelInfo };
