import { HTMLAttributes, useState } from 'react';

import Badge from '@/components/common/Badge/Badge';
import Flex from '@/components/common/Flex/Flex';
import Heading from '@/components/common/Heading/Heading';
import Text from '@/components/common/Text/Text';
import {
  cardStyle,
  inviteCodeStyle,
  itemTitleWrapperStyle,
  itemWrapperStyle,
  listStyle,
} from '@/components/meet/common/MeetResultCard/MeetResultCard.style';

import { useToast } from '@/hooks/common';

import Calendar from '@/assets/svg/calendar.svg?react';
import ClipboardCheck from '@/assets/svg/check-document.svg?react';
import Clone from '@/assets/svg/clone.svg?react';
import Key from '@/assets/svg/key.svg?react';
import Member from '@/assets/svg/member.svg?react';
import Position from '@/assets/svg/position.svg?react';

interface MeetResultCardProps extends HTMLAttributes<HTMLDivElement> {
  meetTitle?: string;
  memberCount?: number;
  meetTime?: string;
  meetPlace?: string;
  inviteCode?: number;
}

const MeetResultCard = ({
  meetTitle,
  memberCount,
  meetTime,
  meetPlace,
  inviteCode,
}: MeetResultCardProps) => {
  const { createToast } = useToast();
  const [isCopy, setIsCopy] = useState(false);

  const handleClipBoard = async () => {
    try {
      await navigator.clipboard.writeText(String(inviteCode));
      setIsCopy(true);
      createToast('복사에 성공하였습니다.', 'success');
    } catch {
      createToast('복사에 실패하였습니다.', 'error');
    }

    setTimeout(() => {
      setIsCopy(false);
    }, 2000);
  };

  return (
    <div css={cardStyle}>
      <Flex styles={{ gap: '12px' }}>
        <Heading size="xSmall">{meetTitle}</Heading>
        <Badge size="small" text="동아리 활동" color="purple100" />
      </Flex>

      <ul css={listStyle}>
        <li css={itemWrapperStyle}>
          <div css={itemTitleWrapperStyle}>
            <Member width={20} height={20} />
            <Text size="medium">인원 수</Text>
          </div>
          <Text size="medium">{memberCount}명</Text>
        </li>
        <li css={itemWrapperStyle}>
          <div css={itemTitleWrapperStyle}>
            <Calendar width={20} height={20} />
            <Text size="medium">약속 시간대</Text>
          </div>
          <Text size="medium">{meetTime}</Text>
        </li>
      </ul>
      <ul css={listStyle}>
        <li css={itemWrapperStyle}>
          <div css={itemTitleWrapperStyle}>
            <Position width={20} height={20} />
            <Text size="medium">약속 장소</Text>
          </div>
          <Text size="medium">{meetPlace}</Text>
        </li>
        <li css={itemWrapperStyle}>
          <div css={itemTitleWrapperStyle}>
            <Key width={20} height={20} />
            <Text size="medium">초대 코드(6자리)</Text>
          </div>
          <Text css={inviteCodeStyle} size="medium">
            {inviteCode}
            {isCopy ? (
              <ClipboardCheck width={20} />
            ) : (
              <Clone onClick={handleClipBoard} width={20} height={20} />
            )}
          </Text>
        </li>
      </ul>
    </div>
  );
};

export default MeetResultCard;
