import Flex from '@/components/common/Flex/Flex';
import Heading from '@/components/common/Heading/Heading';
import Text from '@/components/common/Text/Text';
import {
  imgStyle,
  titleWrapperStyle,
} from '@/components/meet/common/MeetPageTitle/MeetPageTitle.style';

import bulb_png from '@/assets/img/main-bulb.png';
import bulb from '@/assets/img/main-bulb.webp';

import { Theme } from '@/styles/theme/theme';

interface MeetPageTitleProps {
  mainDescription: string;
  subDescription: string;
}

const MeetPageTitle = ({ mainDescription, subDescription }: MeetPageTitleProps) => {
  return (
    <Flex
      tag="section"
      styles={{
        direction: 'column',
        align: 'center',
        gap: Theme.spacing.spacing6,
      }}
    >
      <picture css={imgStyle}>
        <source srcSet={bulb} type="image/webp" />
        <img src={bulb_png} alt="Bulb" />
      </picture>

      <div css={titleWrapperStyle}>
        <Heading size="xLarge">{mainDescription}</Heading>
        <Text size="small">{subDescription}</Text>
      </div>
    </Flex>
  );
};

export default MeetPageTitle;
