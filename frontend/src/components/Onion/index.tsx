import styled from '@emotion/styled';

import { onionRecord } from '@/utils/onionRecord';

const OnionWrapper = styled.div`
  width: 100%;
  height: 400px;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const SmallOnionWrapper = styled(OnionWrapper)`
  height: 100px;
`;

interface OnionProps {
  categoryId: number;
  size?: 'small' | 'large';
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
