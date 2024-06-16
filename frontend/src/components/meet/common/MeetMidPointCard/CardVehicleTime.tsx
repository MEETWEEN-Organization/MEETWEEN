import { HTMLAttributes } from 'react';

import Flex from '@/components/common/Flex/Flex';
import Text from '@/components/common/Text/Text';
import {
  durationAverageTextStyle,
  wrapperStyle,
} from '@/components/meet/common/MeetMidPointCard/Card.style';

import CarIcon from '@/assets/svg/car.svg?react';
import SubwayIcon from '@/assets/svg/subway.svg?react';

interface MeetVehicleTimeProps extends HTMLAttributes<HTMLParagraphElement> {
  averageDuration?: string;
  carDuration?: number;
  subwayDuration?: number;
}

const CardVehicleTime = ({
  averageDuration,
  carDuration,
  subwayDuration,
}: MeetVehicleTimeProps) => {
  return (
    <Flex styles={{ justify: 'space-between', align: 'center', width: '95%' }}>
      <p css={durationAverageTextStyle}>
        <span>{averageDuration}</span>
        <Text size="small">평균 이동 시간</Text>
      </p>

      <p css={wrapperStyle}>
        <CarIcon width={36} height={36} />
        <Text size="large">{carDuration}분</Text>

        <SubwayIcon width={36} height={32} />
        <Text size="large">{subwayDuration}분</Text>
      </p>
    </Flex>
  );
};

export default CardVehicleTime;
