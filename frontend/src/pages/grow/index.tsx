import SpeechRecognition, {
  useSpeechRecognition,
} from 'react-speech-recognition';
import { useEffect, useState } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import styled from '@emotion/styled';
import { useRecoilState } from 'recoil';

import theme from '@/styles/theme';
import { onionNameRecord } from '@/utils/onionRecord';
import { OnionTitle } from '@/pages/choose';
import { postSpeechToText } from '@/services/chatGpt';
import Water from '@/pages/grow/Water';
import { voiceCountState } from '@/states/voiceCount';

import Background from '@/components/Background';
import Button from '@/components/Button';
import Onion from '@/components/Onion';

import {
  IconArrowLeft,
  IconHeart,
  IconRecordStart,
  IconRecordStop,
} from '#/svgs';

const GrowPageWrapper = styled.section`
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  position: relative;
`;

const ButtonWrapper = styled.div`
  position: absolute;
  top: 10px;
  left: 10px;
`;

const SvgWrapper = styled.div`
  position: absolute;
  top: 50%;
  transform: translate(40%, -50%);
`;

const HeartWrapper = styled.div`
  width: 100px;
  margin-bottom: 20px;
  display: flex;
  align-items: center;
  justify-content: space-around;
`;

const OnionText = styled.h2`
  top: 10%;
  position: absolute;
`;

const GrowPage = () => {
  const [message, setMessage] = useState<string>('');
  const [isRecord, setIsRecord] = useState<boolean>(false);
  const [isWatering, setIsWatering] = useState<boolean>(false);
  const [voiceCount, setVoiceCount] = useRecoilState(voiceCountState);
  const navigate = useNavigate();
  const { onionId, categoryId, isFinal, voiceNumber } = useLocation().state;
  const { transcript, resetTranscript } = useSpeechRecognition({});

  const startRecord = () => {
    setIsRecord(true);
    SpeechRecognition.startListening({
      continuous: true,
      language: 'ko-KR',
    });
    resetTranscript();
  };

  const stopRecord = async () => {
    setIsRecord(false);
    await SpeechRecognition.stopListening();
    const rawData = await postSpeechToText(onionId, message);
    const { canEvolve, categoryId: nextId } = rawData.data;

    if (canEvolve) {
      navigate('/evolution', { state: { before: categoryId, after: nextId } });
    } else {
      setIsWatering(true);
      setTimeout(() => {
        setIsWatering(false);
      }, 4000);
    }

    setVoiceCount((prev) => prev - 1);
  };

  const voice = transcript.slice(0);

  useEffect(() => {
    setMessage(voice);
  }, [voice]);

  useEffect(() => {
    setVoiceCount(voiceNumber);
  }, []);

  return (
    <Background>
      <GrowPageWrapper>
        <Link to='/choose'>
          <ButtonWrapper>
            <Button
              type='button'
              svg={<IconArrowLeft width={60} height={60} />}
            />
          </ButtonWrapper>
        </Link>
        <OnionText hidden={!isWatering}>아직 아무런 반응이 없어요!</OnionText>
        <OnionTitle>{onionNameRecord[categoryId]}</OnionTitle>
        <Onion categoryId={categoryId}>
          <Water isBegin={isWatering} />
        </Onion>
        <HeartWrapper>
          <IconHeart width={50} />
          <h2> x {voiceCount}</h2>
        </HeartWrapper>
        {isRecord ? (
          <Button
            type='button'
            color={theme.color.blue}
            size='medium'
            onClick={stopRecord}
          >
            <SvgWrapper>
              <IconRecordStop width={25} height={25} />
            </SvgWrapper>
            <span>그만 말하기</span>
          </Button>
        ) : (
          !isFinal && (
            <Button
              type='button'
              color={theme.color.blue}
              size='medium'
              onClick={startRecord}
              disabled={voiceCount === 0}
            >
              <SvgWrapper>
                <IconRecordStart width={25} height={25} />
              </SvgWrapper>
              <span>
                {voiceCount !== 0 ? '양파에게 말하기' : '잠시 기다려주세요'}
              </span>
            </Button>
          )
        )}
      </GrowPageWrapper>
    </Background>
  );
};

export default GrowPage;
