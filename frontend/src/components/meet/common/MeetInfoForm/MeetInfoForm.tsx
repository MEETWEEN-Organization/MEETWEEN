import { useFunnelInfo } from '@/context/funnel';

import ColorPicker from '@/components/common/ColorPicker/ColorPicker';
import Flex from '@/components/common/Flex/Flex';
import Input from '@/components/common/Input/Input';
import { formStyle } from '@/components/meet/common/MeetInfoForm/MeetInfoForm.style';

const MeetInfoForm = () => {
  const { info, handleTitleChange, handleCategoryChange, handleSelectColor } = useFunnelInfo();

  return (
    <section css={formStyle}>
      <Input
        variant="text"
        size="large"
        label="약속 이름"
        name="title"
        placeholder="약속 이름을 설정해주세요"
        value={info.title}
        onChange={handleTitleChange}
      />
      <Flex styles={{ gap: '24px' }}>
        <Input
          variant="text"
          size="large"
          label="카테고리 이름"
          name="category"
          placeholder="카테고리를 설정해주세요"
          value={info.category}
          onChange={handleCategoryChange}
        />
        <ColorPicker
          label="카테고리 색상"
          color={info.categoryColor}
          onSelectColor={handleSelectColor}
        />
      </Flex>
    </section>
  );
};

export default MeetInfoForm;
