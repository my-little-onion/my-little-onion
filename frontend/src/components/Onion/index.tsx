import styled from '@emotion/styled';

const OnionWrapper = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
`;

const LargeOnion = styled.img`
  width: 500px;
  height: 500px;
`;

const SmallOnion = styled.img`
  width: 100px;
  height: 100px;
`;

interface OnionProps {
  onClick?: () => void;
  size: 'small' | 'large';
}

const Onion = ({ onClick, size }: OnionProps) => {
  if (size === 'small') {
    return (
      <OnionWrapper>
        <SmallOnion onClick={onClick} />
      </OnionWrapper>
    );
  }
  return (
    <OnionWrapper>
      <LargeOnion onClick={onClick} />
    </OnionWrapper>
  );
};

export default Onion;
