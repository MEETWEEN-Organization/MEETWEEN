import { ComponentPropsWithoutRef } from 'react';

import { profileStyle } from './Profile.style';

interface ProfileProps extends ComponentPropsWithoutRef<'img'> {
  imgUrl: string;
}

const Profile = ({ imgUrl, ...props }: ProfileProps) => {
  return <img css={profileStyle} src={imgUrl} {...props} />;
};

export default Profile;
