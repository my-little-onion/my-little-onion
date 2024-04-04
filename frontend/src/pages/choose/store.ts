import { atom } from 'recoil';

export const onionIndexState = atom<number>({
  key: 'onionNumber',
  default: 0,
});
