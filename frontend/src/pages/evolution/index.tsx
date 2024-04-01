import { useLocation } from 'react-router-dom';

import Evolution from '@/components/Evolution';

const EvolutionPage = () => {
  const { before, after } = useLocation().state;

  return <Evolution before={before} after={after} />;
};

export default EvolutionPage;
