import SpeechRecognition, {
  useSpeechRecognition,
} from 'react-speech-recognition';
import { useRef, useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import styled from '@emotion/styled';

// import { postMessage } from '@/services/chat-gpt';
import theme from '@/styles/theme';
import { onionNameRecord } from '@/utils/onionRecord';
import { IconArrowLeft } from '#/svgs';
import { OnionTitle } from '@/pages/choose';

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
`;

const GrowPage = () => {
  // const location = useLocation();
  // const { categoryId } = location.state;

  const [message, setMessage] = useState<string>('');
  const { transcript, resetTranscript } = useSpeechRecognition({});

  const isRecording = useRef<boolean>(false);

  const startRecord = () => {
    isRecording.current = true;
    SpeechRecognition.startListening({
      continuous: true,
      language: 'ko-KR',
    });
    resetTranscript();
  };

  const stopRecord = () => {
    isRecording.current = false;
    SpeechRecognition.stopListening();
    // postMessage(categoryId, message);
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
        <OnionTitle>{onionNameRecord[1]}</OnionTitle>
        {/* categoryId 받아오기 */}
        <Onion categoryId={1} />
        {isRecording.current ? (
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
