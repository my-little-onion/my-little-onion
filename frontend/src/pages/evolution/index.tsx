import { useLocation } from 'react-router-dom';
import ReactAudioPlayer from 'react-audio-player';

import Evolution from '@/components/Evolution';

import { EvolutionAudio } from '#/audios';

const EvolutionPage = () => {
  const { before, after, isTutorial } = useLocation().state;

  return (
    <>
      <Evolution before={before} after={after} isTutorial={isTutorial} />
      <ReactAudioPlayer src={EvolutionAudio} autoPlay />
    </>
  );
};

export default EvolutionPage;
