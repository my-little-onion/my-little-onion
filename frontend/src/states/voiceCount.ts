import { atom } from 'recoil';

export const voiceCountState = atom<number>({
  key: 'voiceCount',
  default: 0,
});
