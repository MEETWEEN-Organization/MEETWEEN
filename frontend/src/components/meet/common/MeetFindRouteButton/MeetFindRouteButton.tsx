import { css } from '@emotion/react';

import { ComponentPropsWithoutRef } from 'react';

import Button from '@/components/common/Button/Button';
import { iconStyle } from '@/components/meet/common/MeetFindRouteButton/MeetFindRouteButton.style';

import ForwardArrow from '@/assets/svg/foward-arrow.svg?react';

interface MeetFindRouteButtonProps extends ComponentPropsWithoutRef<'button'> {
  redirectUrl?: string;
}

const MeetFindRouteButton = ({ redirectUrl = '' }: MeetFindRouteButtonProps) => {
  const handleRedirect = () => {
    window.location.href = redirectUrl;
  };

  const handleKeyDown = (e: React.KeyboardEvent<HTMLButtonElement>) => {
    const { key } = e;
    if (key === 'Enter') {
      window.location.href = redirectUrl;
    }
  };
  return (
    <Button
      variant="text"
      size="small"
      onClick={handleRedirect}
      onKeyDown={handleKeyDown}
      css={css`
        padding: 0;
      `}
      icon={<ForwardArrow css={iconStyle} />}
    >
      길찾기
    </Button>
  );
};

export default MeetFindRouteButton;
