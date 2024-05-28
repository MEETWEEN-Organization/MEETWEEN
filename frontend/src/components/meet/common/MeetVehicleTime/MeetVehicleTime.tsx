import { HTMLAttributes } from 'react';

import Text from '@/components/common/Text/Text';
import { wrapperStyle } from '@/components/meet/common/MeetVehicleTime/MeetVehicleTime.style';

import CarIcon from '@/assets/svg/car.svg?react';
import SubwayIcon from '@/assets/svg/subway.svg?react';

interface MeetVehicleTimeProps extends HTMLAttributes<HTMLParagraphElement> {
  vehicle: 'car' | 'subway';
  duration: number;
}

const MeetVehicleTime = ({ vehicle, duration }: MeetVehicleTimeProps) => {
  return (
    <p css={wrapperStyle}>
      {vehicle === 'car' ? (
        <>
          <CarIcon width={36} height={36} />
          <Text size="large">{duration}분</Text>
        </>
      ) : (
        <>
          <SubwayIcon width={36} height={32} />
          <Text size="large">{duration}분</Text>
        </>
      )}
    </p>
  );
};

export default MeetVehicleTime;
