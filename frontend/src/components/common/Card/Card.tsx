import { ComponentPropsWithoutRef } from 'react';

import Button from '@/components/common/Button/Button';
import { containerStyle } from '@/components/common/Card/Card.style';
import Heading from '@/components/common/Heading/Heading';
import Text from '@/components/common/Text/Text';

import { GETTING_STARTED_TEXT } from '@/constants/common';

import CreateButtonImg from '@/assets/svg/create.svg?react';
import JoinButtonImg from '@/assets/svg/join.svg?react';
import CreateImg from '@/assets/svg/main-create.svg?react';
import JoinImg from '@/assets/svg/main-join.svg?react';
import MyInfoImg from '@/assets/svg/main-mine.svg?react';

interface CardProps extends ComponentPropsWithoutRef<'div'> {
  variant: 'create' | 'join' | 'myInfo';
  onClick?: () => void;
}

const getVariantImg = (variant: CardProps['variant']) => {
  if (variant === 'create') return <CreateImg width={240} height={200} />;
  if (variant === 'join') return <JoinImg width={240} height={200} />;
  return <MyInfoImg width={240} height={200} />;
};

const Card = ({ variant, onClick }: CardProps) => {
  const { title, description, buttonText } = GETTING_STARTED_TEXT[variant];

  return (
    <div css={containerStyle}>
      {getVariantImg(variant)}
      <Heading size="xSmall">{title}</Heading>
      <Text size="small">{description}</Text>
      <Button
        icon={variant === 'create' ? <CreateButtonImg /> : <JoinButtonImg />}
        onClick={onClick}
        variant="default"
        size="medium"
      >
        {buttonText}
      </Button>
    </div>
  );
};

export default Card;
