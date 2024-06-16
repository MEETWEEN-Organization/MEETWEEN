import { ComponentPropsWithoutRef } from 'react';

import Flex from '@/components/common/Flex/Flex';
import Heading from '@/components/common/Heading/Heading';
import MeetFindRouteButton from '@/components/meet/common/MeetMidPointCard/CardFindRouteButton';

interface MeetMidPointCardProps extends ComponentPropsWithoutRef<'li'> {
  destination: string;
  routeUrl?: string;
}

const MeetMidPointCard = ({ destination, routeUrl = '' }: MeetMidPointCardProps) => {
  const handleRedirect = () => {
    window.location.href = routeUrl;
  };

  return (
    <Flex styles={{ justify: 'space-between' }}>
      <Heading size="xSmall">{destination}</Heading>
      <MeetFindRouteButton onClick={handleRedirect} />
    </Flex>
  );
};

export default MeetMidPointCard;
