import Button from '@/components/common/Button/Button';
import { wrapperStyle } from '@/components/meet/MeetMemberCountStep/MeetMemberCountStep.style';
import MeetInfoForm from '@/components/meet/common/MeetInfoForm/MeetInfoForm';
import MeetStepTitle from '@/components/meet/common/MeetStepTitle/MeetStepTitle';

import { StepProps } from '@/type/funnel';

const MeetTitleCategoryStep = ({ onNextStep = () => {} }: StepProps) => {
  return (
    <section css={wrapperStyle}>
      <MeetStepTitle
        mainDescription={'약속의 이름과 카테고리를 입력해주세요'}
        subDescription="약속의 이름과 카테고리 이름, 색상을 선택해주세요"
      />
      <MeetInfoForm />
      <Button onClick={onNextStep} variant="default" size="large">
        다음으로
      </Button>
    </section>
  );
};

export default MeetTitleCategoryStep;
