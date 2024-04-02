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
        <Onion categoryId={categoryId} size='small' />
      )}
    </OnionInfoContent>
  );
};

export default OnionInfo;
