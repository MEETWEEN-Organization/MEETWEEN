import { ComponentPropsWithoutRef, useCallback, useState } from 'react';

import Counter from '@/components/common/Counter/Counter';
import {
  buttonStyle,
  wrapperStyle,
} from '@/components/meet/common/MeetGuestCounter/MeetGuestCounter.style';

interface MeetGuestCounterProps extends ComponentPropsWithoutRef<'section'> {
  guestCount: number;
  onCountChange?: (change: number) => void;
  maxLength?: number;
}

/* eslint-disable react-hooks/exhaustive-deps */
const MeetGuestCounter = ({
  guestCount,
  onCountChange = () => {},
  maxLength = 10,
}: MeetGuestCounterProps) => {
  const [isError, setIsError] = useState(false);

  const checkDecreaseLimit = useCallback((count: number) => {
    if (count === 1) {
      setIsError(true);
      return false;
    }
    !isError && setIsError(false);
    return true;
  }, []);

  const checkIncreaseLimit = useCallback((count: number) => {
    if (count === maxLength) {
      setIsError(true);
      return false;
    }
    !isError && setIsError(false);
    return true;
  }, []);

  return (
    <section css={wrapperStyle}>
      <button
        onClick={() => onCountChange(checkDecreaseLimit(guestCount) ? -1 : 0)}
        css={buttonStyle}
      >
        -
      </button>
      <Counter count={guestCount} isError={isError} />
      <button
        onClick={() => onCountChange(checkIncreaseLimit(guestCount) ? +1 : 0)}
        css={buttonStyle}
      >
        +
      </button>
    </section>
  );
};

export default MeetGuestCounter;
