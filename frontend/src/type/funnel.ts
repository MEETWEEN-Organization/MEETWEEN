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
