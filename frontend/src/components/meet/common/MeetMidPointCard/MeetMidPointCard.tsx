import { ComponentPropsWithoutRef } from 'react';

import Flex from '@/components/common/Flex/Flex';
import Heading from '@/components/common/Heading/Heading';
import Text from '@/components/common/Text/Text';
import MeetFindRouteButton from '@/components/meet/common/MeetFindRouteButton/MeetFindRouteButton';
import {
  cardContainerStyle,
  durationAverageTextStyle,
} from '@/components/meet/common/MeetMidPointCard/MeetMidPointCard.style';
import MeetVehicleTime from '@/components/meet/common/MeetVehicleTime/MeetVehicleTime';

interface MeetMidPointCardProps extends ComponentPropsWithoutRef<'li'> {
  carDuration: number;
  subwayDuration: number;
  averageDuration: string;
  destination: string;
  routeUrl?: string;
}

const MeetMidPointCard = ({
  carDuration,
  subwayDuration,
  averageDuration,
  destination,
  routeUrl = '',
}: MeetMidPointCardProps) => {
  const handleRedirect = () => {
    window.location.href = routeUrl;
  };

  return (
    <li css={cardContainerStyle}>
      <Flex styles={{ justify: 'space-between' }}>
        <Heading size="xSmall">{destination}</Heading>
        <MeetFindRouteButton onClick={handleRedirect} />
      </Flex>
      <Flex styles={{ justify: 'space-between', width: '90%' }}>
        <p css={durationAverageTextStyle}>
          <span>{averageDuration}</span>
          <Text size="small">평균 이동 시간</Text>
        </p>
        <MeetVehicleTime vehicle="car" duration={carDuration} />
        <MeetVehicleTime vehicle="subway" duration={subwayDuration} />
      </Flex>
    </li>
  );
};

export default MeetMidPointCard;
