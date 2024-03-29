import { useEffect, useState } from 'react';
import styled from '@emotion/styled';

import { getOnions } from '@/services/onion';
import { onion } from '@/types/onion';
import theme from '@/styles/theme';
import { onionNameRecord } from '@/utils/onionRecord';

import Background from '@/components/Background';
import Button from '@/components/Button';
import Onion from '@/components/Onion';

import { IconArrowLeft, IconArrowRight } from '#/svgs';

const ChoosePageWrapper = styled.section`
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

const CommandButtonWrapper = styled.section`
  width: 90%;
  display: flex;
  align-items: center;
  justify-content: space-between;
`;

const OnionTitle = styled.span`
  width: fit-content;
  height: 40px;
  overflow: hidden;
  padding: 4px 40px;
  background-color: ${theme.color.black};
  color: ${theme.color.white};
  display: flex;
  align-items: center;
  border-radius: 20px;
`;

const OnionName = styled.h3`
  margin-bottom: 40px;
`;

const ChoosePage = () => {
  const [onionIndex, setOnionIndex] = useState<number>(0);
  const [onions, setOnions] = useState<onion[]>([]);

  const currOnion = onions[onionIndex];

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
      <ChoosePageWrapper>
        <OnionTitle>
          {onionNameRecord[currOnion?.onionCategoryId ?? 0]}
        </OnionTitle>
        {onions.length > onionIndex ? (
          <>
            <Onion categoryId={currOnion.onionCategoryId} />
            <OnionName>{currOnion.onionName}</OnionName>
          </>
        ) : (
          <>
            <Onion categoryId={0} />
            <OnionName>{onionIndex + 1}번째 양파가 없어</OnionName>
          </>
        )}
        <CommandButtonWrapper>
          <Button
            type='button'
            onClick={handlePrevClick}
            svg={<IconArrowLeft width={40} height={40} />}
          />
          {onions.length > onionIndex ? (
            <Button type='button' size='small' color={theme.color.blue}>
              양파한테 말걸러 가기
            </Button>
          ) : (
            <Button type='button' size='small' color={theme.color.green}>
              새 양파 생성
            </Button>
          )}
          <Button
            type='button'
            onClick={handleNextClick}
            svg={<IconArrowRight width={40} height={40} />}
          />
        </CommandButtonWrapper>
      </ChoosePageWrapper>
    </Background>
  );
};

export default ChoosePage;