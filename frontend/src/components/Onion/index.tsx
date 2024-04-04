import styled from '@emotion/styled';
import { ReactNode } from 'react';

import { onionRecord } from '@/utils/onionRecord';

const OnionWrapper = styled.div`
  width: 100%;
  height: 350px;
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;
`;

const SmallOnionWrapper = styled(OnionWrapper)`
  height: 100px;
`;

const MediumOnionWrapper = styled(OnionWrapper)`
  height: 250px;
`;

interface OnionProps {
  categoryId: number;
  size?: 'small' | 'medium' | 'large';
  children?: ReactNode;
}

const Onion = ({ categoryId, size, children }: OnionProps) => {
  if (size === 'small') {
    return (
      <SmallOnionWrapper>
        <img
          src={`/images/onions/onion-${onionRecord[categoryId]}.png`}
          alt={onionRecord[categoryId]}
          width={100}
        />
        {children}
      </SmallOnionWrapper>
    );
  }

  if (size === 'medium') {
    return (
      <MediumOnionWrapper>
        <img
          src={`/images/onions/onion-${onionRecord[categoryId]}.png`}
          alt={onionRecord[categoryId]}
          width={250}
        />
        {children}
      </MediumOnionWrapper>
    );
  }

  return (
    <OnionWrapper>
      <img
        src={`/images/onions/onion-${onionRecord[categoryId]}.png`}
        alt={onionRecord[categoryId]}
      />
      {children}
    </OnionWrapper>
  );
};

export default Onion;
