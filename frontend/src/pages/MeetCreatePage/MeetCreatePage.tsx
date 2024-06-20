import { css } from '@emotion/react';

import React, { SetStateAction, Suspense, createContext, lazy, useState } from 'react';
import { useNavigate } from 'react-router-dom';

import GoogleMapWrapper from '@/components/common/GoogleMapWrapper/GoogleMapWrapper';
import ProgressBar from '@/components/common/ProgressBar/ProgressBar';
import MeetResultStep from '@/components/meet/MeetResultStep/MeetResultStep';
import MeetSelectMidPointStep from '@/components/meet/MeetSelectMidPointStep/MeetSelectMidPointStep';

import { useFunnel } from '@/hooks/meet/useFunnel';

import { MeetFunnelInfoProvider } from '@/context/funnel';

import { StepType } from '@/type/funnel';

import { STEP_COUNT } from '@/constants/meet';

const MeetAddressStep = lazy(() => import('@/components/meet/MeetAddressStep/MeetAddressStep'));
const MeetDateSelect = lazy(() => import('@/components/meet/MeetDateStep/MeetDateStep'));
const MeetMemberCountForm = lazy(
  () => import('@/components/meet/MeetMemberCountStep/MeetMemberCountStep'),
);
const MeetTimeStep = lazy(() => import('@/components/meet/MeetTimeStep/MeetTimeStep'));
const MeetTitleCategoryForm = lazy(
  () => import('@/components/meet/MeetTitleCategoryStep/MeetTitleCategoryStep'),
);

export type InfoType = {
  title: string;
  category: string;
  categoryColor: string;
};

type MeetFunnelStateType = {
  info: InfoType;
  setInfo: React.Dispatch<SetStateAction<InfoType>>;
  guestCount: number;
  handleChangeCount: (change: number) => void;
  time: string;
  handleChangeTime: (time: string) => void;
};

export const MeetFunnelContext = createContext<MeetFunnelStateType | null>(null);

const MeetCreatePage = () => {
  const { Funnel, setStep } = useFunnel();

  const [currentStep, setCurrentStep] = useState(1);

  const navigate = useNavigate();

  const handleNextStep = (step: StepType) => {
    setStep(step);
    setCurrentStep((prev) => prev + 1);
  };

  return (
    <MeetFunnelInfoProvider>
      <ProgressBar css={progressBarStyle} degree={currentStep} maxLength={STEP_COUNT} />
      <Funnel>
        <Funnel.Step name="약속 인원">
          <Suspense>
            <MeetMemberCountForm onNextStep={() => handleNextStep('약속 이름&카테고리')} />
          </Suspense>
        </Funnel.Step>
        <Funnel.Step name="약속 이름&카테고리">
          <Suspense>
            <MeetTitleCategoryForm onNextStep={() => handleNextStep('약속 날짜')} />
          </Suspense>
        </Funnel.Step>
        <Funnel.Step name="약속 날짜">
          <Suspense>
            <MeetDateSelect onNextStep={() => handleNextStep('약속 시간')} />
          </Suspense>
        </Funnel.Step>
        <Funnel.Step name="약속 시간">
          <Suspense>
            <MeetTimeStep onNextStep={() => handleNextStep('출발지 입력')} />
          </Suspense>
        </Funnel.Step>
        <Funnel.Step name="출발지 입력">
          <Suspense>
            <MeetAddressStep onNextStep={() => handleNextStep('중간 장소 결과')} />
          </Suspense>
        </Funnel.Step>
        <Funnel.Step name="중간 장소 결과">
          <GoogleMapWrapper>
            <MeetSelectMidPointStep onNextStep={() => handleNextStep('약속 생성 완료')} />
          </GoogleMapWrapper>
        </Funnel.Step>
        <Funnel.Step name="약속 생성 완료">
          <MeetResultStep onComplete={() => navigate('/mymeet')} />
        </Funnel.Step>
      </Funnel>
    </MeetFunnelInfoProvider>
  );
};

export default MeetCreatePage;

export const progressBarStyle = css({
  position: 'fixed',
});
