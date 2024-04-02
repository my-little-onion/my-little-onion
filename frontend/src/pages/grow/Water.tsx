import { useState, useEffect } from 'react';
import { keyframes } from '@emotion/react';
import styled from '@emotion/styled';

import { IconDrop } from '#/svgs';

const WaterAnimation = keyframes`
  5% {
    transform: translateY(0);
    opacity: 1;
  } 
  100% {
    transform: translateY(200px);
    opacity: 0;
  }
`;

const WaterWrapper = styled.div`
  top: 0;
  width: 100%;
  position: absolute;
`;

const IconDropAnimation = styled(IconDrop)<{ delay: number }>`
  position: absolute;
  opacity: 0;
  animation: ${WaterAnimation} 2s infinite;
  animation-delay: ${({ delay }) => delay}s;
`;

interface Drop {
  num: number;
  place: number;
  delay: number;
}

interface WaterProps {
  isBegin: boolean;
}

const WaterPage = ({ isBegin }: WaterProps) => {
  const [drops, setDrops] = useState<Drop[]>([]);

  useEffect(() => {
    const newDrops = [];

    for (let i = 0; i < 10; i++) {
      const newDrop = {
        num: Math.random(),
        place: 30 + ((60 - 30) / (10 - 1)) * i,
        delay: Math.random() * 1.2,
      };
      newDrops.push(newDrop);
    }
    setDrops(newDrops);
  }, []);

  return (
    <WaterWrapper hidden={!isBegin}>
      {drops.map((currDrop) => (
        <IconDropAnimation
          key={currDrop.num}
          style={{ left: `${currDrop.place}%` }}
          delay={currDrop.delay}
          width={50}
        />
      ))}
    </WaterWrapper>
  );
};

export default WaterPage;
