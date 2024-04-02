import { useLocation } from 'react-router-dom';
import ReactAudioPlayer from 'react-audio-player';

import { EvolutionAudio } from '#/audios';

import Evolution from '@/components/Evolution';

const EvolutionPage = () => {
  const { before, after } = useLocation().state;

  return (
    <>
      <Evolution before={before} after={after} />
      <ReactAudioPlayer src={EvolutionAudio} autoPlay />
    </>
  );
};

export default EvolutionPage;
