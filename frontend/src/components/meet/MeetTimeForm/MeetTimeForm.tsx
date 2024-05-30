import Flex from '@/components/common/Flex/Flex';
import Input from '@/components/common/Input/Input';

import { Theme } from '@/styles/theme/theme';

interface MeetTimeFormProps {}

const MeetTimeForm = ({}: MeetTimeFormProps) => {
  return (
    <Flex
      styles={{
        gap: Theme.spacing.spacing5,
      }}
    >
      <Input variant="text" size="large" label="Hour" placeholder="00" />
      <Input variant="text" size="large" label="Minute" placeholder="00" />
    </Flex>
  );
};

export default MeetTimeForm;