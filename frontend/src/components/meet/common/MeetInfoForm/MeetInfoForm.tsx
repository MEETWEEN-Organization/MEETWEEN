import ColorPicker from '@/components/common/ColorPicker/ColorPicker';
import Flex from '@/components/common/Flex/Flex';
import Input from '@/components/common/Input/Input';
import { formStyle } from '@/components/meet/common/MeetInfoForm/MeetInfoForm.style';

import { COLOR_PALLETE } from '@/constants/common';

interface MeetInfoFormProps {
  selectedColor?: string;
  onSelectColor?: (color: string) => void;
}

const MeetInfoForm = ({
  selectedColor = COLOR_PALLETE[0],
  onSelectColor = () => {},
}: MeetInfoFormProps) => {
  return (
    <section css={formStyle}>
      <Input
        variant="text"
        size="large"
        label="약속 이름"
        name="title"
        placeholder="약속 이름을 설정해주세요"
      />
      <Flex styles={{ gap: '24px' }}>
        <Input
          variant="text"
          size="large"
          label="카테고리 이름"
          name="category"
          placeholder="카테고리를 설정해주세요"
        />
        <ColorPicker label="카테고리 색상" color={selectedColor} onSelectColor={onSelectColor} />
      </Flex>
    </section>
  );
};

export default MeetInfoForm;
