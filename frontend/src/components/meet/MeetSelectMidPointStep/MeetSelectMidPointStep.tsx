import { css } from '@emotion/react';

import Button from '@/components/common/Button/Button';
import {
  buttonStyle,
  containerStyle,
  listStyle,
} from '@/components/meet/MeetSelectMidPointStep/MeetSelectMidPointStep.style';
import MeetMidPointCard from '@/components/meet/common/MeetMidPointCard/MeetMidPointCard';
import MeetStepTitle from '@/components/meet/common/MeetStepTitle/MeetStepTitle';

import { useSelect } from '@/hooks/common/useSelect';

import { StepProps } from '@/type/funnel';

import { Theme } from '@/styles/theme/theme';

type MidPointProps = {
  id: number;
  carDuration: number;
  subwayDuration: number;
  destination: string;
  averageDuration: string;
};

const MeetSelectMidPointStep = ({ onNextStep }: StepProps) => {
  const data = [
    { id: 1, carDuration: 20, subwayDuration: 30, destination: '주안역', averageDuration: '1시간' },
    {
      id: 2,
      carDuration: 34,
      subwayDuration: 50,
      destination: '강남역',
      averageDuration: '1시간 24분',
    },
    {
      id: 3,
      carDuration: 15,
      subwayDuration: 24,
      destination: '신도림역',
      averageDuration: '42분',
    },
  ];

  const { selectedItem, handleSelect } = useSelect<MidPointProps>({
    id: 0,
    carDuration: 0,
    subwayDuration: 0,
    destination: '',
    averageDuration: '',
  });

  return (
    <section
      css={css`
        display: flex;
      `}
    >
      <div css={containerStyle}>
        <MeetStepTitle
          mainDescription="📍 중간 장소 결과를 확인하세요"
          subDescription="참여자들의 위치 기반 중간 지점의 후보군들입니다."
        />
        <div css={listStyle}>
          {data.map((item) => (
            <MeetMidPointCard
              key={item.id}
              cardId={item.id}
              selectId={selectedItem.id}
              onClick={() => handleSelect(item)}
              carDuration={item.carDuration}
              subwayDuration={item.subwayDuration}
              destination={item.destination}
              averageDuration={item.averageDuration}
            />
          ))}
        </div>
        <Button onClick={onNextStep} type="button" size="large" css={buttonStyle}>
          다음으로
        </Button>
      </div>
      <div
        css={css`
          width: 100%;
          height: calc(100vh - 66px);
          background-color: ${Theme.color.blue400};
        `}
      />
      {/* <GoogleMapWrapper>
        <Map
          center={{
            lat: '37.123',
            lng: '126.12',
          }}
        />
      </GoogleMapWrapper> */}
    </section>
  );
};

export default MeetSelectMidPointStep;
