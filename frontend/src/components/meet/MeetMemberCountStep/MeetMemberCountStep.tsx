import Button from '@/components/common/Button/Button';
import { wrapperStyle } from '@/components/meet/MeetStep.style';
import MeetGuestCounter from '@/components/meet/common/MeetGuestCounter/MeetGuestCounter';
import MeetStepTitle from '@/components/meet/common/MeetStepTitle/MeetStepTitle';

import { useFunnelInfo } from '@/context/funnel';

import { StepProps } from '@/type/funnel';

const MeetMemberCountStep = ({ onNextStep = () => {} }: StepProps) => {
  const { guestCount, handleChangeCount } = useFunnelInfo();

  return (
    <section css={wrapperStyle}>
      <MeetStepTitle
        mainDescription="몇 명이서 모일 예정인가요"
        subDescription="약속에 참여할 인원 수를 입력해요"
      />

      <MeetGuestCounter guestCount={guestCount} onCountChange={handleChangeCount} />
      <Button onClick={onNextStep} variant="default" size="large">
        다음으로
      </Button>
    </section>
  );
};

export default MeetMemberCountStep;
