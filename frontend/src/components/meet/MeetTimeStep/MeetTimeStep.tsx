import { useFunnelInfo } from '@/context/funnel';

import { useState } from 'react';

import Button from '@/components/common/Button/Button';
import Flex from '@/components/common/Flex/Flex';
import Input from '@/components/common/Input/Input';
import { wrapperStyle } from '@/components/meet/MeetMemberCountStep/MeetMemberCountStep.style';
import MeetStepTitle from '@/components/meet/common/MeetStepTitle/MeetStepTitle';

import { StepProps } from '@/type/funnel';

import { Theme } from '@/styles/theme/theme';

const MeetTimeStep = ({ onNextStep }: StepProps) => {
  const { handleChangeTime } = useFunnelInfo();

  const [hour, setHour] = useState('');
  const [minute, setMinute] = useState('');

  const handleSubmit = () => {
    handleChangeTime(`${hour.padStart(2, '0')}:${minute.padStart(2, '0')}`);

    onNextStep?.();
  };

  return (
    <section css={wrapperStyle}>
      <MeetStepTitle
        mainDescription="약속 시간 입력하기"
        subDescription="약속 시간대를 입력해주세요."
      />
      <Flex
        tag="form"
        styles={{
          gap: Theme.spacing.spacing5,
        }}
      >
        <Input
          value={hour}
          onChange={(e) => setHour(e.target.value)}
          variant="text"
          size="large"
          label="Hour"
          placeholder="0 ~ 23"
        />
        <Input
          value={minute}
          onChange={(e) => setMinute(e.target.value)}
          variant="text"
          size="large"
          label="Minute"
          placeholder="0 ~ 59"
        />
      </Flex>
      <Button type="submit" onClick={handleSubmit} variant="default" size="large">
        다음으로
      </Button>
    </section>
  );
};

export default MeetTimeStep;
