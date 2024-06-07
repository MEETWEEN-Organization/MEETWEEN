import Button from '@/components/common/Button/Button';
import { wrapperStyle } from '@/components/meet/MeetMemberCountStep/MeetMemberCountStep.style';
import MeetStepTitle from '@/components/meet/common/MeetStepTitle/MeetStepTitle';
import MeetTimeForm from '@/components/meet/common/MeetTimeForm/MeetTimeForm';

import { StepProps } from '@/type/funnel';

const MeetTimeStep = ({ onNextStep }: StepProps) => {
  return (
    <section css={wrapperStyle}>
      <MeetStepTitle
        mainDescription="약속 시간 입력하기"
        subDescription="약속 시간대를 입력해주세요."
      />
      <MeetTimeForm />
      <Button onClick={onNextStep} variant="default" size="large">
        다음으로
      </Button>
    </section>
  );
};

export default MeetTimeStep;
