import { css } from '@emotion/react';

import { wrapperStyle } from '@/components/meet/MeetMemberCountStep/MeetMemberCountStep.style';

export const resultWrapperStyle = css`
  ${wrapperStyle}

  & > button {
    white-space: nowrap;
  }
`;
