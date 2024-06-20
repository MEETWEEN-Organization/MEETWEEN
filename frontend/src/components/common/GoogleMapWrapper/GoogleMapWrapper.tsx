import { Status, Wrapper } from '@googlemaps/react-wrapper';

import { PropsWithChildren } from 'react';

import Spinner from '@/components/common/Spinner/Spinner';

type GoogleMapWrapperProps = PropsWithChildren;

const GoogleMapWrapper = ({ children }: GoogleMapWrapperProps) => {
  const render = (status: Status) => {
    if (status === Status.FAILURE) throw new Error('google map load fail');
    // if (status === Status.LOADING) return <Spinner />;

    return <Spinner />;
  };

  return (
    <Wrapper
      apiKey={import.meta.env.VITE_GOOGLE_APP_KEY}
      render={render}
      libraries={['marker', 'places']}
      language="ko"
    >
      {children}
    </Wrapper>
  );
};

export default GoogleMapWrapper;
