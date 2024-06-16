import { css } from '@emotion/react';

import { wrapperStyle } from '@/components/meet/MeetStep.style';

export const resultWrapperStyle = css`
  ${wrapperStyle}

  & > button {
    white-space: nowrap;
  }
`;
