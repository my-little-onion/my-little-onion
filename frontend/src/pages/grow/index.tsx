import SpeechRecognition, {
  useSpeechRecognition,
} from 'react-speech-recognition';
import { useEffect, useState } from 'react';
import { Link, useLocation } from 'react-router-dom';
import styled from '@emotion/styled';

import theme from '@/styles/theme';
import { onionNameRecord } from '@/utils/onionRecord';
import { IconArrowLeft } from '#/svgs';
import { OnionTitle } from '@/pages/choose';
import { postSpeechToText } from '@/services/chatGpt';

import Background from '@/components/Background';
import Button from '@/components/Button';
import Onion from '@/components/Onion';

const GrowPageWrapper = styled.section`
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  position: relative;
`;

const GrowPage = () => {
  const location = useLocation();
  const { onionId, categoryId } = location.state;

  const [message, setMessage] = useState<string>('');
  const [isRecord, setIsRecord] = useState<boolean>(false);
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
    await postSpeechToText(onionId, message);
    setMessage('');
  };

  const voice = transcript.slice(0);

  useEffect(() => {
    setMessage(voice);
  }, [voice]);

  return (
    <Background>
      <GrowPageWrapper>
        <Link to='/choose'>
          <Button
            type='button'
            svg={<IconArrowLeft width={60} height={60} />}
          />
        </Link>
        <OnionTitle>{onionNameRecord[categoryId]}</OnionTitle>
        <Onion categoryId={categoryId} />
        {isRecord ? (
          <Button type='button' color={theme.color.blue} onClick={stopRecord}>
            <span>그만 말하기</span>
          </Button>
        ) : (
          <Button type='button' color={theme.color.blue} onClick={startRecord}>
            <span>양파에게 말하기</span>
          </Button>
        )}
      </GrowPageWrapper>
    </Background>
  );
};

export default GrowPage;
