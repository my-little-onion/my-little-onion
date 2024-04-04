import styled from '@emotion/styled';

import Onion from '@/components/Onion';

const OnionInfoContent = styled.button`
  width: 110px;
  height: 110px;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: white;
  border: none;
  border-radius: 20px;
  cursor: pointer;
  box-shadow: 4px 5px #c0c0c0;
`;

const OnionNotHaveWrapper = styled.div`
  filter: brightness(0);
`;

interface OnionInfoProps {
  onClick: () => void;
  categoryId: number;
  isCollected: boolean;
}

const OnionInfo = ({ onClick, categoryId, isCollected }: OnionInfoProps) => {
  return (
    <OnionInfoContent onClick={onClick}>
      {isCollected ? (
        <Onion categoryId={categoryId} size='small' />
      ) : (
        <OnionNotHaveWrapper>
          <Onion categoryId={categoryId} size='small' />
        </OnionNotHaveWrapper>
      )}
    </OnionInfoContent>
  );
};

export default OnionInfo;
