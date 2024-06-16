import { useFunnelInfo } from '@/context/funnel';

import { useRef, useState } from 'react';

import Button from '@/components/common/Button/Button';
import ColorPicker from '@/components/common/ColorPicker/ColorPicker';
import Flex from '@/components/common/Flex/Flex';
import Input from '@/components/common/Input/Input';
import { wrapperStyle } from '@/components/meet/MeetStep.style';
import { formStyle } from '@/components/meet/MeetTitleCategoryStep/MeetTitleCategoryStep.style';
import MeetStepTitle from '@/components/meet/common/MeetStepTitle/MeetStepTitle';

import { StepProps } from '@/type/funnel';

const MeetTitleCategoryStep = ({ onNextStep }: StepProps) => {
  const { info, handleTitleChange, handleCategoryChange, handleSelectColor } = useFunnelInfo();

  const [errorState, setErrorState] = useState({ title: false, category: false });

  const titleRef = useRef<HTMLInputElement>(null);
  const categoryRef = useRef<HTMLInputElement>(null);

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    if (!info.title && titleRef.current) {
      titleRef.current.focus();
      setErrorState((prev) => ({ ...prev, title: true }));
      return;
    }
    if (!info.category && categoryRef.current) {
      categoryRef.current.focus();
      setErrorState((prev) => ({ ...prev, category: true }));
      return;
    }

    onNextStep?.();
  };

  return (
    <section css={wrapperStyle}>
      <MeetStepTitle
        mainDescription={'약속의 이름과 카테고리를 입력해주세요'}
        subDescription="약속의 이름과 카테고리 이름, 색상을 선택해주세요"
      />
      <form css={formStyle} onSubmit={handleSubmit}>
        <Input
          ref={titleRef}
          isError={!info.title && errorState.title}
          variant="text"
          size="large"
          label="약속 이름"
          name="title"
          placeholder="약속 이름을 설정해주세요"
          value={info.title}
          onChange={(e) => {
            handleTitleChange(e);
            errorState.title && setErrorState((prev) => ({ ...prev, title: false }));
          }}
        />
        <Flex styles={{ gap: '24px' }}>
          <Input
            ref={categoryRef}
            isError={!info.category && errorState.category}
            variant="text"
            size="large"
            label="카테고리 이름"
            name="category"
            placeholder="카테고리를 설정해주세요"
            value={info.category}
            onChange={(e) => {
              handleCategoryChange(e);
              errorState.category && setErrorState((prev) => ({ ...prev, category: false }));
            }}
          />
          <ColorPicker
            label="카테고리 색상"
            color={info.categoryColor}
            onSelectColor={handleSelectColor}
          />
        </Flex>

        <Button type="submit" variant="default" size="large">
          다음으로
        </Button>
      </form>
    </section>
  );
};

export default MeetTitleCategoryStep;
