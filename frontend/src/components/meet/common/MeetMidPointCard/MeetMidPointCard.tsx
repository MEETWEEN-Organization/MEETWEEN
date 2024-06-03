import { ComponentPropsWithoutRef } from 'react';

import Heading from '@/components/common/Heading/Heading';
import Text from '@/components/common/Text/Text';
import MeetFindRouteButton from '@/components/meet/common/MeetFindRouteButton/MeetFindRouteButton';
import {
  cardContainerStyle,
  durationAverageTextStyle,
  durationRowStyle,
  titleRowStyle,
} from '@/components/meet/common/MeetMidPointCard/MeetMidPointCard.style';
import MeetVehicleTime from '@/components/meet/common/MeetVehicleTime/MeetVehicleTime';

type MeetMidPointCardProps = ComponentPropsWithoutRef<'li'>;

const MeetMidPointCard = ({}: MeetMidPointCardProps) => {
  return (
    <li css={cardContainerStyle}>
      <div css={titleRowStyle}>
        <Heading size="xSmall">주안역</Heading>
        <MeetFindRouteButton />
      </div>
      <div css={durationRowStyle}>
        <p css={durationAverageTextStyle}>
          <p>1시간 25분</p>
          <Text size="small">평균 이동 시간</Text>
        </p>
        <MeetVehicleTime vehicle="car" duration={34} />
        <MeetVehicleTime vehicle="subway" duration={16} />
      </div>
    </li>
  );
};

export default MeetMidPointCard;
