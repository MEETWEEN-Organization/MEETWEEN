import { ComponentPropsWithoutRef } from 'react';

import { profileStyle } from './Profile.style';

interface ProfileProps extends ComponentPropsWithoutRef<'img'> {
  imgUrl: string;
}

const Profile = ({ imgUrl, ...props }: ProfileProps) => {
  return <img alt="프로필" css={profileStyle} src={imgUrl} {...props} />;
};

export default Profile;
