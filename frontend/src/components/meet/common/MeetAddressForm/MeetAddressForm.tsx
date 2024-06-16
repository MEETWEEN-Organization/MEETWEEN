import Flex from '@/components/common/Flex/Flex';
import Input from '@/components/common/Input/Input';
import Text from '@/components/common/Text/Text';
import {
  formStyle,
  textStyle,
} from '@/components/meet/common/MeetAddressForm/MeetAddressForm.style';

import { AddressType } from '@/type/place';

import SearchIcon from '@/assets/svg/search.svg?react';

import { Theme } from '@/styles/theme/theme';

interface MeetAddressFormProps {
  addressInfo?: AddressType[];
  addressCount?: number;
  onSearchAddress?: (index: number) => void;
}

const MeetAddressForm = ({
  addressInfo = [],
  addressCount = 3,
  onSearchAddress,
}: MeetAddressFormProps) => {
  const addressInputArray = new Array(addressCount).fill(0).map((_, i) => i + 1);

  const currentInputCount = addressInfo.filter((item) => item.address_name !== '').length;

  return (
    <Flex
      tag="section"
      styles={{
        direction: 'column',
        align: 'center',
        gap: Theme.spacing.spacing3,
      }}
    >
      <Text css={textStyle} size="large">
        <span>{currentInputCount}</span>
        <span>/</span>
        <span>{addressCount}</span>
      </Text>
      <form css={formStyle}>
        {addressInputArray.map((index) => (
          <Input
            key={index}
            variant="text"
            icon={<SearchIcon />}
            value={addressInfo[index - 1].address_name}
            placeholder={`주소 ${index}을 입력해주세요`}
            onChange={() => {}}
            onFocus={() => onSearchAddress?.(index - 1)}
            // onClick={() => handleSearchAddress(index - 1)}
          />
        ))}
      </form>
    </Flex>
  );
};

export default MeetAddressForm;
