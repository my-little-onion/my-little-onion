import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { keyframes } from '@emotion/react';
import { useRecoilState } from 'recoil';
import styled from '@emotion/styled';

import { deleteOnion, getOnions } from '@/services/onion';
import { onion, voiceType } from '@/types/onion';
import theme from '@/styles/theme';
import { onionNameRecord } from '@/utils/onionRecord';
import Stars from '@/pages/choose/Stars';
import useModal from '@/hooks/useModal';
import OnionNameInput from '@/pages/choose/OnionNameInput';
import { onionIndexState } from '@/pages/choose/store';

import Background from '@/components/Background';
import Button from '@/components/Button';
import Onion from '@/components/Onion';

import { IconArrowLeft, IconArrowRight } from '#/svgs';

const OnionAnimation = keyframes`
  0% {
    transform: translateY(10px);
  }
  50% {
    transform: translateY(0);
  }
  100% {
    transform: translateY(10px);
  }
`;

const ChoosePageWrapper = styled.section`
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

const CollectionWrapper = styled.div`
  position: absolute;
  top: 3%;
  right: 5%;
`;

const LevelWrapper = styled.section`
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 10px;
`;

const CommandButtonWrapper = styled.section`
  width: 90%;
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 8px;
`;

const BlankDelete = styled.div`
  width: 100%;
  height: 40px;
`;

export const OnionTitle = styled.span`
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

const AnimatedOnion = styled.div`
  animation: ${OnionAnimation} 3s infinite;
`;

const OnionName = styled.h3`
  margin-bottom: 20px;
`;

const ModalQuestion = styled.div`
  width: 100%;
  margin-bottom: 16px;
`;

const ModalMessageWrapper = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
`;

const ChoosePage = () => {
  const [onions, setOnions] = useState<onion[]>([]);
  const [onionIndex, setOnionIndex] = useRecoilState(onionIndexState);
  const [voices, setVoices] = useState<voiceType[]>([]);
  const [modalType, setModalType] = useState<'create' | 'delete'>('create');

  const { Modal, openModal, closeModal } = useModal();

  const currOnion = onions[onionIndex];

  const fetchData = async () => {
    const rawData = await getOnions();
    setOnions(rawData.data);
  };

  const handlePrevClick = () => {
    setOnionIndex((prev) => (prev === 0 ? 2 : prev - 1));
  };

  const handleNextClick = () => {
    setOnionIndex((prev) => (prev === 2 ? 0 : prev + 1));
  };

  const handleCreateClick = () => {
    setModalType('create');
    openModal();
  };

  const handleDeleteClick = async () => {
    setModalType('delete');
    const voiceData = await deleteOnion(currOnion.onionId);
    setVoices(voiceData.data);
    openModal();
  };

  useEffect(() => {
    fetchData();
  }, []);

  return (
    <Background>
      <ChoosePageWrapper>
        <Link to='/collection'>
          <CollectionWrapper>
            <img
              src='/images/icons/collection-book.png'
              alt='circle'
              height={60}
            />
          </CollectionWrapper>
        </Link>
        <OnionTitle>
          {onionNameRecord[currOnion?.onionCategoryId ?? 0]}
        </OnionTitle>
        {onions.length > onionIndex ? (
          <>
            <AnimatedOnion>
              <Onion categoryId={currOnion.onionCategoryId} />
            </AnimatedOnion>
            <OnionName>{currOnion.onionName}</OnionName>
            <LevelWrapper>
              <Stars level={currOnion?.onionLevel ?? 2} />
            </LevelWrapper>
          </>
        ) : (
          <>
            <Onion categoryId={0} />
            <OnionName>{onionIndex + 1}번째 양파를 만들어주세요</OnionName>
          </>
        )}
        <CommandButtonWrapper>
          <Button
            type='button'
            onClick={handlePrevClick}
            svg={<IconArrowLeft width={40} height={40} />}
          />
          {onions.length > onionIndex ? (
            <Link
              to='/grow'
              state={{
                onionId: currOnion.onionId,
                categoryId: currOnion.onionCategoryId,
                isFinal: currOnion.isFinal,
              }}
            >
              <Button type='button' size='medium' color={theme.color.blue}>
                양파한테 말걸러 가기
              </Button>
            </Link>
          ) : (
            <Button
              type='button'
              size='medium'
              color={theme.color.green}
              onClick={handleCreateClick}
            >
              새 양파 생성
            </Button>
          )}
          <Button
            type='button'
            onClick={handleNextClick}
            svg={<IconArrowRight width={40} height={40} />}
          />
        </CommandButtonWrapper>
        {onions.length > onionIndex ? (
          <Button
            type='button'
            size='medium'
            color={theme.color.red}
            onClick={handleDeleteClick}
          >
            양파 까기
          </Button>
        ) : (
          <BlankDelete />
        )}
      </ChoosePageWrapper>
      <Modal isDelete={modalType === 'delete'}>
        {modalType === 'create' ? (
          <>
            <ModalQuestion>양파의 이름은?</ModalQuestion>
            <OnionNameInput
              indexToSet={onions.length}
              fetchData={fetchData}
              closeModal={closeModal}
            />
          </>
        ) : (
          <>
            <ModalQuestion>내가 양파에게 했던 말</ModalQuestion>
            <ModalMessageWrapper>
              {voices.length > 0 ? (
                voices.map((voice) => (
                  <span key={voice.voice}>&quot;{voice.voice}&quot;</span>
                ))
              ) : (
                <span>양파에게 했던 말이 없어요</span>
              )}
            </ModalMessageWrapper>
          </>
        )}
      </Modal>
    </Background>
  );
};

export default ChoosePage;
