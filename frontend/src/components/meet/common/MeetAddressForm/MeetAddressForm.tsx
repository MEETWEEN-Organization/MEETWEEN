import { useState } from 'react';

import Flex from '@/components/common/Flex/Flex';
import Input from '@/components/common/Input/Input';
import Text from '@/components/common/Text/Text';
import {
  formStyle,
  textStyle,
} from '@/components/meet/common/MeetAddressForm/MeetAddressForm.style';

import { LatLngType } from '@/type/place';

import SearchIcon from '@/assets/svg/search.svg?react';

import { Theme } from '@/styles/theme/theme';

interface MeetAddressFormProps {
  addressCount?: number;
}

const MeetAddressForm = ({ addressCount = 3 }: MeetAddressFormProps) => {
  const addressInputArray = new Array(addressCount).fill(0).map((_, i) => i + 1);
  const initialInputValues = new Array(addressCount).fill('');

  const [latLngs, setLatLngs] = useState<LatLngType[]>([]);
  const [values, setValues] = useState<string[]>(initialInputValues);

  const currentInputCount = values.filter((value) => !!value).length;

  console.log(latLngs);

  const handleSearchAddress = (index: number) => {
    new window.daum.Postcode({
      oncomplete: (data: { address: string }) => {},
    }).open();
  };

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
            value={values[index - 1]}
            placeholder={`주소 ${index}을 입력해주세요`}
            onChange={() => {}}
            onFocus={() => handleSearchAddress(index - 1)}
            // onClick={() => handleSearchAddress(index - 1)}
          />
        ))}
      </form>
    </Flex>
  );
};

export default MeetAddressForm;
