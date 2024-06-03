import { useState } from 'react';

import Flex from '@/components/common/Flex/Flex';
import Heading from '@/components/common/Heading/Heading';
import Input from '@/components/common/Input/Input';
import {
  currentCompletedTextStyle,
  formStyle,
} from '@/components/meet/MeetAddressForm/MeetAddressForm.style';

import { AddressResultType, LatLngType } from '@/type/funnel';

import SearchIcon from '@/assets/svg/search.svg?react';

import { Theme } from '@/styles/theme/theme';

interface MeetAddressFormProps {
  friendsLength?: number;
}

const MeetAddressForm = ({ friendsLength = 3 }: MeetAddressFormProps) => {
  const addressInputArray = new Array(friendsLength).fill(0).map((_, i) => i + 1);
  const initialInputValues = new Array(friendsLength).fill('');

  const [latLngs, setLatLngs] = useState<LatLngType[]>([]);
  const [values, setValues] = useState<string[]>(initialInputValues);

  console.log(latLngs);

  const currentInputCount = values.filter((value) => !!value).length;

  const handleSearchAddress = (index: number) => {
    new window.daum.Postcode({
      oncomplete: (data: { address: string }) => {
        const geocoder = new window.kakao.maps.services.Geocoder();

        geocoder.addressSearch(data.address, async (result: AddressResultType[], status: any) => {
          if (status !== 'OK') return;

          setLatLngs((prev) => [
            ...prev,
            {
              x: result[0].x,
              y: result[0].y,
              key: index,
            },
          ]);
          setValues((prev) => {
            const next = prev.concat();
            next[index] = result[0].address.address_name;
            return next;
          });
        });
      },
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
      <Heading size="xSmall">
        <span css={currentCompletedTextStyle}>{currentInputCount}&nbsp;</span>
        <span>/&nbsp;</span>
        <span>{friendsLength}</span>
      </Heading>
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
