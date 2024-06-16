import { ComponentPropsWithoutRef } from 'react';

import { cardContainerStyle } from '@/components/meet/common/MeetMidPointCard/Card.style';
import CardHeader from '@/components/meet/common/MeetMidPointCard/CardHeader';
import CardVehicleTime from '@/components/meet/common/MeetMidPointCard/CardVehicleTime';

interface MeetMidPointCardProps extends ComponentPropsWithoutRef<'li'> {
  cardId: number | string;
  selectId: number | string;
}

const CardWrapper = ({ cardId, selectId, children, ...props }: MeetMidPointCardProps) => {
  const isSelected = cardId === selectId;

  return (
    <li
      role="listitem"
      aria-selected={isSelected}
      tabIndex={0}
      css={cardContainerStyle(isSelected)}
      {...props}
    >
      {children}
    </li>
  );
};

const Card = {
  Wrapper: CardWrapper,
  Header: CardHeader,
  Time: CardVehicleTime,
};

export default Card;
