import { ReactElement, ReactNode, useMemo, useState } from 'react';

import { StepType } from '@/type/funnel';

type StepProps = {
  name: string;
  children: ReactNode;
};

type FunnelProps = {
  children: ReactElement<StepProps>[];
};

const Step = ({ children }: StepProps) => {
  return <>{children}</>;
};

export const useFunnel = () => {
  const [step, setStep] = useState<StepType>('약속 인원');

  const Funnel = ({ children }: FunnelProps) => {
    const targetStep = children.find((childStep) => childStep.props.name === step);

    return targetStep;
  };

  /* eslint-disable react-hooks/exhaustive-deps */
  const FunnelComponent = useMemo(
    () => Object.assign((props: FunnelProps) => <Funnel>{props.children}</Funnel>, { Step }),
    [step],
  );

  return { Funnel: FunnelComponent, setStep };
};
