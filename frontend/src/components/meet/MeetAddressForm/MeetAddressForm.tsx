import Flex from '@/components/common/Flex/Flex';
import Heading from '@/components/common/Heading/Heading';
import Input from '@/components/common/Input/Input';
import {
  currentCompletedTextStyle,
  formStyle,
} from '@/components/meet/MeetAddressForm/MeetAddressForm.style';

import SearchIcon from '@/assets/svg/search.svg?react';

import { Theme } from '@/styles/theme/theme';

interface MeetAddressFormProps {
  friendsLength?: number;
}

const MeetAddressForm = ({ friendsLength = 3 }: MeetAddressFormProps) => {
  const addressInputArray = new Array(friendsLength).fill(0).map((_, i) => i + 1);

  return (
    <Flex
      tag="section"
      styles={{
        direction: 'column',
        align: 'center',
        gap: Theme.spacing.spacing3,
      }}
    >
      <Heading size="xSmall">
        <span css={currentCompletedTextStyle}>1&nbsp;</span>
        <span>/&nbsp;</span>
        <span>{friendsLength}</span>
      </Heading>
      <form css={formStyle}>
        {addressInputArray.map((index) => (
          <Input
            key={index}
            variant="text"
            icon={<SearchIcon />}
            placeholder={`주소 ${index}을 입력해주세요`}
            onChange={() => {}}
          />
        ))}
      </form>
    </Flex>
  );
};

export default MeetAddressForm;
