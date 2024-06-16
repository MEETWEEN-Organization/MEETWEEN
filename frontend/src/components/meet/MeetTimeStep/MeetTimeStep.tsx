import { useFunnelInfo } from '@/context/funnel';

import { useRef, useState } from 'react';

import Button from '@/components/common/Button/Button';
import Flex from '@/components/common/Flex/Flex';
import Input from '@/components/common/Input/Input';
import { wrapperStyle } from '@/components/meet/MeetStep.style';
import { formStyle } from '@/components/meet/MeetTitleCategoryStep/MeetTitleCategoryStep.style';
import MeetStepTitle from '@/components/meet/common/MeetStepTitle/MeetStepTitle';

import { StepProps } from '@/type/funnel';

import { Theme } from '@/styles/theme/theme';

const MeetTimeStep = ({ onNextStep }: StepProps) => {
  const { handleChangeTime: setTime } = useFunnelInfo();

  const [hour, setHour] = useState('');
  const [minute, setMinute] = useState('');

  const [errorState, setErrorState] = useState({ hour: false, minute: false });

  const hourRef = useRef<HTMLInputElement>(null);
  const minuteRef = useRef<HTMLInputElement>(null);

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    if (!hour && hourRef.current) {
      hourRef.current.focus();
      setErrorState((prev) => ({ ...prev, hour: true }));
      return;
    }
    if (!minute && minuteRef.current) {
      minuteRef.current.focus();
      setErrorState((prev) => ({ ...prev, minute: true }));
      return;
    }

    setTime(`${hour.padStart(2, '0')}:${minute.padStart(2, '0')}`);

    onNextStep?.();
  };

  return (
    <section css={wrapperStyle}>
      <MeetStepTitle
        mainDescription="약속 시간 입력하기"
        subDescription="약속 시간대를 입력해주세요."
      />
      <form onSubmit={handleSubmit} css={formStyle}>
        <Flex styles={{ gap: Theme.spacing.spacing5 }}>
          <Input
            ref={hourRef}
            isError={!hour}
            value={hour}
            onChange={(e) => {
              setHour(e.target.value);
              errorState.hour && setErrorState((prev) => ({ ...prev, hour: false }));
            }}
            variant="text"
            size="large"
            label="Hour"
            placeholder="0 ~ 23"
          />
          <Input
            ref={minuteRef}
            isError={!minute}
            value={minute}
            onChange={(e) => {
              setMinute(e.target.value);
              errorState.minute && setErrorState((prev) => ({ ...prev, minute: false }));
            }}
            variant="text"
            size="large"
            label="Minute"
            placeholder="0 ~ 59"
          />
        </Flex>
        <Button type="submit" variant="default" size="large">
          다음으로
        </Button>
      </form>
    </section>
  );
};

export default MeetTimeStep;
