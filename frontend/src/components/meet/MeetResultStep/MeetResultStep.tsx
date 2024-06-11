import { useFunnelInfo } from '@/context/funnel';

import Button from '@/components/common/Button/Button';
import { resultWrapperStyle } from '@/components/meet/MeetResultStep/MeetResultStep.style';
import MeetResultCard from '@/components/meet/common/MeetResultCard/MeetResultCard';
import MeetStepTitle from '@/components/meet/common/MeetStepTitle/MeetStepTitle';

import { getResultDate } from '@/utils/date';

interface MeetResultStepProps {
  onComplete: () => void;
}

const MeetResultStep = ({ onComplete }: MeetResultStepProps) => {
  const {
    info: { title, category },
    date,
    time,
    guestCount,
  } = useFunnelInfo();

  return (
    <section css={resultWrapperStyle}>
      <MeetStepTitle
        mainDescription="약속 생성 완료"
        subDescription="생성된 약속 정보를 확인하세요."
      />
      <MeetResultCard
        meetTitle={title}
        meetTime={getResultDate(date, time)}
        meetCategory={category}
        memberCount={guestCount}
        meetPlace="인천광역시 미추홀구 주안역"
        inviteCode={123123}
      />
      <Button onClick={onComplete} type="button" size="large">
        약속 확인하러 가기
      </Button>
    </section>
  );
};

export default MeetResultStep;
