import { useEffect, useState } from 'react';

import { getOnions } from '@/services/onion';
import { onion } from '@/types/onion';

import Background from '@/components/Background';
import Button from '@/components/Button';

import { IconArrowLeft, IconArrowRight } from '#/svgs';

const ChoosePage = () => {
  const [onionIndex, setOnionIndex] = useState<number>(0);
  const [onions, setOnions] = useState<onion[]>([]);

  const handlePrevClick = () => {
    setOnionIndex((prev) => (prev === 0 ? 2 : prev - 1));
  };

  const handleNextClick = () => {
    setOnionIndex((prev) => (prev === 2 ? 0 : prev + 1));
  };

  useEffect(() => {
    const fetchData = async () => {
      const rawData = await getOnions();
      setOnions(rawData.data);
    };
    fetchData();
  }, []);

  return (
    <Background>
      {onions.length > onionIndex ? (
        <div>{onions[onionIndex].onionName}</div>
      ) : (
        <div>{onionIndex + 1}번째 양파가 없어</div>
      )}
      <Button
        type='button'
        onClick={handlePrevClick}
        svg={<IconArrowLeft width={40} height={40} />}
      />
      <Button
        type='button'
        onClick={handleNextClick}
        svg={<IconArrowRight width={40} height={40} />}
      />
    </Background>
  );
};

export default ChoosePage;
