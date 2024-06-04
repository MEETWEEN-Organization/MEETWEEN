import { ComponentPropsWithoutRef } from 'react';

import Button from '@/components/common/Button/Button';
import { containerStyle, titleWrapperStyle } from '@/components/common/Card/Card.style';
import Heading from '@/components/common/Heading/Heading';
import Text from '@/components/common/Text/Text';

import { GETTING_STARTED_TEXT } from '@/constants/common';

import CreateButtonImg from '@/assets/svg/create.svg?react';
import JoinButtonImg from '@/assets/svg/join.svg?react';
import create from '@/assets/svg/main-create.webp';
import join from '@/assets/svg/main-join.webp';
import myinfo from '@/assets/svg/main-mine.webp';

interface CardProps extends ComponentPropsWithoutRef<'div'> {
  variant: 'create' | 'join' | 'myInfo';
  onClick?: () => void;
}

const IMG_BY_VARIANT = {
  create: <img alt="약속 생성 이미지" src={create} width={180} height={180} />,
  join: <img alt="약속 참여 이미지" src={join} width={180} height={180} />,
  myInfo: <img alt="약속 확인 이미지" src={myinfo} width={180} height={180} />,
};

const Card = ({ variant, onClick }: CardProps) => {
  const { title, description, buttonText } = GETTING_STARTED_TEXT[variant];

  const Img = IMG_BY_VARIANT[variant];

  return (
    <div css={containerStyle}>
      {Img}
      <div css={titleWrapperStyle}>
        <Heading size="xSmall">{title}</Heading>
        <Text size="small">{description}</Text>
      </div>
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
