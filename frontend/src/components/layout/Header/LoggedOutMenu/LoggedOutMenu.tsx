import { useNavigate } from 'react-router-dom';

import Button from '@/components/common/Button/Button';

import { URL_PATH } from '@/constants/url';

const LoggedInMenu = () => {
  const navigate = useNavigate();

  return (
    <Button variant="text" onClick={() => navigate(URL_PATH.LOGIN)}>
      로그인
    </Button>
  );
};

export default LoggedInMenu;
