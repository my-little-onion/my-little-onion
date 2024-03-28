import { useState } from 'react';

import Background from '@/components/Background';
import Button from '@/components/Button';

import { IconArrowLeft, IconArrowRight } from '#/svgs';

const ChoosePage = () => {
  const [onionIndex, setOnionIndex] = useState<number>(0);

  const handlePrevClick = () => {
    setOnionIndex((prev) => (prev === 0 ? 2 : prev - 1));
  };

  const handleNextClick = () => {
    setOnionIndex((prev) => (prev === 2 ? 0 : prev + 1));
  };

  return (
    <Background>
      <div>{onionIndex}</div>
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
