import SpeechRecognition, {
  useSpeechRecognition,
} from 'react-speech-recognition';
import { useEffect, useState } from 'react';

const TestPage = () => {
  // const [record, setRecord] = useState<boolean>(false);
  const [message, setMessage] = useState<string>('');
  const { transcript, resetTranscript } = useSpeechRecognition({});

  // const ChangeMode = (e) => {
  //   setRecord((record) => !record);
  //   e.preventDefault();
  // };
  const startRecord = () => {
    SpeechRecognition.startListening({
      continuous: true,
      language: 'ko-KR',
    });
    resetTranscript();
  };
  const stopRecord = () => {
    SpeechRecognition.stopListening();
  };

  const voice = transcript.slice(0);

  useEffect(() => {
    setMessage(voice);
  }, [voice]);

  return (
    <div>
      <button type='button' onClick={startRecord}>
        녹음 시작
      </button>
      <button type='button' onClick={stopRecord}>
        녹음 종료
      </button>
      <span>{message}</span>
    </div>
  );
};

export default TestPage;
