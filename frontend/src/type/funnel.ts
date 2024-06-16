import { AddressType } from '@/type/place';

export type StepType =
  | '약속 인원'
  | '약속 이름&카테고리'
  | '약속 날짜'
  | '약속 시간'
  | '출발지 입력'
  | '중간 장소 결과'
  | '약속 생성 완료';

export type StepProps = {
  onNextStep?: () => void;
};

export type InfoType = {
  title: string;
  category: string;
  categoryColor: string;
};

export type MeetFunnelContextType = {
  info: InfoType;
  handleTitleChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
  handleCategoryChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
  handleSelectColor: (color: string) => void;

  guestCount: number;
  handleChangeCount: (change: number) => void;
  date: string;
  handleChangeDate: (date: string) => void;
  time: string;
  handleChangeTime: (time: string) => void;
  origins: AddressType[];
  handleAddOrigins: (origins: AddressType[]) => void;
};
