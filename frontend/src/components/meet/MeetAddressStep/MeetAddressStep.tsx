import { useCallback, useState } from 'react';

import Button from '@/components/common/Button/Button';
import GoogleMapWrapper from '@/components/common/GoogleMapWrapper/GoogleMapWrapper';
import { wrapperStyle } from '@/components/meet/MeetStep.style';
import MeetAddressForm from '@/components/meet/common/MeetAddressForm/MeetAddressForm';
import MeetStepTitle from '@/components/meet/common/MeetStepTitle/MeetStepTitle';

import { useFunnelInfo } from '@/context/funnel';

import { StepProps } from '@/type/funnel';
import { AddressType, LatLngType } from '@/type/place';

const MeetAddressStep = ({ onNextStep }: StepProps) => {
  const { guestCount, handleAddOrigins: setOrigins } = useFunnelInfo();

  const initialInputValues = new Array(guestCount).fill({
    x: '',
    y: '',
    address_name: '',
    key: 0,
  });

  const [addressInfo, setAddressInfo] = useState<AddressType[]>(initialInputValues);

  const handleSearchAddress = useCallback(async (index: number) => {
    new window.daum.Postcode({
      oncomplete: (data: { address: string }) => {
        const geocoder = new window.kakao.maps.services.Geocoder();

        /** geocoder로 주소 이름을 lat lng으로 변환하기 */
        geocoder.addressSearch(data.address, async (result: LatLngType[], status: any) => {
          if (status === 'OK') {
            const info = {
              x: result[0].x,
              y: result[0].y,
              address_name: result[0].address_name,
              key: index,
            };

            /** 주소를 postcode를 통해 입력하고, 다시 돌아와 수정할 수 있으므로 index 접근하여 수정 */
            setAddressInfo((prev) => {
              const next = [...prev];
              next[index] = info;

              return next;
            });
          }
        });
      },
    }).open();
  }, []);

  const handleSubmit = useCallback(() => {
    setOrigins(addressInfo);

    onNextStep?.();
  }, [addressInfo]);

  return (
    <section css={wrapperStyle}>
      <MeetStepTitle
        mainDescription="출발지 입력하기"
        subDescription="약속에 참여할 친구들의 출발지를 입력해주세요."
      />
      <GoogleMapWrapper>
        <MeetAddressForm
          addressInfo={addressInfo}
          addressCount={guestCount}
          onSearchAddress={handleSearchAddress}
        />
      </GoogleMapWrapper>
      <Button onClick={handleSubmit} type="submit" variant="default" size="large">
        다음으로
      </Button>
    </section>
  );
};

export default MeetAddressStep;
