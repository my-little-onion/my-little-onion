import styled from '@emotion/styled';
import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';

import useModal from '@/hooks/useModal';
import OnionInfo from '@/pages/collection/OnionInfo';
import { collection } from '@/types/collection';
import { getCollections } from '@/services/collection';
import { IconArrowLeft } from '#/svgs';

import Onion from '@/components/Onion';
import Background from '@/components/Background';
import Button from '@/components/Button';

const ButtonWrapper = styled.div`
  position: absolute;
  top: 10px;
  left: 10px;
`;

const TitleWrapper = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  height: 12vh;
`;

const Title = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  position: relative;
  width: 180px;
  height: 50px;
  background-color: #007fff;
  border-radius: 20px;
  color: white;
  font-size: 25px;
`;

const CollectionWrapper = styled.div`
  display: flex;
  justify-content: center;
  height: 85vh;
`;

const Collection = styled.div`
  position: relative;
  background-color: rgb(255, 255, 255, 0.6);
  width: 90%;
  height: 100%;
  border-radius: 20px;
`;

const RateWrapper = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  height: 10%;
`;

const Rate = styled.div`
  display: flex;
  justify-content: center;
  background-color: #b9b9b9;
  width: 45%;
  height: 80%;
  border-radius: 25px;
  color: white;
  font-size: 20px;
`;

const RateString = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  width: 60%;
  height: 100%;
  background-color: #007fff;
  border-radius: 25px;
`;

const RateNumber = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  width: 50%;
`;

const OnionInfoWrapper = styled.div`
  height: 90%;
  padding: 20px;
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  place-items: center;
  gap: 20px;
  overflow-y: scroll;
`;

const CollectionPage = () => {
  const [collections, setCollections] = useState<collection[]>([]);
  const [count, setCount] = useState<number>(12);
  const [percentage, setPercentage] = useState<number>(0);
  const [currName, setCurrName] = useState<string>('');
  const [currDetail, setCurrDetail] = useState<string>('');

  const { Modal, openModal } = useModal();

  const handleOnionInfoClick = () => {
    setCurrName('하이');
    setCurrDetail('테스트');
    openModal();
  };

  const fetchData = async () => {
    const rawData = await getCollections();
    const currCount = rawData.data.reduce(
      (acc, item) => acc + (item.have ? 1 : 0),
      0,
    );

    setCount(currCount);
    setCollections(rawData.data);
  };

  useEffect(() => {
    fetchData();
    setPercentage(Math.ceil(count / collections.length));
  }, [count]);

  return (
    <Background>
      <Link to='/choose'>
        <ButtonWrapper>
          <Button
            type='button'
            svg={<IconArrowLeft width={50} height={50} />}
          />
        </ButtonWrapper>
      </Link>
      <TitleWrapper>
        <Title>
          <span>양파 도감</span>
        </Title>
      </TitleWrapper>
      <CollectionWrapper>
        <Collection>
          <RateWrapper>
            <Rate>
              <RateString>달성률</RateString>
              <RateNumber>{percentage}%</RateNumber>
            </Rate>
          </RateWrapper>
          <OnionInfoWrapper>
            {collections &&
              collections.map((item) => (
                <OnionInfo
                  key={item.id}
                  categoryId={item.id}
                  isCollected={item.have}
                  onClick={handleOnionInfoClick}
                />
              ))}
          </OnionInfoWrapper>
        </Collection>
      </CollectionWrapper>
      <Modal>
        <h3>{currName}</h3>
        <Onion size='medium' categoryId={1} />
        <span>힌트: {currDetail}</span>
      </Modal>
    </Background>
  );
};

export default CollectionPage;
