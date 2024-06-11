import { ComponentPropsWithoutRef } from 'react';

import Flex from '@/components/common/Flex/Flex';
import Heading from '@/components/common/Heading/Heading';
import Text from '@/components/common/Text/Text';
import {
  imgStyle,
  titleWrapperStyle,
} from '@/components/meet/common/MeetStepTitle/MeetStepTitle.style';

import bulbJpg from '@/assets/img/main-bulb.jpg';
import bulb from '@/assets/img/main-bulb.webp';

import { Theme } from '@/styles/theme/theme';

export interface MeetStepTitleProps extends ComponentPropsWithoutRef<'header'> {
  logoSize?: number;
  logoPos?: 'left' | 'top';
  mainDescription: string;
  subDescription: string;
}

const MeetStepTitle = ({
  logoSize = 140,
  logoPos = 'top',
  mainDescription,
  subDescription,
  ...props
}: MeetStepTitleProps) => {
  return (
    <Flex
      tag="header"
      styles={{
        direction: logoPos === 'left' ? 'row' : 'column',
        align: 'center',
        gap: Theme.spacing.spacing6,
      }}
      {...props}
    >
      <picture css={imgStyle(logoSize)}>
        <source srcSet={bulb} type="image/webp" />
        <img src={bulbJpg} alt="Bulb" />
      </picture>

      <div css={titleWrapperStyle}>
        <Heading size="xLarge">{mainDescription}</Heading>
        <Text size="small">{subDescription}</Text>
      </div>
    </Flex>
  );
};

export default MeetStepTitle;
