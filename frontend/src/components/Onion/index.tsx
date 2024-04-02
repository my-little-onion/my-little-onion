import styled from '@emotion/styled';

import { onionRecord } from '@/utils/onionRecord';

const OnionWrapper = styled.div`
  width: 100%;
  height: 350px;
  display: flex;
  justify-content: center;
  align-items: center;
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
}

const Onion = ({ categoryId, size }: OnionProps) => {
  if (size === 'small') {
    return (
      <SmallOnionWrapper>
        <img
          src={`/images/onions/onion-${onionRecord[categoryId]}.png`}
          alt={onionRecord[categoryId]}
          width={100}
        />
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
      </MediumOnionWrapper>
    );
  }

  return (
    <OnionWrapper>
      <img
        src={`/images/onions/onion-${onionRecord[categoryId]}.png`}
        alt={onionRecord[categoryId]}
      />
    </OnionWrapper>
  );
};

export default Onion;
