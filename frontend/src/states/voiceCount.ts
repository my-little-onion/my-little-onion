import { atom } from 'recoil';
import { recoilPersist } from 'recoil-persist';

const { persistAtom } = recoilPersist({
  key: 'persist-atom-key',
  storage: localStorage,
});
export const voiceCountState = atom<number>({
  key: 'voiceCount',
  default: -1,
  effects_UNSTABLE: [persistAtom],
});
