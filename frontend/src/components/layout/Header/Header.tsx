import { useNavigate } from 'react-router-dom';

import { useRecoilValue } from 'recoil';

import Flex from '@/components/common/Flex/Flex';
import Text from '@/components/common/Text/Text';
import { appNameStyle, headerStyle, logoStyle } from '@/components/layout/Header/Header.style';
import LoggedInMenu from '@/components/layout/Header/LoggedInMenu/LoggedInMenu';
import LoggedOutMenu from '@/components/layout/Header/LoggedOutMenu/LoggedOutMenu';

import { isLoggedInState } from '@/store/auth';

import { URL_PATH } from '@/constants/url';

import Logo from '@/assets/svg/logo.svg?react';

import { Theme } from '@/styles/theme/theme';

interface HeaderProps {}

const Header = ({}: HeaderProps) => {
  const isLoggedIn = useRecoilValue(isLoggedInState);

  const navigate = useNavigate();

  return (
    <header css={headerStyle}>
      <Flex styles={{ align: 'center', justify: 'space-between' }}>
        <Flex styles={{ align: 'center', gap: Theme.spacing.spacing3 }}>
          <Logo css={logoStyle} />
          <Text onClick={() => navigate(URL_PATH.HOME)} css={appNameStyle} size="large">
            MEETWEEN
          </Text>
        </Flex>
        {isLoggedIn ? <LoggedInMenu /> : <LoggedOutMenu />}
      </Flex>
    </header>
  );
};

export default Header;
