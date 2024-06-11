import { useFunnelInfo } from '@/context/funnel';

import Button from '@/components/common/Button/Button';
import { wrapperStyle } from '@/components/meet/MeetMemberCountStep/MeetMemberCountStep.style';
import MeetAddressForm from '@/components/meet/common/MeetAddressForm/MeetAddressForm';
import MeetStepTitle from '@/components/meet/common/MeetStepTitle/MeetStepTitle';

import { StepProps } from '@/type/funnel';

const MeetAddressStep = ({ onNextStep }: StepProps) => {
  const { guestCount } = useFunnelInfo();

  return (
    <section css={wrapperStyle}>
      <MeetStepTitle
        mainDescription="출발지 입력하기"
        subDescription="약속에 참여할 친구들의 출발지를 입력해주세요."
      />
      <MeetAddressForm addressCount={guestCount} />
      <Button onClick={onNextStep} variant="default" size="large">
        다음으로
      </Button>
    </section>
  );
};

export default MeetAddressStep;
