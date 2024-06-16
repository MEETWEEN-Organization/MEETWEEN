import { ComponentPropsWithoutRef } from 'react';

import { profileStyle } from '@/components/common/Profile/Profile.style';

interface ProfileProps extends ComponentPropsWithoutRef<'img'> {
  imgUrl: string;
  size?: number;
}

const Profile = ({ size = 44, imgUrl, ...props }: ProfileProps) => {
  return <img alt="프로필" width={size} height={size} css={profileStyle} src={imgUrl} {...props} />;
};

export default Profile;
