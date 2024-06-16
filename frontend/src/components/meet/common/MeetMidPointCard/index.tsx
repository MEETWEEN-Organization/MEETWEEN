import { ComponentPropsWithoutRef } from 'react';

import Card from '@/components/meet/common/MeetMidPointCard/CardWrapper';

interface MeetMidPointCardProps extends ComponentPropsWithoutRef<'li'> {
  cardId: number | string;
  selectId: number | string;
  carDuration: number;
  subwayDuration: number;
  averageDuration: string;
  destination: string;
  routeUrl?: string;
  onSelect?: () => void;
}

const MeetMidPointCard = ({
  cardId,
  selectId,
  carDuration,
  subwayDuration,
  averageDuration,
  destination,
  routeUrl = '',
  ...props
}: MeetMidPointCardProps) => {
  return (
    <Card.Wrapper cardId={cardId} selectId={selectId} {...props}>
      <Card.Header routeUrl={routeUrl} destination={destination} />
      <Card.Time
        averageDuration={averageDuration}
        carDuration={carDuration}
        subwayDuration={subwayDuration}
      />
    </Card.Wrapper>
  );
};

export default MeetMidPointCard;
